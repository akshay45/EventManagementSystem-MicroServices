package com.assignment.group.bookingservice.service;


import com.assignment.group.bookingservice.client.CustomerClient;
import com.assignment.group.bookingservice.client.EventClient;
import com.assignment.group.bookingservice.entity.Booking;
import com.assignment.group.bookingservice.repository.BookingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class BookingService {
    Logger logger=Logger.getLogger("BookingService");

    @Autowired
    private final BookingRepo bookingRepository;

    @Autowired
    private final EventClient eventClient;
    @Autowired
    private final CustomerClient customerClient;

    public BookingService(BookingRepo bookingRepository, EventClient eventClient,CustomerClient customerClient) {
        this.bookingRepository = bookingRepository;
        this.eventClient = eventClient;
        this.customerClient=customerClient;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        // Validate event existence using Feign client
        String eventResponse = eventClient.getEventById(booking.getEventID());
        String customerResponce=customerClient.getCustomerById(booking.getCustomerId());
        if (eventResponse == null) {
            if (customerResponce == null) {
                logger.info("Customer id not found");
            }
            else {
                logger.info("Event not found with id: " + booking.getEventID());
                throw new RuntimeException("Event not found with id: " + booking.getEventID());
            }


        }

        // Process booking
        booking.setStatus("Confirmed");
        logger.info("Booking created with id: " + booking.getEventID());
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        booking.setEventID(bookingDetails.getEventID());
        booking.setCustomerId(bookingDetails.getCustomerId());
        booking.setStatus(bookingDetails.getStatus());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }
}

