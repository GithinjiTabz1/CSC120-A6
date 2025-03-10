/**
 * The Engine class represents an engine of a vehicle, such as a car or a train.
 * It manages the fuel type, current fuel level, and maximum fuel capacity of the engine.
 * It allows for fuel management (refueling and fuel consumption) and provides details about the engine.
 */
public class Engine implements EngineRequirements {
    // Attributes
    private FuelType f;  // Fuel type of the engine
    private double currentFuellevel;  // Current fuel level of the engine
    private double maxFuelLevel;  // Maximum fuel capacity of the engine

    /**
     * Constructor for the Engine class.
     * 
     * @param f The fuel type of the engine.
     * @param currentFuellevel The current fuel level of the engine.
     * @param maxFuelLevel The maximum fuel level the engine can hold.
     */
    public Engine(FuelType f, double currentFuellevel) {
        this.f = f;
        this.currentFuellevel = currentFuellevel;
        this.maxFuelLevel = currentFuellevel;
    }

    /**
     * Getter for the fuel type of the engine.
     * 
     * @return The fuel type of the engine.
     */
    public FuelType getFuelType() {
        return this.f;
    }

    /**
     * Getter for the maximum fuel level of the engine.
     * 
     * @return The maximum fuel level of the engine.
     */
    public double getMaxFuel() {
        return this.maxFuelLevel;
    }

    /**
     * Getter for the current fuel level of the engine.
     * 
     * @return The current fuel level of the engine.
     */
    public double getCurrentFuel() {
        return this.currentFuellevel;
    }

    /**
     * Refuels the engine to its maximum fuel level.
     */
    public void refuel() {
        this.currentFuellevel = this.maxFuelLevel;
    }

    /**
     * Simulates the engine going, consuming a fixed amount of fuel.
     * If there is sufficient fuel, the engine will continue running.
     * 
     * @return true if the engine still has fuel to continue running; false if out of fuel.
     */
    public Boolean go() {
        this.currentFuellevel = this.currentFuellevel - 10;
        if (currentFuellevel > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Provides a string representation of the engine's current state.
     * 
     * @return A string describing the engine's fuel type, current fuel level, and maximum fuel capacity.
     */
    public String toString() {
        return ("Engine has Fuel type: " + this.f + " current fuel level " + this.currentFuellevel + " and this max fuel level " + this.maxFuelLevel);
    }

    /**
     * Main method to test the functionality of the Engine class.
     * It demonstrates refueling, consuming fuel, and displaying engine state.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Engine myEngine = new Engine(FuelType.ELECTRIC, 100.0);
        while (myEngine.go()) {
            System.out.println("Choo choo!");
        }
        System.out.println("Out of fuel.");
    }
}
