package com.ruwen.service;

import com.ruwen.data.Booking;

import java.util.Optional;

/**
 * Created by ruwen on 27.03.17.
 */
public class OptionalService {

    public Optional<String> findOptional() {
        return Optional.of("Hello world!");
    }

    public Optional<String> findEmptyOptional() {
        return Optional.empty();
    }

    public Optional<Booking> findBooking() {
        return Optional.of(Booking.builder().trackingNumber("123").build());
    }

}
