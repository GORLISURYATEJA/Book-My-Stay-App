import java.util.*;

class Room {
    private String type;
    private double price;
    private List<String> amenities;

    public Room(String type, double price, List<String> amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAmenities() {
        return amenities;
    }
}

class Inventory {
    private Map<String, Integer> availability;

    public Inventory() {
        availability = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        availability.put(type, count);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public Set<String> getRoomTypes() {
        return availability.keySet();
    }
}

class SearchService {
    private Inventory inventory;
    private Map<String, Room> rooms;

    public SearchService(Inventory inventory, Map<String, Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    public void searchAvailableRooms() {
        for (String type : inventory.getRoomTypes()) {
            int count = inventory.getAvailability(type);
            if (count > 0) {
                Room room = rooms.get(type);
                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: " + room.getPrice());
                System.out.println("Amenities: " + room.getAmenities());
                System.out.println("Available: " + count);
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addRoomType("Single", 3);
        inventory.addRoomType("Double", 0);
        inventory.addRoomType("Suite", 2);

        Map<String, Room> rooms = new HashMap<>();

        rooms.put("Single", new Room("Single", 2000, Arrays.asList("WiFi", "TV")));
        rooms.put("Double", new Room("Double", 3500, Arrays.asList("WiFi", "TV", "Mini Bar")));
        rooms.put("Suite", new Room("Suite", 6000, Arrays.asList("WiFi", "TV", "Mini Bar", "Jacuzzi")));

        SearchService searchService = new SearchService(inventory, rooms);

        System.out.println("Available Rooms:");
        searchService.searchAvailableRooms();
    }
}