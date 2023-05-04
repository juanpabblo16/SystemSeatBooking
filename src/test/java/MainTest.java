import model.BookingSystem;
import model.SeatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import struture.MyHashMap;
import struture.MyMap;
import struture.MyPriorityQueue;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import static org.junit.Assert.*;

class MainTest {

    private final String newLine = System.lineSeparator();
    private MyMap<String, Boolean> firstClass;
    private MyMap<String, Boolean> businessClass;
    private MyMap<String, MyPriorityQueue<Date>> economyClass;

    @BeforeEach
    @Test
    void setUp() {
        firstClass = new MyMap<>();
        businessClass = new MyMap<>();
        economyClass = new MyMap<>();
        for (int i = 1; i <= 5; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String seat = i + String.valueOf(j);
                firstClass.put(seat, true);
                businessClass.put(seat, true);
                economyClass.put(seat, new MyPriorityQueue<>());
            }
        }
    }



    @Test
    void testCancelBooking() {
        BookingSystem.bookSeat(firstClass, null, null, System.currentTimeMillis(), "1A", SeatType.FIRST_CLASS);
        String input = "2" + newLine + "1" + newLine + "1A" + newLine + "1" + newLine + "4" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        assertTrue(firstClass.get("1A"));
    }

    @Test
    void testDisplayCurrentReservations() {
        BookingSystem.bookSeat(firstClass, null, null, System.currentTimeMillis(), "1A", SeatType.FIRST_CLASS);
        BookingSystem.bookSeat(null, businessClass, null, System.currentTimeMillis(), "1B", SeatType.BUSINESS_CLASS);
        BookingSystem.bookSeat(null, null, (Map<String, PriorityQueue<Date>>) economyClass, System.currentTimeMillis(), "1C", SeatType.ECONOMY_CLASS);

        String expectedOutput = "First Class: [1A]" + newLine +
                "Business Class: [1B]" + newLine +
                "Economy Class: {1C=[]}" + newLine;

        String input = "3" + newLine + "4" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        assertEquals(expectedOutput, getConsoleOutput());
    }

    private String getConsoleOutput() {
        Scanner scanner = new Scanner((Readable) System.out);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(newLine);
        }
        return sb.toString();
    }
}
