import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

public class PhoneTest {

    private Phone phone;

    @Before
    public void setUp() {
        phone = new Phone(new MapPanel());
    }

    @Test
    public void testDepositMoney() {
        // Initial amount should be 0
        assertEquals(0.0, phone.getCurrentAmount(), 0.001);

        // Deposit money
        phone.depositMoney(50.0);

        // After deposit, the amount should be 50.0
        assertEquals(50.0, phone.getCurrentAmount(), 0.001);
    }

    @Test
    public void testUpdateAmountLabel() {
        // Initial amount label text should be "$0.0"
        assertEquals("$0.0", phone.getAmountLabel().getText());

        // Update the amount label with a new amount
        phone.setCurrentAmount(100.0);
        phone.updateAmountLabel();

        // After update, the amount label text should be "$100.0"
        assertEquals("$100.0", phone.getAmountLabel().getText());
    }

    @Test
    public void testSelectColorAndSize() {
        // Initial state, selecting color
        assertTrue(phone.isSelectingColor());
        assertNull(phone.getSelectedColor());

        // Simulate clicking the Red button
        phone.actionPerformed(new ActionEvent(phone.getRedButton(), ActionEvent.ACTION_PERFORMED, "Red"));

        // After clicking Red, it should be selecting size
        assertFalse(phone.isSelectingColor());
        assertEquals("red", phone.getSelectedColor());

        // Simulate clicking the Small button
        phone.actionPerformed(new ActionEvent(phone.getRedButton(), ActionEvent.ACTION_PERFORMED, "Small"));

        // After clicking Small, UI should be updated
        assertFalse(phone.getRedButton().isVisible());
        assertFalse(phone.getBlueButton().isVisible());
        assertFalse(phone.getYellowButton().isVisible());
        assertFalse(phone.getDepositButton().isVisible());
        assertFalse(phone.getAmountLabel().isVisible());
        assertTrue(phone.getDriverInfo().isVisible());
    }

    @Test
    public void testDisplayInfo() {
        // Simulate calling displayInfo with index 0
        phone.displayInfo(0);

        // Check if driverInfo text is not null or empty (you may replace with expected text)
        assertNotNull(phone.getDriverInfo().getText());
        assertNotEquals("", phone.getDriverInfo().getText().trim());
    }
}
