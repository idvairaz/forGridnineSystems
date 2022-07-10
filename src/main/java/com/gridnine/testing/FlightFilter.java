package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * this class implements the filter interface
 * @author Razumova Irina
 * @see FlightFilterImpl
 */

public class FlightFilter implements FlightFilterImpl {

    /**
     * this method rejects flights with a departure earlier than the current date
     * @param list  list flights
     * @param timeNow current date
     * @return  filtered list
     */

    @Override
    public List<Flight> departureUntilNow(List<Flight> list, LocalDateTime timeNow) {
        long timeNowEpoch = timeNow.toEpochSecond(ZoneOffset.UTC);
        List<Flight> res = new ArrayList<>(list);
        Iterator i = res.iterator();
        while (i.hasNext()){
            Flight flight = (Flight) i.next();
            List<Segment> segments = flight.getSegments();
            boolean flag = false;
            for (Segment segment : segments){
                if(segment.getDepartureDate().toEpochSecond(ZoneOffset.UTC) < timeNowEpoch){
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("This flight will be delete : " + flight);
                i.remove();
            }
        }
        return res;
    }

    /**
     * this method rejects flights with an arrival date earlier than the departure date
     * @param list list flights
     * @return filtered list
     */

    @Override
    public List<Flight> arrivalLessThanDeparture(List<Flight> list) {
        List<Flight> res = new ArrayList<>(list);
        Iterator i = res.iterator();
        while (i.hasNext()){
            Flight flight = (Flight) i.next();
            List<Segment> segments = flight.getSegments();
            boolean flag = false;
            for (Segment segment : segments){
                if(segment.getArrivalDate().toEpochSecond(ZoneOffset.UTC) < segment.getDepartureDate().toEpochSecond(ZoneOffset.UTC)){
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("This flight will be delete : " + flight);
                i.remove();
            }
        }
        return res;
    }

    /**
     * this method rejects flights with more than two hours on the ground
     * @param list list flights
     * @return filtered list
     */
    @Override
    public List<Flight> landMoreThanTwoHours(List<Flight> list) {
        final int twoHoursInSec = 7200;
        List<Flight> res = new ArrayList<>(list);
        Iterator i = res.iterator();
        while (i.hasNext()){
            Flight flight = (Flight) i.next();
            List<Segment> segments = new ArrayList<>();
            segments = flight.getSegments();
            Segment[] arr =  segments.toArray(new Segment[0]);
            long count = 0;
            for (int j = 0; j < arr.length - 1; j++){
                count += arr[j+1].getDepartureDate().toEpochSecond(ZoneOffset.UTC) - arr[j].getArrivalDate().toEpochSecond(ZoneOffset.UTC);
            }
            if (count>twoHoursInSec) {
                System.out.println("This flight will be delete : " + flight);
                i.remove();
            }
        }
        return res;
    }
}
