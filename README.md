# Flight Booking System

This is a simple Flight Booking System implemented in Java using hash tables. The system allows users to book and cancel seats in different classes of an airplane, and also displays the current reservations.
---
### Authors
Juan Pablo Acevedo
---
### Language and IDE
- Java
- IntelliJ IDEA
---
### Prerequisites
Java Development Kit (JDK) version 8 or later
---
### How to run the project
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the Main.java file to execute the program.

When the program is running, you can follow the prompts in the console to book and cancel seats, and display the current reservations. The program uses hash tables to store the reservations for each seat class. The SeatClass enum defines the different classes (First Class, Business Class, and Economy Class), and the seat maps are implemented as hash tables with the seat number as the key and a Boolean value indicating whether the seat is available or booked. For the Economy Class, a priority queue is used to store the booking dates for each seat, so that they can be sorted chronologically.
