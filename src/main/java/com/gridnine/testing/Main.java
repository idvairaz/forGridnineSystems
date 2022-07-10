package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.*;

/**
 * program for filtering flights according to specified conditions
 */
public class Main {
    public static void main(String[] args) {

        FlightFilterImpl flightFilterImpl = new FlightFilter();

        LocalDateTime timeNow = LocalDateTime.now();
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("===========all flights==============");
        System.out.println(flights);
        System.out.println("==========departureUntilNow=========");
        System.out.println(flightFilterImpl.departureUntilNow(flights, timeNow));
        System.out.println("==========arrivalLessThanDeparture=========");
        System.out.println(flightFilterImpl.arrivalLessThanDeparture(flights));
        System.out.println("=============landMoreThanTwoHours =======");
        System.out.println(flightFilterImpl.landMoreThanTwoHours(flights));

    }
}
