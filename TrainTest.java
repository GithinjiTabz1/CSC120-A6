import static org.junit.Assert.*;
import org.junit.Test;

public class TrainTest {

    // Engine Tests
    @Test
    public void testEngineConstructor() {
        Engine engine = new Engine(FuelType.DIESEL, 100);
        assertEquals(FuelType.DIESEL, engine.getFuelType());
        assertEquals(100, engine.getFuelLevel());
    }

    @Test
    public void testEngineGo() {
        Engine engine = new Engine(FuelType.DIESEL, 100);
        engine.go();
        assertTrue(engine.getFuelLevel() < 100); 
    }

    }

    // Car Tests
    @Test
    public void testCarAddPassenger() {
        Car car = new Car(3); // Assuming capacity is 3
        Passenger p = new Passenger("Alice");
        assertTrue(car.addPassenger(p));
        assertEquals(1, car.getPassengerCount());
    }


    @Test
    public void testCarRemovePassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Alice");
        car.addPassenger(p);
        car.removePassenger(p);
        assertEquals(0, car.getPassengerCount());
    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        Car car = new Car(2);
        Passenger p = new Passenger("Bob");
        assertTrue(p.boardCar(car));
        assertEquals(1, car.getPassengerCount());
    }

    @Test
    public void testPassengerBoardCarFull() {
        Car car = new Car(1);
        Passenger p1 = new Passenger("Alice");
        Passenger p2 = new Passenger("Bob");
        car.addPassenger(p1);
        assertFalse(p2.boardCar(car)); // Should fail because car is full
    }

    // Train Tests
    @Test
    public void testTrainConstructor() {
        Train train = new Train(3); // Assuming train starts with 3 cars
        assertEquals(3, train.getNumberOfCars());
    }

    @Test
    public void testTrainPassengerCount() {
        Train train = new Train(2);
        Passenger p = new Passenger("Charlie");
        train.getCar(0).addPassenger(p);
        assertEquals(1, train.getTotalPassengerCount());
    }

    @Test
    public void testTrainGetCar() {
        Train train = new Train(2);
        assertNotNull(train.getCar(1)); // Should return a valid Car object
    }

    @Test
    public void testTrainPrintManifest() {
        Train train = new Train(2);
        train.getCar(0).addPassenger(new Passenger("Bob"));
        train.getCar(1).addPassenger(new Passenger("Alice"));
        String manifest = train.printManifest();
        assertTrue(manifest.contains("Bob"));
        assertTrue(manifest.contains("Alice"));
    }
    
}
