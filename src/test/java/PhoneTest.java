import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneTest {

    @Test
    public void testActionPerformed() {
        // Create a mock MapPanel for testing
        MapPanel mockMapPanel = new MapPanel();

        // Create a mock Phone instance for testing
        Phone phone = new Phone(mockMapPanel);

        // Set up the initial state
        phone.setSelectingColor(true);

        // Simulate the action performed event for the "Red" button
        phone.actionPerformed(new ActionEvent(phone.getRedButton(), ActionEvent.ACTION_PERFORMED, "Red"));

        // Verify that the state has changed as expected
        assertEquals("Small", phone.getRedButton().getText());
        assertEquals("Medium", phone.getBlueButton().getText());
        assertEquals("Big", phone.getYellowButton().getText());
        assertEquals(false, phone.isSelectingColor());

        // Simulate the action performed event for the "Small" button
        phone.actionPerformed(new ActionEvent(phone.getRedButton(), ActionEvent.ACTION_PERFORMED, "Small"));

        // Verify that the UI has been updated as expected
        assertEquals("Drivers Information", phone.getQuestion().getText());
        assertEquals(false, phone.getRedButton().isVisible());
        assertEquals(false, phone.getBlueButton().isVisible());
        assertEquals(false, phone.getYellowButton().isVisible());
        assertEquals(true, phone.getDriverInfo().isVisible());
    }

    // Add more tests for other methods and functionalities as needed
}
