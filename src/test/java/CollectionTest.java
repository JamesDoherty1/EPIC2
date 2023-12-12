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
        Person person;
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
    public void testIsOnMap() {
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

        Taxi taxi = (Taxi) taxiList.get(0);

        taxi.move();

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
        Taxi taxi = (Taxi) taxiList.get(0);
        // Set X and Y coordinates
        int setX = 225;
        int setY = 225;

        taxi.setTaxiX(setX);
        taxi.setTaxiY(setY);

        // Get X and Y coordinates
        int getX = taxi.getTaxiX();
        int getY = taxi.getTaxiY();

        // Assert that setX is equal to getX
        assertEquals("X coordinates should match after setting", setX, getX);

        // Assert that setY is equal to getY
        assertEquals("Y coordinates should match after setting", setY, getY);
    }

    @Test
    public void testGetVehiclesInRange() {
        int radius = 100;

        // Set the person's location
        Person.setPersonX(25);
        Person.setPersonY(25);

        // Set the locations of taxis
        Taxi closeTaxi = (Taxi) taxiList.get(0);
        closeTaxi.setTaxiX(50);
        closeTaxi.setTaxiY(50);

        Taxi farTaxi1 = (Taxi) taxiList.get(1); // Assuming you have more than one taxi
        farTaxi1.setTaxiX(675);
        farTaxi1.setTaxiY(675);

        Taxi farTaxi2 = (Taxi) taxiList.get(2); // Assuming you have more than two taxis
        farTaxi2.setTaxiX(725);
        farTaxi2.setTaxiY(725);

        // Count the number of taxis within the radius
        int taxisWithinRadius = 0;

        // Check each taxi
        for (Taxi taxi : taxiList) {
            int distance = calculateDistance(taxi.getTaxiX(), taxi.getTaxiY(), Person.getPersonX(), Person.getPersonY());
            if (distance <= radius) {
                taxisWithinRadius++;
            }
        }

        // Assert that only 1 taxi is within the radius
        assertEquals("Only 1 taxi should be within the radius", 1, taxisWithinRadius);
    }

    // A method to calculate the distance between two points
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
