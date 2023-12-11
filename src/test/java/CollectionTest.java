import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CollectionTest {

    private Collection collection;
    private MyArrayList<Taxi> taxiList;

    @Before
    public void setUp() {
        // Initialize the collection and taxiList as needed
        taxiList = new MyArrayList<>();
        taxiList.add(new Big(new Map()));
        taxiList.add(new Medium(new Map()));
        taxiList.add(new Small(new Map()));
    }

    @Test
    public void testAddToMap() {
        // Add your test logic here
        // Example:
        // collection.addToMap(taxiList.get(0));
        // assertTrue(collection.getMap().contains(taxiList.get(0)));
    }

    @Test
    public void testMoveVehicle() {
        // Arrange
        Map map = null;
        MyArrayList taxis = new MyArrayList();
        taxis.add(new Big(map));
        Taxi taxi = (Taxi) taxis.get(0);

        // Act
        taxi.move();

        // Assert
        assertTrue(taxi.shouldMove());
    }

    @Test
    public void testRemoveVehicle() {
        // Add your test logic here
        // Example:
        // collection.removeFromMap(taxiList.get(0));
        // assertFalse(collection.getMap().contains(taxiList.get(0)));
    }

    @Test
    public void testGetVehicleLoc() {
        // Add your test logic here
        // Example:
        // assertEquals(expectedLocation, collection.getVehicleLocation(taxiList.get(0)));
    }

    @Test
    public void testGetVehiclesInRange() {
        // Add your test logic here
        // Example:
        // List<Taxi> vehiclesInRange = collection.getVehiclesInRange(targetLocation, range);
        // assertTrue(vehiclesInRange.contains(taxiList.get(0)));
    }
}
