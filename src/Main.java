import java.util.*;

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (₹" + cost + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addServices(String reservationId, List<Service> services) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).addAll(services);
    }

    public List<Service> getServices(String reservationId) {
        return reservationServices.getOrDefault(reservationId, Collections.emptyList());
    }

    public double calculateTotalCost(String reservationId) {
        return getServices(reservationId).stream()
                .mapToDouble(Service::getCost)
                .sum();
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservation1 = "RES123";
        String reservation2 = "RES456";

        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa Access", 1500);
        Service airportPickup = new Service("Airport Pickup", 800);

        manager.addServices(reservation1, Arrays.asList(breakfast, spa));
        manager.addServices(reservation2, Arrays.asList(airportPickup));

        System.out.println("Reservation: " + reservation1);
        System.out.println("Selected Services: " + manager.getServices(reservation1));
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation1));

        System.out.println("\nReservation: " + reservation2);
        System.out.println("Selected Services: " + manager.getServices(reservation2));
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation2));
    }
}