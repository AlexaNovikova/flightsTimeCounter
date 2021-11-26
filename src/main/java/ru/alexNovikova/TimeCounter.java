package ru.alexNovikova;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class TimeCounter {

    public static void main(String[] args) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (TimeCounter.class.getClassLoader().getResourceAsStream("tickets.json")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(sb.toString());
            JSONArray ticketsArray = (JSONArray) jsonObject.get("tickets");
            Iterator<Object> iterator = ticketsArray.iterator();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            int stops;
            int price;
            Date departureDateTime;
            Date arrivalDateTime;
            while (iterator.hasNext()) {
                JSONObject ticketInfo = (JSONObject) iterator.next();
                String origin = (String) ticketInfo.get("origin");
                String originName = (String) ticketInfo.get("origin_name");
                String destination = (String) ticketInfo.get("destination");
                String destinationName = (String) ticketInfo.get("destination_name");
                String departureDate = (String) ticketInfo.get("departure_date");
                String departureTime = (String) ticketInfo.get("departure_time");
                departureDateTime = simpleDateFormat.parse(departureDate + " " + departureTime);
                String arrivalDate = (String) ticketInfo.get("arrival_date");
                String arrivalTime = (String) ticketInfo.get("arrival_time");
                arrivalDateTime = simpleDateFormat.parse(arrivalDate + " " + arrivalTime);
                String carrier = (String) ticketInfo.get("carrier");
                stops = (int) (long) ticketInfo.get("stops");
                price = (int) (long) ticketInfo.get("price");
                Ticket ticket = new Ticket(origin, originName, destination, destinationName, departureDateTime, arrivalDateTime,
                        carrier, stops, price);
                tickets.add(ticket);
            }


            ArrayList<Long> flightsDuration = new ArrayList<>();
            for (Ticket ticket : tickets) {
                flightsDuration.add(ticket.flightDuration());
            }

            LocalTime averageDuration = LocalTime.ofSecondOfDay(averageDuration(flightsDuration));
            String average = "Average flight duration: " + averageDuration.getHour() + " hours and " + averageDuration.getMinute() + " minutes.";
            System.out.println(average);
            LocalTime percentileDuration = LocalTime.ofSecondOfDay(percentile(flightsDuration, 90));
            String percentile = "90 percentile: " + percentileDuration.getHour() + " hours and " + percentileDuration.getMinute() + " minutes.";
            System.out.println(percentile);
           try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt", false))) {
               bufferedWriter.write(average);
               bufferedWriter.write("\n");
               bufferedWriter.write(percentile);
           }
            System.out.println("Results are written in file: output.txt");
        } catch (ParseException | java.text.ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static long percentile(List<Long> duration, double percentile) {
        Collections.sort(duration);
        int index = (int) Math.ceil(percentile / 100.0 * duration.size());
        return duration.get(index - 1);
    }

    public static long averageDuration(List<Long> duration) {
        long sum = 0;
        for (Long l : duration) {
            sum += l;
        }
        return sum / duration.size();
    }
}
