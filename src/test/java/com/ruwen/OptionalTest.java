package com.ruwen;

import java.util.Optional;

import org.junit.Test;

import com.ruwen.data.Booking;
import com.ruwen.data.enums.ModeOfTransport;
import com.ruwen.service.ExpensiveService;
import com.ruwen.service.FooService;
import com.ruwen.service.OptionalService;

public class OptionalTest {

    private OptionalService optionalService = new OptionalService();
    private ExpensiveService expensiveService = new ExpensiveService();

    @Test
    public void explicitOptionalCheck() {
        ModeOfTransport modeOfTransport;

        Optional<Booking> optional = optionalService.findBooking();

        // check Optional explicitly with .isPresent and then .get() it
        if (optional.isPresent()) {
            modeOfTransport = optional.get().getModeOfTransport();
        } else {
            modeOfTransport = ModeOfTransport.AIR;
        }
        System.out.println(modeOfTransport);
    }

    @Test
    public void explicitOptionalCheck_alternative() {

        // avoids explicit check and intermediate variable
        optionalService.findBooking()
                .map(Booking::getModeOfTransport)
                .ifPresent(System.out::println);
    }

    @Test
    public void expensiveCallsAsOptionalDefault() {
        String bookingString = optionalService.findBooking()
                .map(Booking::toString)
                // operation in .orElse will be executed !!always!!
                // do not make any expensive calls here
                .orElse(expensiveService.callWebservice().toString());

        System.out.println(bookingString);
    }

    @Test
    public void expensiveCallsAsOptionalDefault_alternative() {
        String bookingString = optionalService.findBooking()
                .map(Booking::toString)
                // .orElseGet will only be executed if Optional was empty
                .orElseGet(() -> expensiveService.callWebservice().toString());

        System.out.println(bookingString);
    }

    @Test
    public void weirdOptionalCreation() {
        Optional<Booking> result;

        FooService fooService = new FooService();
        Booking booking = fooService.findBooking();

        // Uahhhh....
        if (booking != null) {
            result = Optional.of(booking);
        } else {
            result = Optional.empty();
        }

        System.out.println(result);
    }

    @Test
    public void weirdOptionalCreation_partTwo() {
        FooService fooService = new FooService();
        Booking booking = fooService.findBooking();

        // "It's a one liner...."
        Optional<Booking> result = booking != null ? Optional.of(booking) : Optional.empty();
        System.out.println(result);
    }

    @Test
    public void weirdOptionalCreation_alternative() {
        FooService fooService = new FooService();

        Optional<Booking> booking = Optional.ofNullable(fooService.findBooking());

        System.out.println(booking);
    }

}
