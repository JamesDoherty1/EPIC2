import javax.swing.*;
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
        int personX = Person.getPersonX();
        int personY = Person.getPersonY();
        int UNITSIZE = MapPanel.UNIT_SIZE;
        int temp = 0;

        for (Taxi taxi : taxis) {
            closestTaxi.setShouldMove(false);
        }

        moveToIntersection(personX,personY, UNITSIZE, temp);
    }

    public static void moveToIntersection(int personX, int personY,int UNITSIZE, int temp) {
        Timer timer = MapPanel.timer;
        int maxIterations = 4;  // Set a maximum number of iterations

        if (Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY())) {
                while (!(Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() +  UNITSIZE)))) {
                    closestTaxi.setTaxiX(closestTaxi.getTaxiX() + UNITSIZE);
                    timer.start();

                }
        }
        if (Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() + UNITSIZE))) {
            while (!(Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY()))) {
                closestTaxi.setTaxiY(closestTaxi.getTaxiY() + UNITSIZE);
                timer.start();

            }
        }

    }
    public static void  moveToX(){

    }

    public static void  moveToY(){

    }


    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {

        taxis = new ArrayList<>();  // Initialize taxis with appropriate values
        getClosestTaxi(taxis);
    }
}
