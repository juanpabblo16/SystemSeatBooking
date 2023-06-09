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
        }
    }
}
