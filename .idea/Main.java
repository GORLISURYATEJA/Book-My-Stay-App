import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    int nights;

    Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String toString() {
        return "Guest: " + guestName + ", Room: " + roomType + ", Nights: " + nights;
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        Queue<Reservation> bookingQueue = new LinkedList<>();

        Reservation r1 = new Reservation("Alice", "Deluxe", 2);
        Reservation r2 = new Reservation("Bob", "Suite", 3);
        Reservation r3 = new Reservation("Charlie", "Standard", 1);

        bookingQueue.add(r1);
        bookingQueue.add(r2);
        bookingQueue.add(r3);

        System.out.println("Booking Requests in Queue:");

        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }
    }
}