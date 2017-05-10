package com.ruwen.service;

import com.ruwen.data.Booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateService {

    //imagine @Autowired here
    private FooService fooService = new FooService();

    public LocalDate findEtaPlus2Day(Booking booking) {
        String etaForBooking = fooService.findEtaForBooking(booking.getTrackingNumber());

        LocalDate etaDate = LocalDate.parse(etaForBooking, DateTimeFormatter.BASIC_ISO_DATE);
        return etaDate.plusDays(2);
    }

    public Set<List<String>> findMixedBeerBoxes() {
        List beerBoxOne = Arrays.asList("Becks", "Astra");
        List beerBoxTwo = Arrays.asList("Krombacher", "Bit");

        Set beerBoxes = new HashSet();
        beerBoxes.add(beerBoxOne);
        beerBoxes.add(beerBoxTwo);

        return beerBoxes;
    }

}
