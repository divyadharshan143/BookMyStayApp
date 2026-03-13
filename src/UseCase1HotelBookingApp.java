import java.util.*;

/**
 * =========================================================
 * CLASS - Reservation
 * =========================================================
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

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
 * CLASS - RoomAllocationService
 * =========================================================
 * Use Case 6: Reservation Confirmation & Room Allocation
 */

class RoomAllocationService {

    private Set<String> allocatedRoomIds;

    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {

        allocatedRoomIds = new HashSet<>();

        assignedRoomsByType = new HashMap<>();

        assignedRoomsByType.put("Single", new HashSet<>());
        assignedRoomsByType.put("Double", new HashSet<>());
        assignedRoomsByType.put("Suite", new HashSet<>());
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) > 0) {

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType.get(roomType).add(roomId);

            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println(
                    "Booking confirmed for Guest: "
                            + reservation.getGuestName()
                            + ", Room ID: "
                            + roomId
            );

        } else {

            System.out.println("No rooms available for " + roomType);
        }
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType.get(roomType).size() + 1;

        return roomType + "-" + count;
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
        System.out.println(" Version : v6.0 ");
        System.out.println("=================================");

        System.out.println("\nRoom Allocation Processing\n");

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService allocationService = new RoomAllocationService();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Single");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        allocationService.allocateRoom(r1, inventory);
        allocationService.allocateRoom(r2, inventory);
        allocationService.allocateRoom(r3, inventory);
    }
}