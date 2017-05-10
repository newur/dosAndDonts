package com.ruwen;

import com.ruwen.data.Booking;
import com.ruwen.data.enums.ModeOfTransport;
import com.ruwen.service.ExpensiveService;
import com.ruwen.service.FooService;
import com.ruwen.service.OptionalService;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    private OptionalService optionalService = new OptionalService();
    private ExpensiveService expensiveService = new ExpensiveService();

    @Test
    public void optionalTest() {
        ModeOfTransport modeOfTransport = null;

        Optional<Booking> optional = optionalService.findBooking();

        if (optional.isPresent()) {
            modeOfTransport = optional.get().getModeOfTransport();
        } else {
            modeOfTransport = ModeOfTransport.AIR;
        }


//        return modeOfTransport;
    }

    @Test
    public void moreOptional() {
        String bookingString = null;

        Optional<Booking> optional = optionalService.findBooking();

        if (optional.isPresent()) {
            bookingString = optional
                    .map(Booking::toString)
                    .orElse(expensiveService.callWebservice().toString());
        }

        System.out.println(bookingString);
    }

    @Test
    public void betterOptional() {
        optionalService.findBooking()
                .map(Booking::toString)

                .orElseGet(() -> expensiveService.callWebservice().toString());
    }

    @Test
    public void weirdOptionalCreation() {
        Optional<Booking> result;

        FooService fooService = new FooService();
        Booking booking = fooService.findBooking();

        if (booking != null) {
            result = Optional.of(booking);
        } else {
            result = Optional.empty();
        }
    }

    @Test
    public void slightlyLessWeirdOptionalCreation() {
        Optional<Booking> result;

        FooService fooService = new FooService();
        Booking booking = fooService.findBooking();

        result = booking != null ? Optional.of(booking) : Optional.empty();
    }

}
