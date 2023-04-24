package model;


import java.util.LinkedList;

// model.Passenger class to store a linked list of bookings for a passenger
public class Passenger {
    private int passengerId;
    private LinkedList<Booking> bookings;

    public Passenger(int passengerId) {
        this.passengerId = passengerId;
        this.bookings = new LinkedList<Booking>();
    }

    public int getPassengerId() {
        return passengerId;
    }

    public LinkedList<Booking> getBookings() {
        return bookings;
    }
}



