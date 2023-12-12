import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MapTest {

    private Phone phone;

    @Before
    public void setUp() {
        phone = new Phone(new MapPanel());
    }
    // Test that taxis move within the map boundaries
    @Test
    public void testTaxiMovement() {
        Map map = new Map();
        Big bigTaxi = new Big(map);
        Medium mediumTaxi = new Medium(map);
        Small smallTaxi = new Small(map);

        // Move each taxi and check if the new position is within the map boundaries
        bigTaxi.move();
        assertTrue(bigTaxi.getTaxiX() >= 0 && bigTaxi.getTaxiX() < MapPanel.SCREEN_WIDTH);
        assertTrue(bigTaxi.getTaxiY() >= 0 && bigTaxi.getTaxiY() < MapPanel.SCREEN_HEIGHT);

        mediumTaxi.move();
        assertTrue(mediumTaxi.getTaxiX() >= 0 && mediumTaxi.getTaxiX() < MapPanel.SCREEN_WIDTH);
        assertTrue(mediumTaxi.getTaxiY() >= 0 && mediumTaxi.getTaxiY() < MapPanel.SCREEN_HEIGHT);

        smallTaxi.move();
        assertTrue(smallTaxi.getTaxiX() >= 0 && smallTaxi.getTaxiX() < MapPanel.SCREEN_WIDTH);
        assertTrue(smallTaxi.getTaxiY() >= 0 && smallTaxi.getTaxiY() < MapPanel.SCREEN_HEIGHT);
    }

    // Test that taxis are initialized on grey squares
    @Test
    public void testTaxiInitialization() {
        Map map = new Map();
        Big bigTaxi = new Big(map);
        Medium mediumTaxi = new Medium(map);
        Small smallTaxi = new Small(map);

        // Check if each taxi is initialized on a grey square
        assertTrue(map.isOnGreySquare(bigTaxi.getTaxiX(), bigTaxi.getTaxiY()));
        assertTrue(map.isOnGreySquare(mediumTaxi.getTaxiX(), mediumTaxi.getTaxiY()));
        assertTrue(map.isOnGreySquare(smallTaxi.getTaxiX(), smallTaxi.getTaxiY()));
    }
    @Test
    public void testTaxiInteractionWithMap() {
        Map map = new Map();
        Big bigTaxi = new Big(map);

        // Place the taxi on a non-grey square
        bigTaxi.setTaxiX(50);
        bigTaxi.setTaxiY(50);

        // Attempt to move the taxi
        bigTaxi.move();

        // Verify that the taxi position remains unchanged
        assertEquals(50, bigTaxi.getTaxiX());
        assertEquals(50, bigTaxi.getTaxiY());
    }

}
