package com.ruwen.service;

import com.ruwen.data.Booking;
import com.ruwen.data.enums.ModeOfTransport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ruwen on 24.03.17.
 */
public class FooService {

    public void printFoo() {
        System.out.println("foo");
    }

    public Booking findBooking() {
        return Booking.builder()
                .modeOfTransport(ModeOfTransport.AIR)
                .trackingNumber("1234")
                .build();

    }

    public String findEtaForBooking(String trackingNumber) {
        return LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public String findStringBy2Parameters(String one, String two) {
        return "here is your string";
    }
}
