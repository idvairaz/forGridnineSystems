package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTest {
    private  FlightFilterImpl flightFilterImpl;
    List<Flight> flights;
    LocalDateTime now;

    @BeforeEach
    void setUp() {

       flightFilterImpl = new FlightFilter();

        now = LocalDateTime.now();
        LocalDateTime nowPlus2h = now.plusHours(2);
        LocalDateTime nowPlus5h = now.plusHours(5);
        LocalDateTime nowPlus7h = now.plusHours(7);
        LocalDateTime nowPlus8h = now.plusHours(8);
        LocalDateTime nowPlus10h = now.plusHours(10);


        Segment segment1 = new Segment(now, nowPlus2h);
        Segment segment2 = new Segment(nowPlus2h, now);
        Segment segment3 = new Segment(nowPlus5h, nowPlus7h);
        Segment segment4 = new Segment(nowPlus8h, nowPlus10h);


        List<Segment> segmentsA = new ArrayList<>();
        segmentsA.add(segment1);

        List<Segment> segmentsB = new ArrayList<>();
        segmentsB.add(segment2);

        List<Segment> segmentsC = new ArrayList<>();
        segmentsC.add(segment3);
        segmentsC.add(segment4);

        List<Segment> segmentsD = new ArrayList<>();
        segmentsD.add(segment3);

        List<Segment> segmentsE = new ArrayList<>();
        segmentsE.add(segment1);
        segmentsE.add(segment3);


        Flight flight1 = new Flight(segmentsA);
        Flight flight2 = new Flight(segmentsB);
        Flight flight3 = new Flight(segmentsC);
        Flight flight4 = new Flight(segmentsD);
        Flight flight5 = new Flight(segmentsE);

        flights = new ArrayList<>();

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
    }

    @Test
    void departureUntilNowTest() {
        System.out.println("==========Test departureUntilNow=========");
        LocalDateTime testTime = now.plusHours(3);
        Optional<List<Flight>> optionalFlights = ofNullable(flightFilterImpl.departureUntilNow(flights, testTime));
        optionalFlights.ifPresent(filter -> assertEquals(Arrays.asList(flights.get(2), flights.get(3)), optionalFlights.get(), "list not equals"));
//        System.out.println(optionalFlights);
    }

    @Test
    void arrivalLessThanDepartureTest() {
        System.out.println("==========Test arrivalLessThanDeparture=========");
        Optional<List<Flight>> optionalFlights = ofNullable(flightFilterImpl.arrivalLessThanDeparture(flights));
        optionalFlights.ifPresent(filter -> assertEquals(Arrays.asList(flights.get(0), flights.get(2), flights.get(3), flights.get(4)), optionalFlights.get(), "list not equals"));
//        System.out.println(optionalFlights);
    }

    @Test
    void landMoreThanTwoHoursTest() {
        System.out.println("==========Test landMoreThanTwoHours=========");
        Optional<List<Flight>> optionalFlights = ofNullable(flightFilterImpl.landMoreThanTwoHours(flights));
        optionalFlights.ifPresent(filter -> assertEquals(Arrays.asList(flights.get(0), flights.get(1), flights.get(2),flights.get(3)), optionalFlights.get(), "list not equals"));
//        System.out.println(optionalFlights);

    }
}