import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing an EV Charging Station
class ChargingStation {
    private String name;
    private String location;
    private boolean isAvailable;

    public ChargingStation(String name, String location, boolean isAvailable) {
        this.name = name;
        this.location = location;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookSlot() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Slot booked successfully at " + name);
        } else {
            System.out.println("Slot not available at " + name);
        }
    }
}

// Main class
public class EVChargingSystem {
    private static List<ChargingStation> stations = new ArrayList<>();

    public static void main(String[] args) {
        // Sample data: Add existing charging stations
        stations.add(new ChargingStation("1.MVP", "Downtown", true));
        stations.add(new ChargingStation("2.Gajuwaka", "Uptown", true));
        stations.add(new ChargingStation("3.Madhilapalem", "Midtown", true));
        stations.add(new ChargingStation("4.Madhurwada", "Suburban", true));
        stations.add(new ChargingStation("5.RK Beach", "Seaside", true));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("\n--- EV Charging System ---");
            System.out.println("1. Display all stations");
            System.out.println("2. Book a charging slot");
            System.out.println("3. Add a new station");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Handle invalid input for menu choice
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (choice) {
                case 1:
                    displayStations();
                    break;
                case 2:
                    System.out.print("\nEnter the station name to book a slot: ");
                    String stationName = scanner.nextLine();
                    bookChargingSlot(stationName);
                    break;
                case 3:
                    addNewStation(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    // Display all charging stations
    private static void displayStations() {
        System.out.println("\nAvailable Charging Stations:");
        for (ChargingStation station : stations) {
            System.out.println("Name: " + station.getName() + ", Location: " + station.getLocation() +
                    ", Available: " + (station.isAvailable() ? "Yes" : "No"));
        }
    }

    // Book a charging slot by station name
    private static void bookChargingSlot(String stationName) {
        for (ChargingStation station : stations) {
            if (station.getName().equalsIgnoreCase(stationName)) {
                station.bookSlot();
                return;
            }
        }
        System.out.println("Station not found.");
    }

    // Add a new charging station
    private static void addNewStation(Scanner scanner) {
        System.out.print("\nEnter the station name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the station location: ");
        String location = scanner.nextLine();
        System.out.print("Is the station available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        stations.add(new ChargingStation(name, location, isAvailable));
        System.out.println("New station added successfully.");
    }
}
