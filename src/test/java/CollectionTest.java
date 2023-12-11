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
        int initialAmount = 0;
        int finalAmount = 0;
        for(int i = 0; i < taxiList.size(); i++){
            initialAmount++;
        }
        taxiList.add(new Big(new Map()));
        for(int i = 0; i < taxiList.size(); i++){
            finalAmount++;
        }
        assertEquals("Adding one element should increase the size by 1", initialAmount + 1, finalAmount);
    }

    @Test
    public void isOnMap() {
        Taxi taxi = (Taxi) taxiList.get(0);
        int X = taxi.getTaxiX();
        int Y = taxi.getTaxiY();
        int height = MapPanel.SCREEN_HEIGHT;
        int width = MapPanel.SCREEN_WIDTH;

        assertTrue("X coordinate is out of bounds", X >= 0 && X <= width);

        assertTrue("Y coordinate is out of bounds", Y >= 0 && Y <= height);
    }

    @Test
    public void testMoveVehicle() {
        // Arrange
        Taxi taxi = (Taxi) taxiList.get(0);

        // Act
        taxi.move();

        // Assert
        assertTrue(taxi.shouldMove());
    }

    @Test
    public void testRemoveVehicle() {
        {
            int initialAmount = 0;
            int finalAmount = 0;
            for(int i = 0; i < taxiList.size(); i++){
                initialAmount++;
            }
            taxiList.remove(0);
            for(int i = 0; i < taxiList.size(); i++){
                finalAmount++;
            }
            assertEquals("Adding one element should increase the size by 1", initialAmount - 1, finalAmount);
        }

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
