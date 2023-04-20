package ui;

import model.BookingSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Map<String, Boolean> firstClass = new HashMap<>();
        Map<String, Boolean> businessClass = new HashMap<>();
        Map<String, PriorityQueue<Date>> economyClass = new HashMap<>();

        // Initialize seat maps with all seats available
        for (int i = 1; i <= 5; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String seat = i + String.valueOf(j);
                firstClass.put(seat, true);
                businessClass.put(seat, true);
                economyClass.put(seat, new PriorityQueue<Date>());
            }
        }

        // Book some seats
        bookSeat(firstClass, businessClass, economyClass, 1, "1A", new Date());
        bookSeat(firstClass, businessClass, economyClass, 2, "1B", new Date());
        bookSeat(firstClass, businessClass, economyClass, 3, "2C", new Date());
        bookSeat(firstClass, businessClass, economyClass, 4, "3D", new Date());
        bookSeat(firstClass, businessClass, economyClass, 5, "2F", new Date());
        bookSeat(firstClass, businessClass, economyClass, 6, "5A", new Date());
        bookSeat(firstClass, businessClass, economyClass, 7, "5B", new Date());
        bookSeat(firstClass, businessClass, economyClass, 8, "5C", new Date());
        bookSeat(firstClass, businessClass, economyClass, 9, "6D", new Date());
        bookSeat(firstClass, businessClass, economyClass, 10, "6F", new Date());
        bookSeat(firstClass, businessClass, economyClass, 11, "1C", new Date()); // Should fail - no such seat
        bookSeat(firstClass, businessClass, economyClass, 12, "2A", new Date()); // Should fail - seat already booked

        // Cancel a booking
        BookingSystem.cancelBooking(firstClass, businessClass, economyClass, 2, "1B");

        // Display the current seat map
        BookingSystem.displayCurrentReservations(firstClass, businessClass, economyClass);
    }

    public static void bookSeat(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
                                Map<String, PriorityQueue<Date>> economyClass, int passengerId, String seat, Date date) {
        // Check if seat exists and is available in the corresponding class
        if (seat.charAt(0) == '1' && !firstClass.containsKey(seat)) {
            System.out.println("model.Booking failed: model.Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '2' && !businessClass.containsKey(seat)) {
            System.out.println("model.Booking failed: model.Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '3' && !economyClass.containsKey(seat)) {
            System.out.println("model.Booking failed: model.Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '1' && !firstClass.get(seat)) {
            System.out.println("model.Booking failed: model.Seat " + seat + " is already booked");
        } else if (seat.charAt(0) == '2' && !businessClass.get(seat)) {
            System.out.println("model.Booking failed: model.Seat " + seat + " is already booked");
        } else if (seat.charAt(0) == '3' && !economyClass.get(seat).isEmpty() &&
                date.compareTo(economyClass.get(seat).peek()) >= 0) {
            System.out.println("model.Booking failed: model.Seat " + seat + " is already booked for a later date");
        } else {
            // Book the seat
            if (seat.charAt(0) == '1') {
                firstClass.put(seat, false);
            } else if (seat.charAt(0) == '2') {
                businessClass.put(seat, false);
            } else if (seat.charAt(0) == '3') {
                economyClass.get(seat).offer(date);
            }

            // Print confirmation message
            System.out.println("model.Seat " + seat + " booked for passenger " + passengerId);
        }
    }
}
