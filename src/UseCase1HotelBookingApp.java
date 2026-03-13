import java.util.ArrayList;

/**
 * Hotel Booking System - Application Entry Point
 */

public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        // -------- Use Case 1 --------
        System.out.println("===============================");
        System.out.println(" Welcome to Hotel Booking System ");
        System.out.println(" Version : v1.0 ");
        System.out.println("===============================");

        // -------- Use Case 2 : Room Inventory --------
        ArrayList<String> rooms = new ArrayList<>();

        rooms.add("Room101");
        rooms.add("Room102");
        rooms.add("Room103");

        System.out.println("Available Rooms:");

        for(String room : rooms){
            System.out.println(room);
        }

    }
}