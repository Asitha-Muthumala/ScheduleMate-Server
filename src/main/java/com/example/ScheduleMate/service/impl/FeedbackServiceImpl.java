package com.example.ScheduleMate.service.impl;

import com.example.ScheduleMate.configs.exception.CommonException;
import com.example.ScheduleMate.dto.FeedbackDto;
import com.example.ScheduleMate.entity.Client;
import com.example.ScheduleMate.entity.Feedback;
import com.example.ScheduleMate.repository.BookingRepository;
import com.example.ScheduleMate.repository.ClientRepository;
import com.example.ScheduleMate.repository.FeedbackRepository;
import com.example.ScheduleMate.repository.ServicesRepository;
import com.example.ScheduleMate.service.FeedbackService;
import com.example.ScheduleMate.utils.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ServicesRepository servicesRepository;
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void createFeedback(FeedbackDto feedbackDto) {

        Optional<Client> clientResult = Optional.of(clientRepository.getById(feedbackDto.getClientId()));
        Optional<Client> businessResult = Optional.of(clientRepository.getById(feedbackDto.getBusinessId()));

        if (clientResult.isPresent() && businessResult.isPresent()) {
            Feedback feedback = new Feedback();

            feedback.setComment(feedback.getComment());
            feedback.setRating(feedback.getRating());
            feedback.setBusiness(businessResult.get());
            feedback.setClient(clientResult.get());

            feedbackRepository.save(feedback);

        } else {
            throw new CommonException(ResponseCode.RESOURCE_NOT_FOUND);
        }

    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(feedback -> objectMapper.convertValue(feedback, FeedbackDto.class))
                .toList();
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND));
        return objectMapper.convertValue(feedback, FeedbackDto.class);
    }

    @Override
    public void updateFeedback(Long id, FeedbackDto feedbackDto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND));

        feedback.setRating(feedbackDto.getRating());
        feedback.setComments(feedbackDto.getComments());
        // Update relationships if necessary, similar to createFeedback

        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }
        feedbackRepository.deleteById(id);
    }
}
