package com.example.ScheduleMate.service;

import com.example.ScheduleMate.dto.BookingDto;
import com.example.ScheduleMate.entity.Booking;
import com.example.ScheduleMate.entity.BookingStatus;

import java.util.List;

public interface BookingService {
    void createBooking(BookingDto booking);
    BookingDto approveOrRejectBooking(Long bookingId, BookingStatus status, String providerNotes);
    List<BookingDto> getBookingsByClientId(Long clientId);

}
