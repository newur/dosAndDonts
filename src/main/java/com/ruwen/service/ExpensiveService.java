package com.ruwen.service;

import com.ruwen.data.Booking;

/**
 * Created by ruwen on 27.03.17.
 */
public class ExpensiveService {

    public Booking callWebservice() {
        System.out.println("Making expensive call");

        return Booking.builder().trackingNumber("123").build();
    }
}