import java.util.ArrayList;

/**
 * The Car class represents a car in a transportation system.
 * It keeps track of the car's capacity,
 * and the passengers on board.
 */

public class Car implements CarRequirements {
    // Attributes
    private int Capacity;
    public ArrayList<Passenger> Passengers; 

    /**
     * Constructor for the Car class.
     * 
     * @param Capacity The total number of seats available in the car.
     */

    // Constructor for the class
    public Car(int Capacity) {
        this.Capacity = Capacity;
        this.Passengers = new ArrayList<>();  // Initialize the passengers list
    }

    /**
     * Getter for the car's capacity.
     * 
     * @return The total capacity of the car.
     */

    // Getter for capacity
    public int getCapacity() {
        return this.Capacity;
    }

    /**
     * Calculates and returns the remaining seats in the car.
     * 
     * @return The number of remaining seats in the car.
     */

    public int seatsRemaining(){
        return this.Capacity - Passengers.size();
    }
 /**
     * Adds a passenger to the car if there are available seats.
     * 
     * @param p The passenger to be added.
     * @return true if the passenger was successfully added, false otherwise.
     */
    public Boolean addPassenger(Passenger p) {
        if (this.seatsRemaining() > 0) {
            Passengers.add(p);
            return true;
        } else {
            return false;
        }
    }
 /**
     * Removes a passenger from the car.
     * 
     * @param p The passenger to be removed.
     * @return true if the passenger was successfully removed, false if not found.
     */
    
    public Boolean removePassenger(Passenger p) {
        if (Passengers.contains(p)) {
            Passengers.remove(p);  // Remove passenger from the list
            return true;
        } else {
            return false;  // Passenger not found
        }
    }

    /**
     * Prints the manifest of passengers currently in the car.
     * If the car is empty, it will print a message indicating that the car is empty.
     */
    
    public void printManifest() {
        if (Passengers.isEmpty()) {
            System.out.println("This car is empty");
        } 
        else {
            System.out.println("Passengers in the car: " + Passengers);
        }
    }

    // Main method to test the Car class
    public static void main(String[] args) {
        Car myCar = new Car(4);  // Car with 4 seats, initially all seats available
       Passenger myPassenger = new Passenger("Jessica");
        // Add some passengers
        myCar.addPassenger(new Passenger("John"));
        myCar.addPassenger(new Passenger("Alice"));
        myCar.addPassenger(myPassenger);
        myCar.removePassenger(myPassenger);
    

        // Print the manifest
        myCar.printManifest();

        // Remove a passenger
        myCar.removePassenger(myCar.Passengers.get(1));

        // Print the manifest again after removal
        myCar.printManifest();
    }
}
