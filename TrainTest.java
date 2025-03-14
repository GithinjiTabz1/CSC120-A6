import static org.junit.Assert.*;
import org.junit.Test;

public class TrainTest {

    // Engine Tests
    @Test
    public void testEngineConstructor() {
        Engine engine = new Engine(FuelType.STEAM, 100);
        assertEquals(FuelType.STEAM, engine.getFuelType());
        assertEquals(100, engine.getCurrentFuel());   
    }

    @Test
    public void testEngineGo() {
        Engine engine = new Engine(FuelType.STEAM, 100);
        engine.go();
        assertTrue(engine.getCurrentFuel() < 100); 
    }

       

    }

    // Car Tests
    @Test
    public void testCarAddPassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Tabz");
        car.addPassenger(p);
        car.removePassenger(p);
        assertEquals(0, car.getPassengers().size());
    }


    @Test
    public void testCarRemovePassenger() {
        Car car = new Car(3);
        Passenger p = new Passenger("Tabz");
        car.addPassenger(p);
        car.removePassenger(p);
        assertEquals(0, car.getPassengers().size());
    }

    // Passenger Tests
    @Test
    public void testPassengerBoardCarWithSpace() {
        Car car = new Car(2);
        Passenger p = new Passenger("Githinji");
        assertTrue(p.boardCar(car));
        assertEquals(1, car.getPassengers().size());
    }

    @Test
    public void testPassengerBoardCarFull() {
        Car car = new Car(1);
        Passenger p1 = new Passenger("Tabz");
        Passenger p2 = new Passenger("Githinji");
        car.addPassenger(p1);
        assertFalse(p2.boardCar(car));
    }

    // Train Tests
    @Test
    public void testTrainConstructor() {
        Train train = new Train(3);
        assertEquals(3, train.getNumberOfCars());
    }

    @Test
    public void testTrainPassengerCount() {
        Train train = new Train(2);
        Passenger p = new Passenger("Clare");
        train.getCar(0).addPassenger(p);
        assertEquals(1, train.getTotalPassengerCount());
    }

    @Test
    public void testTrainGetCar() {
        Train train = new Train(2);
        assertNotNull(train.getCar(1)); 
    }

    @Test
    public void testTrainPrintManifest() {
        Train train = new Train(2);
        train.getCar(0).addPassenger(new Passenger("Tabz"));
        train.getCar(1).addPassenger(new Passenger("Githinji"));
        String manifest = train.printManifest();
        assertTrue(manifest.contains("Tabz"));
        assertTrue(manifest.contains("Gthinji"));
    }
    
}
