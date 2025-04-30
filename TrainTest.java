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
        assertEquals("Initial fuel should be 100", 100, engine.getCurrentFuel());   
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
        Passenger p = new Passenger("Tabz");
        car.addPassenger(p);
        car.removePassenger(p);
        assertEquals("Car should have 0 passengers after removal", 0, car.getCapacity());
    }

    @Test
    public void testCarRemovePassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Tabz");
        car.addPassenger(p);
        car.removePassenger(p);
        assertEquals("Car should have 0 passengers after removal", 0, car.getCapacity());
    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        Car car = new Car(2);
        Passenger p = new Passenger("Githinji");
        p.boardCar(car);  
        assertEquals("Car should have 1 passenger after boarding", 1, car.getCapacity());
    }

    @Test
    public void testPassengerBoardCarFull() {
        Car car = new Car(1);
        Passenger p1 = new Passenger("Tabz");
        Passenger p2 = new Passenger("Githinji");

        car.addPassenger(p1);
        p2.boardCar(car); 

        // Assert that p2 did not get added to the car
        assertEquals("Car should still have only one passenger", 1, car.getCapacity());
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
        train.getCar(0).addPassenger(new Passenger("Clare"));
        train.getCar(2).addPassenger(new Passenger("Moraa"));
        train.getCar(4).addPassenger(new Passenger("Mutiso"));

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
        train.getCar(0).addPassenger(new Passenger("Tabz"));
        train.getCar(1).addPassenger(new Passenger("Githinji"));
        train.getCar(3).addPassenger(new Passenger("Njeri"));

        // Call the print method
        train.printManifest();

        // Restore original System.out
        System.setOut(originalOut);

        // Get the output and test it
        String printedOutput = outContent.toString();

        assertTrue("Manifest should contain 'Tabz'", printedOutput.contains("Tabz"));
        assertTrue("Manifest should contain 'Githinji'", printedOutput.contains("Githinji"));
        assertTrue("Manifest should contain 'Njeri'", printedOutput.contains("Njeri"));

        // Check that it mentions each car
        assertTrue("Manifest should mention 'Car 0'", printedOutput.contains("Car 0"));
        assertTrue("Manifest should mention 'Car 1'", printedOutput.contains("Car 1"));
        assertTrue("Manifest should mention 'Car 3'", printedOutput.contains("Car 3"));
    }
}
