package model;


import java.util.LinkedList;
import java.util.PriorityQueue;

// model.Seat class to store a linked list or priority queue of bookings for a seat, depending on the seat type
public class Seat {
    private String seatNumber;
    private SeatType seatType;
    private Object bookings; // can be a LinkedList<model.Booking> or a PriorityQueue<model.Booking> depending on the seat type

    public Seat(String seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        if (seatType == SeatType.FIRST_CLASS) {
            this.bookings = new PriorityQueue<Booking>((b1, b2) -> b1.getDate().compareTo(b2.getDate())); // prioritize by date
        } else {
            this.bookings = new LinkedList<Booking>();
        }

    }



    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Object getBookings() {
        return bookings;
    }
}

