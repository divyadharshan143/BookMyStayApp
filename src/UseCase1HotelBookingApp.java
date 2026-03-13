import java.io.*;
import java.util.*;

/**
 * =========================================================
 * CLASS - RoomInventory
 * =========================================================
 */

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {

        roomAvailability = new HashMap<>();

        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * =========================================================
 * CLASS - FilePersistenceService
 * =========================================================
 * Use Case 12: Data Persistence & System Recovery
 */

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {

            for (Map.Entry<String, Integer> entry :
                    inventory.getRoomAvailability().entrySet()) {

                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {

            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {

                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.updateAvailability(roomType, count);
                }
            }

            System.out.println("Inventory loaded successfully.");

        } catch (IOException e) {

            System.out.println("Error loading inventory.");
        }
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
        System.out.println(" Version : v12.0 ");
        System.out.println("=================================");

        System.out.println("\nSystem Recovery\n");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService =
                new FilePersistenceService();

        String filePath = "inventory.txt";

        // Load saved inventory
        persistenceService.loadInventory(inventory, filePath);

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry :
                inventory.getRoomAvailability().entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save inventory before shutdown
        persistenceService.saveInventory(inventory, filePath);
    }
}