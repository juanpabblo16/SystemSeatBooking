import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

// Passenger class to store a linked list of bookings for a passenger
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



