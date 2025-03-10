/**
 * The Passenger class represents a passenger in the system.
 * It allows a passenger to board or get off a car, as well as handle related actions.
 */
public class Passenger implements PassengerRequirements {
    // Attributes
    private String name;

    /**
     * Constructor for the Passenger class.
     * 
     * @param name The name of the passenger.
     */
    public Passenger(String name) {
        this.name = name;
    }

    /**
     * Allows the passenger to board a specific car.
     * It attempts to add the passenger to the car. If successful, a confirmation message is printed.
     * 
     * @param c The car that the passenger is attempting to board.
     */
    public void boardCar(Car c) {
        boolean boarded = c.addPassenger(this); // Call addPassenger from Car
        if (boarded) {
            System.out.println(name + " successfully boarded the car.");
        } else {
            System.out.println("Car is full. " + name + " could not board.");
        }
    }

    /**
     * Allows the passenger to get off a specific car.
     * It attempts to remove the passenger from the car. If successful, a confirmation message is printed.
     * 
     * @param c The car that the passenger is attempting to get off.
     */
    public void getOffCar(Car c) {
        boolean removed = c.removePassenger(this); // Call removePassenger from Car
        if (removed) {
            System.out.println(name + " successfully got off the car.");
        } else {
            System.out.println(name + " was not onboard the car.");
        }
    }
public String toString(){
    return name;
}
    /**
     * Main method to test the functionality of the Passenger class.
     * It demonstrates a passenger boarding and getting off a car.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Car myCar = new Car(4);  // Car with 4 seats, initially all seats available
        Passenger myPassenger = new Passenger("Clare");
        myPassenger.boardCar(myCar);  // Passenger boards the car
        myPassenger.getOffCar(myCar);  // Passenger gets off the car
    }
}
