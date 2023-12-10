import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CollectionTest {

    @Test
    void testGetClosestTaxi() {
        // Create a list of taxis for testing
        MyArrayList<Taxi> taxiList = new MyArrayList<>();
        Taxi taxi1 = mock(Taxi.class);
        Taxi taxi2 = mock(Taxi.class);
        Taxi taxi3 = mock(Taxi.class);
        taxiList.add(taxi1);
        taxiList.add(taxi2);
        taxiList.add(taxi3);

        // Mock Person class
        Person person = mock(Person.class);
        when(person.getPersonX()).thenReturn(3);  // Set a mock person X coordinate
        when(person.getPersonY()).thenReturn(3);  // Set a mock person Y coordinate

        // Mock Map class
        Map mockMap = mock(Map.class);
        when(taxi1.getTaxiX()).thenReturn(0);
        when(taxi1.getTaxiY()).thenReturn(0);
        when(taxi2.getTaxiX()).thenReturn(5);
        when(taxi2.getTaxiY()).thenReturn(5);
        when(taxi3.getTaxiX()).thenReturn(10);
        when(taxi3.getTaxiY()).thenReturn(10);
        when(taxi1.getMap()).thenReturn(mockMap);
        when(taxi2.getMap()).thenReturn(mockMap);
        when(taxi3.getMap()).thenReturn(mockMap);

        // Test the getClosestTaxi method
        Taxi closestTaxi = Collection.getClosestTaxi(taxiList, "red");

        // Verify that the closest taxi is correctly determined based on the mock coordinates
        assertEquals(taxi2, closestTaxi);
    }


    // Add more tests for other methods in the Collection class

    // ...
}
