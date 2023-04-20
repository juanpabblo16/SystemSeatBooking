import java.util.*;

// BookingSystem class to manage the bookings using hash tables
public class BookingSystem {
    private HashMap<String, Seat> seatMap;
    private HashMap<Integer, Passenger> passengerMap;

    public BookingSystem() {
        this.seatMap = new HashMap<String, Seat>();
        this.passengerMap = new HashMap<Integer, Passenger>();
    }

    // Method to book a seat for a passenger
    public void bookSeat(int passengerId, String seatNumber, Date date) {
        Seat seat = seatMap.get(seatNumber);
        if (seat != null) {
            Booking booking = new Booking(passengerId, seatNumber, date);
            if (seat.getSeatType() == SeatType.FIRST_CLASS) {
                PriorityQueue<Booking> bookings = (PriorityQueue<Booking>) seat.getBookings();
                bookings.add(booking);
            } else {
                LinkedList<Booking> bookings = (LinkedList<Booking>) seat.getBookings();
                bookings.add(booking);
            }

            Passenger passenger = passengerMap.get(passengerId);
            if (passenger == null) {
                passenger = new Passenger(passengerId);
                passengerMap.put(passengerId, passenger);
            }
            passenger.getBookings().add(booking);

            System.out.println("Booking successful: Passenger " + passengerId + " booked seat " + seatNumber);
        } else {
            System.out.println("Booking failed: Seat " + seatNumber + " does not exist");
        }
    }

    public static void bookSeat(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
                                Map<String, PriorityQueue<Date>> economyClass, int passengerId, String seat, Date date) {
        // Check if seat exists and is available in the corresponding class
        if (seat.charAt(0) == '1' && !firstClass.containsKey(seat)) {
            System.out.println("Booking failed: Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '2' && !businessClass.containsKey(seat)) {
            System.out.println("Booking failed: Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '3' && !economyClass.containsKey(seat)) {
            System.out.println("Booking failed: Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '1' && !firstClass.get(seat)) {
            System.out.println("Booking failed: Seat " + seat + " is already booked");
        } else if (seat.charAt(0) == '2' && !businessClass.get(seat)) {
            System.out.println("Booking failed: Seat " + seat + " is already booked");
        } else if (seat.charAt(0) == '3' && !economyClass.get(seat).isEmpty() &&
                date.compareTo(economyClass.get(seat).peek()) >= 0) {
            System.out.println("Booking failed: Seat " + seat + " is already booked for a later date");
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
            System.out.println("Seat " + seat + " booked for passenger " + passengerId);
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
    public static void cancelBooking(Map<String, Boolean> firstClass, Map<String, Boolean> businessClass,
                                     Map<String, PriorityQueue<Date>> economyClass, int passengerId, String seat) {
        // Check if seat exists and is booked by the passenger
        boolean found = false;
        if (seat.charAt(0) == '1' && !firstClass.containsKey(seat)) {
            System.out.println("Cancellation failed: Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '2' && !businessClass.containsKey(seat)) {
            System.out.println("Cancellation failed: Seat " + seat + " does not exist");
        } else if (seat.charAt(0) == '3' && !economyClass.containsKey(seat)) {
            System.out.println("Cancellation failed: Seat " + seat + " does not exist");
        } else {
            if (seat.charAt(0) == '1' && firstClass.get(seat)) {
                System.out.println("Cancellation failed: Seat " + seat + " is not booked");
            } else if (seat.charAt(0) == '2' && businessClass.get(seat)) {
                System.out.println("Cancellation failed: Seat " + seat + " is not booked");
            } else if (seat.charAt(0) == '3' && economyClass.get(seat).isEmpty()) {
                System.out.println("Cancellation failed: Seat " + seat + " is not booked");
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
                        System.out.println("Cancellation failed: Passenger " + passengerId + " did not book seat " + seat);
                    }
                }
                if (found) {
                    // Print confirmation message
                    System.out.println("Booking cancelled for passenger " + passengerId + " on seat " + seat);
                }
            }
        }
    }

    // Helper method to remove a booking from a passenger's list of bookings
    private void removeBookingFromPassenger(Booking booking) {
        Passenger passenger = passengerMap.get(booking.getPassengerId());
        if (passenger != null) {
            passenger.getBookings().remove(booking);
        }
    }
}

