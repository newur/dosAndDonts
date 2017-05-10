package com.ruwen;

import com.ruwen.data.Booking;
import com.ruwen.service.DateService;
import com.ruwen.service.FooService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Created by ruwen on 24.03.17.
 */
public class DateServiceTest {

    private FooService fooService;

    private Booking booking = Booking.builder().trackingNumber("1234").build();

    @Test
    public void testFooService() {
        fooService = mock(FooService.class);
        DateService dateService = new DateService();

        LocalDate etaPlus2Day = dateService.findEtaPlus2Day(booking);

        Assertions.assertThat(etaPlus2Day.toString()).contains("2017");
    }

    @Test
    public void someStreamTest() {
        String[] names = {"Torben", null, "Anthony", "Sven", "Uli"};

        // check none matches the given value
        boolean svenNotPresent = Arrays.stream(names)
                .noneMatch("Sven"::equals);

        Assert.assertFalse(svenNotPresent);
    }

    @Test
    public void streamBirthdays() {
        Set<List<String>> birthdays = new DateService().findMixedBeerBoxes();

        birthdays.stream()
                .flatMap(Collection::stream)
                .anyMatch(name -> name.equals("Astra"));
    }

    @Test
    public void testServiceWith2Parameters() {
        when(fooService.findStringBy2Parameters(any(), eq("hello"))).thenReturn("bang");

        String stringBy2Parameters = fooService.findStringBy2Parameters("world", "hello");

        Assertions.assertThat(stringBy2Parameters).isEqualTo("bang");
    }
}
