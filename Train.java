import java.util.ArrayList;
/**
 * The Train class represents a train consisting of an engine and a list of cars.
 * It provides functionality to manage the train's cars, engine, and passengers.
 */

public class Train implements TrainRequirements {
   
    // Attributes
    private Engine engine;
    private ArrayList<Car> cars;

    /**
     * Constructor for the Train class.
     * 
     * @param fuelType The type of fuel used by the train's engine.
     * @param fuelCapacity The capacity of fuel the train can hold.
     * @param nCars The number of cars in the train.
     * @param passengerCapacity The passenger capacity for each car.
     */

    public Train(FuelType fuelType, double fuelCapacity, int nCars, int passengerCapacity) {
        this.engine =new Engine(fuelType,fuelCapacity);
        this.cars = new ArrayList<>();
        


        // Initialize the cars
        for (int i = 0; i < nCars; i++) {
            Car car = new Car(passengerCapacity);
            cars.add(car);
        }

    }

     /**
     * Accessor method to get the engine of the train.
     * 
     * @return The engine of the train.
     */
    public Engine getEngine() {
        return this.engine;
    }

   /**
     * Accessor method to get a specific car from the train by its index.
     * 
     * @param i The index of the car.
     * @return The car at the specified index, or null if the index is invalid.
     */
    public Car getCar(int i) {
        if (i >= 0 && i < cars.size()) {
            return cars.get(i);
        } else {
            return null; // Return null instead of throwing an exception
        }
    }

 /**
     * Accessor method to get the total maximum capacity of the train across all cars.
     * 
     * @return The total maximum capacity of the train.
     */
    public int getMaxCapacity() {
        int MaxCapacity = 0;
        for (Car car : cars) {
            MaxCapacity += car.getCapacity(); 
        }
        return MaxCapacity;
    }

     /**
     * Accessor method to get the total remaining seats in the train across all cars.
     * 
     * @return The total number of remaining seats in the train.
     */
    public int seatsRemaining() {
        int remainingSeats = 0;
        for (Car car : cars) {
            remainingSeats += car.seatsRemaining();
        }
        return remainingSeats;
    }

    /**
     * Prints the manifest of passengers onboard the train.
     * It lists all passengers in each car.
     */
    public void printManifest() {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Car " + (i + 1) + ":");
            cars.get(i).printManifest();
        }
    }
    
     /**
     * Main method to test the functionality of the Train class.
     * It demonstrates various methods for interacting with the train's cars and engine.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Car myCar = new Car(4);
        Train myTrain = new Train(FuelType.ELECTRIC, 100.00,5,100);  // Car with 4 seats, initially all seats available


        myTrain.getMaxCapacity();
        myTrain.seatsRemaining();
        myTrain.getCar(2);
        myTrain.getEngine();
        myTrain.printManifest();

        
            // Testing getMaxCapacity
            System.out.println("Total Max Capacity of Train: " + myTrain.getMaxCapacity());
        
            // Testing seatsRemaining
            System.out.println("Total Remaining Seats in Train: " + myTrain.seatsRemaining());
        
            // Testing getCar for car at index 2
            Car carAtIndex2 = myTrain.getCar(2); // Get the car at index 2
            if (carAtIndex2 != null) {
                System.out.println("Car at index 2 has " + carAtIndex2.seatsRemaining() + " seats remaining.");
            } else {
                System.out.println("Car at index 2 does not exist.");
            }
        
            // Testing getEngine
            System.out.println("Train Engine Type: " + myTrain.getEngine().getFuelType());
        
            // Testing printManifest to list passengers (assuming Car and printManifest are implemented)
            myTrain.printManifest();
        
    }
}



