package ui;

import model.BookingSystem;
import model.SeatType;

import java.util.*;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Map<String, Boolean> firstClass = new HashMap<>();
        Map<String, Boolean> businessClass = new HashMap<>();
        Map<String, PriorityQueue<Date>> economyClass = new HashMap<>();
        BookingSystem.initializeSeats(firstClass, businessClass, economyClass);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Print menu
            System.out.println("What do you want to do?");
            System.out.println("1. Book a seat");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Display current reservations");
            System.out.println("4. Exit");

            // Get user input
            int choice = scanner.nextInt();

            switch (choice) {
                    case 1:
                        // Get booking details from user
                        System.out.println("Enter class (1 for First, 2 for Business, 3 for Economy):");
                        int classChoice = scanner.nextInt();
                        SeatType seatClass = null;
                        if (classChoice == 1) {
                            seatClass = SeatType.FIRST_CLASS;
                        } else if (classChoice == 2) {
                            seatClass = SeatType.BUSINESS_CLASS;
                        } else if (classChoice == 3) {
                            seatClass = SeatType.ECONOMY_CLASS;
                        } else {
                            System.out.println("Invalid class choice!");
                            break;
                        }
                        System.out.println("Enter seat number (e.g. 1A):");
                        String seatNumber = scanner.next();
                        System.out.println("Enter passenger name:");
                        String passengerName = scanner.next();
                        System.out.println("Enter date of travel (in milliseconds):");
                        long travelDate = scanner.nextLong();

                        // Book the seat
                        if (seatClass == SeatType.FIRST_CLASS) {
                            model.BookingSystem.bookSeat(firstClass, null, null, new Date(travelDate).getTime(), seatNumber, seatClass);
                        } else if (seatClass == SeatType.BUSINESS_CLASS) {
                            model.BookingSystem.bookSeat(null, businessClass, null, new Date(travelDate).getTime(), seatNumber, seatClass);
                        } else if (seatClass == SeatType.ECONOMY_CLASS) {
                            model.BookingSystem.bookSeat(null, null, economyClass, new Date(travelDate).getTime(), seatNumber, seatClass);
                        }
                        System.out.println("Seat booked for " + passengerName + "!");

                        break;

                    case 2:
                        // Get cancellation details from user
                        System.out.println("Enter class (1 for First, 2 for Business, 3 for Economy):");
                        int cancelClassChoice = scanner.nextInt();
                        SeatType cancelSeatClass = null;
                        if (cancelClassChoice == 1) {
                            cancelSeatClass = SeatType.FIRST_CLASS;
                        } else if (cancelClassChoice == 2) {
                            cancelSeatClass = SeatType.BUSINESS_CLASS;
                        } else if (cancelClassChoice == 3) {
                            cancelSeatClass = SeatType.ECONOMY_CLASS;
                        } else {
                            System.out.println("Invalid class choice!");
                            break;
                        }
                        System.out.println("Enter seat number (e.g. 1A):");
                        String cancelSeatNumber = scanner.next();

                        // Cancel the booking
                        System.out.println("Enter the user id:");
                        String passengerId = scanner.next();
                        boolean cancellationResult = BookingSystem.cancelBooking(firstClass, businessClass, economyClass, passengerId, cancelSeatClass + cancelSeatNumber);

                        if (cancellationResult) {
                            System.out.println("Booking cancelled!");
                        } else {
                            System.out.println("Cancellation failed!");
                        }
                        break;

                    case 3:
                        // Display current reservations
                        BookingSystem.displayCurrentReservations(firstClass, businessClass, economyClass);
                        break;

                    case 4:
                        // Exit the program
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }


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
