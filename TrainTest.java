import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class TrainTest {

    // Engine Tests
    @Test
    public void testEngineConstructor() {
        Engine engine = new Engine(FuelType.STEAM, 100);
        assertEquals("Fuel type should be STEAM", FuelType.STEAM, engine.getFuelType());
        assertEquals("Initial fuel should be 100", 100.0, engine.getCurrentFuel(), 0.001);   
    }

    @Test
    public void testEngineGo() {
        Engine engine = new Engine(FuelType.STEAM, 100);
        engine.go();
        assertTrue("Fuel should decrease after go()", engine.getCurrentFuel() < 100); 
    }

    // Car Tests
    @Test
    public void testCarAddPassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Alice");
        assertTrue("Should be able to add passenger when car has space", car.addPassenger(p));
        assertEquals("Car should have 2 seats remaining after adding passenger", 2, car.seatsRemaining());
    }

    @Test
    public void testCarRemovePassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Bob");
        car.addPassenger(p);
        assertTrue("Should be able to remove existing passenger", car.removePassenger(p));
        assertEquals("Car should have 3 seats remaining after removing passenger", 3, car.seatsRemaining());
    }

    @Test
    public void testCarAddPassengerFull() {
        Car car = new Car(1);
        Passenger p1 = new Passenger("Charlie");
        Passenger p2 = new Passenger("David");
        car.addPassenger(p1);
        assertFalse("Should not be able to add passenger when car is full", car.addPassenger(p2));
        assertEquals("Car should still have 0 seats remaining", 0, car.seatsRemaining());
    }

    @Test
    public void testCarRemoveNonExistentPassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Eve");
        assertFalse("Should not be able to remove non-existent passenger", car.removePassenger(p));
        assertEquals("Car should still have 3 seats remaining", 3, car.seatsRemaining());
    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        Car car = new Car(2);
        Passenger p = new Passenger("Frank");
        p.boardCar(car);  
        assertEquals("Car should have 1 passenger after boarding", 1, car.getCapacity()-car.seatsRemaining());
    }

    @Test
    public void testPassengerBoardCarFull() {
        Car car = new Car(1);
        Passenger p1 = new Passenger("Grace");
        Passenger p2 = new Passenger("Henry");
        car.addPassenger(p1);
        p2.boardCar(car); 
        assertEquals("Car should still have only one passenger", 1, car.getCapacity()-car.seatsRemaining());
    }

    // Train Tests
    @Test
    public void testTrainConstructor() {
        Train train = new Train(FuelType.ELECTRIC, 100.00, 5, 100);
        //  Check the number of cars
        assertNotNull("First car should exist", train.getCar(0));
        assertNotNull("Fifth car should exist", train.getCar(4));
        assertNull("No 6th car should exist", train.getCar(5));

        // Check that each car has the correct capacity
        for (int i = 0; i < 5; i++) {
            Car car = train.getCar(i);
            assertEquals("Car " + i + " should have capacity 100", 100, car.getCapacity());
        }
    }

    @Test
    public void testTrainPassengerCount() {
        Train train = new Train(FuelType.ELECTRIC, 100.00, 5, 100);
        // Add passengers to different cars
        train.getCar(0).addPassenger(new Passenger("Ivy"));
        train.getCar(2).addPassenger(new Passenger("Jack"));
        train.getCar(4).addPassenger(new Passenger("Kelly"));

        int totalPassengers = 0;
        for (int i = 0; i < 5; i++) {
            Car car = train.getCar(i);
            totalPassengers += car.getCapacity() - car.seatsRemaining();
        }

        assertEquals("Total passengers should be 3", 3, totalPassengers);
    }

    @Test
    public void testTrainGetCar() {
        Train train = new Train(FuelType.ELECTRIC, 100.00, 5, 100);
        assertNotNull("First car should exist", train.getCar(0));
        assertNotNull("Fifth car should exist", train.getCar(4));
        assertNull("No 6th car should exist", train.getCar(5));
    }

    @Test
    public void testTrainPrintManifest() {
        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Create train and add passengers
        Train train = new Train(FuelType.ELECTRIC, 100.00, 5, 100);
        train.getCar(0).addPassenger(new Passenger("Liam"));
        train.getCar(1).addPassenger(new Passenger("Mia"));
        train.getCar(3).addPassenger(new Passenger("Noah"));

        // Call the print method
        train.printManifest();

        // Restore original System.out
        System.setOut(originalOut);

        // Get the output and test it
        String printedOutput = outContent.toString();

        assertTrue("Manifest should contain 'Liam'", printedOutput.contains("Liam"));
        assertTrue("Manifest should contain 'Mia'", printedOutput.contains("Mia"));
        assertTrue("Manifest should contain 'Noah'", printedOutput.contains("Noah"));

        // Check that it mentions each car
        assertTrue("Manifest should mention 'Car 1'", printedOutput.contains("Car 1"));
        assertTrue("Manifest should mention 'Car 2'", printedOutput.contains("Car 2"));
        assertTrue("Manifest should mention 'Car 4'", printedOutput.contains("Car 4"));
    }
}
