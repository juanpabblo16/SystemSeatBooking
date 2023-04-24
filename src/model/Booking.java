package model;

import java.util.Date;

// model.Booking class to store the passenger ID and seat number for a booking
public class Booking {
    private int passengerId;
    private String seatNumber;
    private Date date; // optional additional data, such as the date of the flight

    public Booking(int passengerId, String seatNumber, Date date) {
        this.passengerId = passengerId;
        this.seatNumber = seatNumber;
        this.date = date;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Date getDate() {
        return date;
    }
}