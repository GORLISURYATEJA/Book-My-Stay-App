import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Deluxe"));
        bookingQueue.add(new Reservation("Bob", "Suite"));
        bookingQueue.add(new Reservation("Charlie", "Standard"));
        bookingQueue.add(new Reservation("David", "Deluxe"));

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);

        Map<String, Set<String>> allocatedRooms = new HashMap<>();
        allocatedRooms.put("Standard", new HashSet<>());
        allocatedRooms.put("Deluxe", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        Set<String> usedRoomIds = new HashSet<>();

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            int available = inventory.getOrDefault(r.roomType, 0);

            if (available > 0) {
                String roomId;
                do {
                    roomId = r.roomType.substring(0, 1).toUpperCase() + (int)(Math.random() * 1000);
                } while (usedRoomIds.contains(roomId));

                usedRoomIds.add(roomId);
                allocatedRooms.get(r.roomType).add(roomId);
                inventory.put(r.roomType, available - 1);

                System.out.println("Reservation Confirmed: " + r.guestName + " -> Room " + roomId + " (" + r.roomType + ")");
            } else {
                System.out.println("Reservation Failed: " + r.guestName + " (" + r.roomType + " not available)");
            }
        }

        System.out.println("\nAllocated Rooms:");
        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + ": " + allocatedRooms.get(type));
        }
    }
}