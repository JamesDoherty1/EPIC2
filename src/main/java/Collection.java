import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Collection {

    private static ArrayList<Taxi> taxis;
    private static Taxi closestTaxi;
    private static Timer timer;

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
            stopRandomMovement();
        }
        return closestTaxi;
    }

    public static void stopRandomMovement() {
        int personX = Person.getPersonX();
        int personY = Person.getPersonY();
        int UNITSIZE = MapPanel.UNIT_SIZE;
        int temp = 0;

        for (Taxi taxi : taxis) {
            closestTaxi.setShouldMove(false);
        }

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                moveToIntersection(personX, personY, UNITSIZE, temp);
                return null;
            }
        };
        worker.execute();
    }

    public static void moveToIntersection(int personX, int personY, int UNITSIZE, int temp) {
        if (Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY())) {
            while (!(Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() + UNITSIZE)))) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() + UNITSIZE));
            }
            checkUpOrDown(personX, personY, UNITSIZE);
        }
        if (Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() + UNITSIZE))) {
            while (!(Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY()))) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() + UNITSIZE));
            }
            checkUpOrDown(personX, personY, UNITSIZE);
        }
    }

    public static void checkUpOrDown(int personX, int personY, int UNITSIZE) {
        if (Map.isOnGreySquare((personX + UNITSIZE), personY)) {
            moveToY(personX, personY, UNITSIZE);
        } else {
            moveToX(personX, personY, UNITSIZE);
        }
    }

    public static void moveToX(int personX, int personY, int UNITSIZE) {
        int difference = personX - closestTaxi.getTaxiX();
        if ((personX == closestTaxi.getTaxiX()) && (personY == closestTaxi.getTaxiY())) {
            collected();
        }
        if (difference > 0) {
            while (closestTaxi.getTaxiX() != personX) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() + UNITSIZE));
            }
            moveToY(personX, personY, UNITSIZE);
        }
        if (difference < 0) {
            while (closestTaxi.getTaxiX() != personX) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() - UNITSIZE));
            }
            moveToY(personX, personY, UNITSIZE);
        }
    }

    public static void moveToY(int personX, int personY, int UNITSIZE) {
        int difference = personY - closestTaxi.getTaxiY();

        if ((personX == closestTaxi.getTaxiX()) && (personY == closestTaxi.getTaxiY())) {
            collected();
        }
        if (difference > 0) {
            while (closestTaxi.getTaxiY() != personY) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() + UNITSIZE));
            }
            moveToX(personX, personY, UNITSIZE);
        }
        if (difference < 0) {
            while (closestTaxi.getTaxiY() != personY) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() - UNITSIZE));
            }
            moveToX(personX, personY, UNITSIZE);
        }
    }

    private static void delayAndMove(Runnable moveAction) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveAction.run();
    }

    public static void collected() {
        System.out.println("Collected");
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {

        taxis = new ArrayList<>();  // Initialize taxis with appropriate values
        getClosestTaxi(taxis);
    }
}
