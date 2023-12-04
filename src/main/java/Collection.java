import java.util.ArrayList;

public class Collection {

    private static ArrayList<Taxi> taxis;
    private static Taxi closestTaxi;

    public static Taxi getClosestTaxi(ArrayList<Taxi> taxiList) {
        taxis = taxiList;  // Set the class variable

        int personX = Person.getPersonX();
        int personY = Person.getPersonY();

        closestTaxi = null;
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
            collectPerson();
        }
        return closestTaxi;
    }

    public static void collectPerson() {
        for (Taxi taxi : taxis) {
            closestTaxi.setShouldMove(false);
        }

        int personX = Person.getPersonX();
        int personY = Person.getPersonY();
        int chosenTaxiX = closestTaxi.getTaxiX();
        int chosenTaxiY = closestTaxi.getTaxiY();

        int UNITSIZE = MapPanel.UNIT_SIZE;
        int taxiXPlusOne = chosenTaxiX + UNITSIZE;
        int taxiYPlusOne = chosenTaxiY + UNITSIZE;
        int temp = 0;

        if (personY > chosenTaxiY) {
            while (!(Map.isOnGreySquare(taxiXPlusOne, taxiYPlusOne))) {
                temp = chosenTaxiY + UNITSIZE;
                chosenTaxiY = temp;
            }
        }
        if (personY < chosenTaxiY) {
            while (!(Map.isOnGreySquare(taxiXPlusOne, taxiYPlusOne))) {
                temp = chosenTaxiY - UNITSIZE;
                chosenTaxiY = temp;
            }
        }
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        taxis = new ArrayList<>();  // Initialize taxis with appropriate values
        getClosestTaxi(taxis);
    }
}
