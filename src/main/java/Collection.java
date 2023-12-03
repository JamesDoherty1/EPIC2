import java.util.ArrayList;

public class Collection {

    private static ArrayList<Taxi> taxis;

    public static void getClosestTaxi(ArrayList<Taxi> taxiList) {
        int personX = Person.getPersonX();
        int personY = Person.getPersonY();

        Taxi closestTaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxiList) {
            int taxiX = taxi.getTaxiX();
            int taxiY = taxi.getTaxiY();
            int distance = calculateDistance(personX, personY, taxiX, taxiY);

            if (distance < minDistance) {
                minDistance = distance;
                closestTaxi = taxi;
            }
        }

        if (closestTaxi != null) {
            int chosenTaxiX = closestTaxi.getTaxiX();
            System.out.println("X coordinate of the chosen taxi: " + chosenTaxiX);
        }
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        taxis = new ArrayList<>();
        taxis.add(new Big(new Map())); // Pass an instance of Map
        taxis.add(new Medium(new Map()));
        taxis.add(new Small(new Map()));

        int firstTaxiX = taxis.get(0).getTaxiX();
        System.out.println("X coordinate of the first taxi: " + firstTaxiX);

        getClosestTaxi(taxis);
    }
}