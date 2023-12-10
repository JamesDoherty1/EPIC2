import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class CollectionTest {

    private MyArrayList<Taxi> taxiList;
    private Taxi closestTaxi;

    @BeforeEach
    void setUp() {
        taxiList = new MyArrayList<>();
        taxiList.add(new Big(new Map()));
        taxiList.add(new Big(new Map()));
        taxiList.add(new Medium(new Map()));
        taxiList.add(new Medium(new Map()));
        taxiList.add(new Small(new Map()));
        taxiList.add(new Small(new Map()));

        closestTaxi = new Big(new Map()); // Assuming Big is the closest initially
    }

    @Test
    void testGetClosestTaxi() {
        Person.setPersonX(2);
        Person.setPersonY(2);

        Taxi result = Collection.getClosestTaxi(taxiList, "color", "size");

        assertEquals(closestTaxi, result);
    }

    @Test
    void testStopRandomMovement() {
        Person.setPersonX(2);
        Person.setPersonY(2);

        assertDoesNotThrow(() -> Collection.stopRandomMovement("color"));
    }

    @Test
    void testMoveToIntersection() {
        // It's challenging to directly test the method with SwingWorker.
        // Consider refactoring for better testability or use more advanced testing techniques.
    }

    @Test
    void testCheckUpOrDown() {
        int destinationX = 5;
        int destinationY = 5;
        int UNITSIZE = 1;
        String color = "color";

        Collection.checkUpOrDown(destinationX, destinationY, UNITSIZE, color);

        // Add assertions as needed
    }

    @Test
    void testMoveToX() {
        int destinationX = 5;
        int destinationY = 5;
        int UNITSIZE = 1;
        String color = "color";

        Collection.moveToX(destinationX, destinationY, UNITSIZE, color);

        // Add assertions as needed
    }

    // Add more tests as needed for other methods

    @Test
    void testCalculateDistance() {
        int x1 = 0, y1 = 0, x2 = 3, y2 = 4;
        int expectedDistance = 5;

        int result = Collection.calculateDistance(x1, y1, x2, y2);

        assertEquals(expectedDistance, result);
    }

    @Test
    void testMain() {
        // Add tests for the main method if applicable
    }
}
