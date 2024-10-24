package com.example.ScheduleMate.service.impl;

import com.example.ScheduleMate.config.exception.CommonException;
import com.example.ScheduleMate.dto.FeedbackDto;
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
    public void createFeedback(FeedbackDto feedbackDto) {

        Feedback feedbackEntity = objectMapper.convertValue(feedbackDto, Feedback.class);


        Optional<Long> service = Optional.ofNullable(feedbackDto.getServiceId());
        Optional<Long> booking = Optional.ofNullable(feedbackDto.getBookingId());
        Optional<Long> clients = Optional.ofNullable(feedbackDto.getClientId());

        if(service.isPresent()&&booking.isPresent()&&clients.isPresent()){
            feedbackEntity.setServices(servicesRepository.findById(feedbackDto.getServiceId()).get());
            feedbackEntity.setBooking(bookingRepository.findById(feedbackDto.getBookingId()).get());
            feedbackEntity.setClient(clientRepository.findById(feedbackDto.getClientId()).get());

        }else {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }


        feedbackRepository.save(feedbackEntity);


    }
}
