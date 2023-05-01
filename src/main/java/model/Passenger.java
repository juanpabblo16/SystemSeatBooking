package model;



import struture.MyLinkedList;

import java.util.LinkedList;

// model.Passenger class to store a linked list of bookings for a passenger
public class Passenger {
    private int passengerId;
    private MyLinkedList<Booking> bookings;

    public Passenger(int passengerId) {
        this.passengerId = passengerId;
        this.bookings = new MyLinkedList<Booking>();
    }

    public int getPassengerId() {
        return passengerId;
    }

    public MyLinkedList<Booking> getBookings() {
        return bookings;
    }
}



