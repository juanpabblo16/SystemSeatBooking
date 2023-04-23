package model;

import model.Booking;
import structure.MyHashMap;
import structure.MyLinkedList;
import structure.MyPriorityQueue;

import java.util.*;

// model.BookingSystem class to manage the bookings using hash tables
public class BookingSystem {
    private MyHashMap<String, Seat> seatMap1;
    private MyHashMap<Integer, Passenger> passengerMap1;

    public BookingSystem() {
        this.seatMap1 = new MyHashMap<String, Seat>();
        this.passengerMap1 = new MyHashMap<Integer, Passenger>();
    }

    // Method to book a seat for a passenger
    public void bookSeat(int passengerId, String seatNumber, Date date) {
        Seat seat = seatMap1.get(seatNumber);
        if (seat != null) {
            Booking booking = new Booking(passengerId, seatNumber, date);
            if (seat.getSeatType() == SeatType.FIRST_CLASS) {
                MyPriorityQueue<Booking> bookings = (MyPriorityQueue<Booking>) seat.getBookings();
                bookings.add(booking);
            } else {
                MyLinkedList<Booking> bookings = (MyLinkedList<Booking>) seat.getBookings();
                bookings.add(Integer.parseInt(booking.getSeatNumber()),booking);
            }

            Passenger passenger = passengerMap1.get(passengerId);
            if (passenger == null) {
                passenger = new Passenger(passengerId);
                passengerMap1.put(passengerId, passenger);
            }
            passenger.getBookings().add(booking);

            System.out.println("model.Booking successful: model.Passenger " + passengerId + " booked seat " + seatNumber);
        } else {
            System.out.println("model.Booking failed: model.Seat " + seatNumber + " does not exist");
        }
    }

    public static void displayCurrentReservations(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
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

    // Method to cancel a booking for a passenger
    public static void cancelBooking(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
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
    }


    // Helper method to remove a booking from a passenger's list of bookings
    private void removeBookingFromPassenger(Booking booking) {
        Passenger passenger = passengerMap1.get(booking.getPassengerId());
        if (passenger != null) {
            passenger.getBookings().remove(booking);
        }
    }
}

