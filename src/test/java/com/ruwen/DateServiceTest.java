package com.ruwen;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import com.ruwen.data.Booking;
import com.ruwen.service.DateService;
import com.ruwen.service.FooService;

/**
 * Created by ruwen on 24.03.17.
 */
public class DateServiceTest {

    private FooService fooService = mock(FooService.class);

    private static final String TRACKING_NUMBER = "1234";

    private Booking booking;

    @Test
    public void mockPOJOs() {
        // Two lines to mock a getter...
        booking = mock(Booking.class);
        when(this.booking.getTrackingNumber()).thenReturn(TRACKING_NUMBER);

        LocalDate etaPlus2Day = new DateService().findEtaPlus2Day(this.booking);

        assertThat(etaPlus2Day.toString()).contains("2017");
    }

    @Test
    public void mockPOJOs_alternative() {
        // Use a builder if available and consider moving it to a field level
        booking = Booking.builder().trackingNumber("1234").build();

        LocalDate etaPlus2Day = new DateService().findEtaPlus2Day(this.booking);

        assertThat(etaPlus2Day.toString()).contains("2017");
    }

    @Test
    public void checkSomethingIsNotPresentInStream() {
        String[] names = {"Torben", "Anthony", "Sven", "Uli"};

        // '!' can easily be overlooked in a multi-liner
        boolean isSvenAbsent = !Arrays.stream(names)
                // will throw NullPointerException if list contains null values +
                // .filter .findFirst .isPresent is a lot of boilerplate code
                .filter(s -> s.equals("Sven"))
                .findFirst()
                .isPresent();

        Assert.assertFalse(isSvenAbsent);
    }

    @Test
    public void checkSomethingIsNotPresentInStream_alternative() {
        String[] names = {"Torben", null, "Anthony", "Sven", "Uli"};

        boolean isSvenAbsent = Arrays.stream(names)
                // check none matches the given value + null safe
                .noneMatch("Sven"::equals);

        Assert.assertFalse(isSvenAbsent);
    }

    // Could not find the anti pattern anymore, so here directly the "right" way
    @Test
    public void collectionContainsCollection_alternative() {
        Set<List<String>> boxes = new DateService().findMixedBeerBoxes();

        List<String> beers = boxes.stream()
                // flatten the nested collection to get one with all sub elements
                .flatMap(Collection::stream)
                .collect(toList());

        beers.stream().forEach(System.out::println);
    }

    @Test
    public void mockShouldReturnForOneSpecificAndOneArbitraryInput() {
        try {
            // this will not work. Throws InvalidUseOfMatchersException
            when(fooService.findStringBy2Parameters(any(), "hello")).thenReturn("bang");
        } catch (InvalidUseOfMatchersException e) {
            // stack trace explains how to do it. Find an example below
            e.printStackTrace();
        }
    }

    @Test
    public void mockShouldReturnForOneSpecificAndOneArbitraryInput_alternative() {
        // use matchers on both arguments
        when(fooService.findStringBy2Parameters(any(), eq("hello"))).thenReturn("bang");

        String stringBy2Parameters = fooService.findStringBy2Parameters("world", "hello");
        assertThat(stringBy2Parameters).isEqualTo("bang");

        stringBy2Parameters = null;
        stringBy2Parameters = fooService.findStringBy2Parameters("world", "wrongInput");
        assertThat(stringBy2Parameters).isNull();
    }
}
