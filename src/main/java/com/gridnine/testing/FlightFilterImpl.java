package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

/**
 * filter interface
 * @author Razumova Irina
 * @see FlightFilter
 */
public interface FlightFilterImpl {
    List<Flight> departureUntilNow(List<Flight> list, LocalDateTime timeNow);
    List<Flight> arrivalLessThanDeparture(List<Flight> list);
    List<Flight> landMoreThanTwoHours(List<Flight> list);
}
