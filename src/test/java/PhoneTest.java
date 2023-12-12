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
    public void testDisplayInfo() {
        // Simulate calling displayInfo with index 0
        phone.displayInfo(0);

        // Check if driverInfo text is not null or empty
        assertNotNull(phone.getDriverInfo().getText());
        assertNotEquals("", phone.getDriverInfo().getText().trim());
    }
}
