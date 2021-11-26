package ru.alexNovikova;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Ticket {

    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private String carrier;
    private int stops;
    private int price;

    public Ticket() {

    }

    public Ticket(String origin, String originName, String destination,
                  String destinationName, Date departureDateTime, Date arrivalDateTime,
                  String carrier, int stops, int price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    public long flightDuration()  {
        long diff = arrivalDateTime.getTime()-departureDateTime.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toSeconds(diff);
        return diffInMinutes;
    }

    @Override
    public String toString() {
        return "ru.alexNovikova.Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
