import java.util.*;

/**
 * =========================================================
 * CLASS - AddOnService
 * =========================================================
 * Represents an additional service offered by the hotel
 */

class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * =========================================================
 * CLASS - AddOnServiceManager
 * =========================================================
 * Manages add-on services for reservations
 */

class AddOnServiceManager {

    // ReservationID → List of services
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {

            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}

/**
 * =========================================================
 * MAIN CLASS
 * =========================================================
 */

public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version : v7.0 ");
        System.out.println("=================================");

        System.out.println("\nAdd-On Service Selection\n");

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1000);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}