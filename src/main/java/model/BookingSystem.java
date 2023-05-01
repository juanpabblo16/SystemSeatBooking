package model;


import org.jetbrains.annotations.NotNull;
import struture.MyHashMap;
import struture.MyMap;
import struture.MyPriorityQueue;

import java.util.*;

// model.BookingSystem class to manage the bookings using hash tables
public class BookingSystem {
    private MyHashMap<String, Seat> seatMap;
    private MyHashMap<Integer, Passenger> passengerMap;

    public BookingSystem() {
        this.seatMap = new MyHashMap<String, Seat>();
        this.passengerMap = new MyHashMap<Integer, Passenger>();
    }

    // Method to book a seat for a passenger
    public static void bookSeat(MyMap<String, Boolean> firstClass, MyMap<String, Boolean> businessClass, Map<String, PriorityQueue<Date>> economyClass, long travelDate, String seatNumber, SeatType seatClass) {
        // Check if seat is already booked
        boolean found = false;
        if (seatClass == SeatType.FIRST_CLASS && firstClass.containsKey(seatNumber) && !firstClass.get(seatNumber)) {
            found = true;
        } else if (seatClass == SeatType.BUSINESS_CLASS && businessClass.containsKey(seatNumber) && !businessClass.get(seatNumber)) {
            found = true;
        } else if (seatClass == SeatType.ECONOMY_CLASS && economyClass.containsKey(seatNumber) && !economyClass.get(seatNumber).isEmpty()) {
            found = true;
        }
        if (found) {
            System.out.println("Seat " + seatNumber + " is already booked for the travel date " + new Date(travelDate));
        } else {
            if (seatClass == SeatType.FIRST_CLASS) {
                firstClass.put(seatNumber, false);
                System.out.println("model.Seat " + seatNumber + " in First Class is booked for the travel date " + new Date(travelDate));
            } else if (seatClass == SeatType.BUSINESS_CLASS) {
                businessClass.put(seatNumber, false);
                System.out.println("model.Seat " + seatNumber + " in Business Class is booked for the travel date " + new Date(travelDate));
            } else if (seatClass == SeatType.ECONOMY_CLASS) {
                PriorityQueue<Date> dates = economyClass.getOrDefault(seatNumber, new PriorityQueue<Date>());
                dates.add(new Date(travelDate));
                economyClass.put(seatNumber, dates);
                System.out.println("model.Seat " + seatNumber + " in Economy Class is booked for the travel date " + new Date(travelDate));
            }
        }
    }

    public static void displayCurrentReservations(@NotNull Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
                                                  Map<String, PriorityQueue<Date>> economyClass) {
        // Print header
        System.out.println("CURRENT RESERVATIONS\n");
        System.out.println("First Class:");
        for (Map.Entry<String, Boolean> entry : firstClass.entrySet()) {
            String seat = entry.getKey();
            boolean booked = entry.getValue();
            System.out.print(seat + ": ");
            if (booked) {
                System.out.println("Available");
            } else {
                System.out.println("Booked");
            }
        }
        System.out.println("\nBusiness Class:");
        for (Map.Entry<String, Boolean> entry : businessClass.entrySet()) {
            String seat = entry.getKey();
            boolean booked = entry.getValue();
            System.out.print(seat + ": ");
            if (booked) {
                System.out.println("Available");
            } else {
                System.out.println("Booked");
            }
        }
        System.out.println("\nEconomy Class:");
        for (Map.Entry<String, PriorityQueue<Date>> entry : economyClass.entrySet()) {
            String seat = entry.getKey();
            PriorityQueue<Date> bookings = entry.getValue();
            System.out.print(seat + ": ");
            if (bookings.isEmpty()) {
                System.out.println("Available");
            } else {
                System.out.print("Booked by passengers ");
                Iterator<Date> iterator = bookings.iterator();
                while (iterator.hasNext()) {
                    System.out.print(iterator.next().getTime() + " ");
                }
                System.out.println();
            }
        }
    }

    public static void initializeSeats(Map<String, Boolean> firstClass,
                                       Map<String, Boolean> businessClass,
                                       Map<String, PriorityQueue<Date>> economyClass) {
        // First class
        for (int i = 1; i <= 5; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String seatNumber = i + "" + j;
                firstClass.put(seatNumber, false);
            }
        }

        // Business class
        for (int i = 6; i <= 15; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String seatNumber = i + "" + j;
                businessClass.put(seatNumber, false);
            }
        }

        // Economy class
        for (int i = 16; i <= 30; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String seatNumber = i + "" + j;
                economyClass.put(seatNumber, new PriorityQueue<Date>());
            }
        }
    }



    // Method to cancel a booking for a passenger
    public static boolean cancelBooking(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
                                        Map<String, PriorityQueue<Date>> economyClass, int passengerId, String seat) {
        // Check if seat exists and is booked by the passenger
        boolean found = false;
        if (seat.charAt(0) == '1' && !firstClass.containsKey(seat)) {
            System.out.println("Cancellation failed: model.Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '2' && !businessClass.containsKey(seat)) {
            System.out.println("Cancellation failed: model.Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '3' && !economyClass.containsKey(seat)) {
            System.out.println("Cancellation failed: model.Seat " + seat + " does not exist");
        } else {
            if (seat.charAt(0) == '1' && firstClass.get(seat)) {
                System.out.println("Cancellation failed: model.Seat " + seat + " is not booked");
            } else if (seat.charAt(0) == '2' && businessClass.get(seat)) {
                System.out.println("Cancellation failed: model.Seat " + seat + " is not booked");
            } else if (seat.charAt(0) == '3' && economyClass.get(seat).isEmpty()) {
                System.out.println("Cancellation failed: model.Seat " + seat + " is not booked");
            } else {
                // Cancel the booking
                if (seat.charAt(0) == '1') {
                    firstClass.put(seat, true);
                } else if (seat.charAt(0) == '2') {
                    businessClass.put(seat, true);
                } else if (seat.charAt(0) == '3') {
                    PriorityQueue<Date> dates = economyClass.get(seat);
                    Iterator<Date> iterator = dates.iterator();
                    while (iterator.hasNext()) {
                        Date next = iterator.next();
                        if (next.getTime() == passengerId) {
                            iterator.remove();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Cancellation failed: model.Passenger " + passengerId + " did not book seat " + seat);
                    }
                }
                if (found) {
                    // Print confirmation message
                    System.out.println("model.Booking cancelled for passenger " + passengerId + " on seat " + seat);
                }
            }
        }
        return found;
    }


    // Helper method to remove a booking from a passenger's list of bookings
    private void removeBookingFromPassenger( Booking booking) {
        Passenger passenger = passengerMap.get(booking.getPassengerId());
        if (passenger != null) {
            passenger.getBookings().remove(booking);
        }
    }
}

