import java.util.ArrayList;

public class Collection {

    private static ArrayList<Taxi> taxis;

    public static void getClosestTaxi() {
        int personX = Person.getPersonX();
        int personY = Person.getPersonY();

        Taxi closestTaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            int taxiX = taxi.getTaxiX();
            int taxiY = taxi.getTaxiY();
            int distance = calculateDistance(personX, personY, taxiX, taxiY);

            if (distance < minDistance) {
                minDistance = distance;
                closestTaxi = taxi;
            }
        }

        if (closestTaxi != null) {
            // Use the getter method to get the X coordinate of the chosen taxi
            int chosenTaxiX = getChosenTaxiX(closestTaxi);
            System.out.println("X coordinate of the chosen taxi: " + chosenTaxiX);
        }
    }

    // Getter method to get the X coordinate of a chosen taxi
    public static int getChosenTaxiX(Taxi taxi) {
        return taxi.getTaxiX();
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        // Initialize taxis
        taxis = new ArrayList<>();
        taxis.add(new Big(null)); // Replace 'null' with the actual map instance
        taxis.add(new Medium(null));
        taxis.add(new Small(null));

        // Example of getting the X coordinate of the first taxi
        int firstTaxiX = getChosenTaxiX(taxis.get(0));
        System.out.println("X coordinate of the first taxi: " + firstTaxiX);
    }
}
