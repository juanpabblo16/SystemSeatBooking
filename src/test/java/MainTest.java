import model.BookingSystem;
import model.SeatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import struture.MyHashMap;
import struture.MyMap;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.PriorityQueue;

public class MainTest {


    @Test
    public void testBookSeat() {
        // Initialize seat maps
        Map<String, Boolean> firstClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, Boolean> businessClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, PriorityQueue<Date>> economyClass = (Map<String, PriorityQueue<Date>>) new MyHashMap<>();
        BookingSystem.initializeSeats(firstClass, businessClass, economyClass);

        // Set up user input for booking a seat
        String input = "1\n1\n1A\nJohn Doe\n1658716800000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Book the seat
        Main.main(new String[0]);

        // Verify that the seat is no longer available
        Assertions.assertFalse(firstClass.get("1A"));
    }

    @Test
    public void testCancelBooking() {
        // Initialize seat maps
        Map<String, Boolean> firstClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, Boolean> businessClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, PriorityQueue<Date>> economyClass = (Map<String, PriorityQueue<Date>>) new MyHashMap<>();
        BookingSystem.initializeSeats(firstClass, businessClass, economyClass);

        // Book a seat
        model.BookingSystem.bookSeat((MyMap<String, Boolean>) firstClass, null, null, new Date().getTime(), "1A", SeatType.FIRST_CLASS);

        // Set up user input for cancelling the booking
        String input = "2\n1\n1A\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Cancel the booking
        Main.main(new String[0]);

        // Verify that the seat is now available
        Assertions.assertTrue(firstClass.get("1A"));
    }

    @Test
    public void testDisplayCurrentReservations() {
        // Initialize seat maps
        Map<String, Boolean> firstClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, Boolean> businessClass = (Map<String, Boolean>) new MyHashMap<>();
        Map<String, PriorityQueue<Date>> economyClass = (Map<String, PriorityQueue<Date>>) new MyHashMap<>();
        BookingSystem.initializeSeats(firstClass, businessClass, economyClass);

        // Book a seat
        model.BookingSystem.bookSeat((MyMap<String, Boolean>) firstClass, null, null, new Date().getTime(), "1A", SeatType.FIRST_CLASS);

        // Set up user input for displaying current reservations
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Display current reservations
        Main.main(new String[0]);


    }
}
