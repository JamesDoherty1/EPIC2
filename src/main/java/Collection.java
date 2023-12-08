import javax.swing.*;
import java.util.ArrayList;

public class Collection {

    private static ArrayList<Taxi> taxis;
    private static Taxi closestTaxi;
    static boolean collected;
    static int destinationX = Person.getPersonX();
    static int destinationY = Person.getPersonY();

    public static Taxi getClosestTaxi(ArrayList<Taxi> taxiList, String colour) {
        collected = false;
        taxis = taxiList;  // Set the class variable

        closestTaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxiList) {
            int taxiX = taxi.getTaxiX();
            int taxiY = taxi.getTaxiY();
            int distance = calculateDistance(destinationX, destinationY, taxiX, taxiY);

            if (distance < minDistance) {
                minDistance = distance;
                closestTaxi = taxi;
            }
        }

        if (closestTaxi != null) {
            stopRandomMovement(colour);
        }
        return closestTaxi;
    }

    public static void stopRandomMovement(String colour) {
        int destinationX = Person.getPersonX();
        int destinationY = Person.getPersonY();
        int UNITSIZE = MapPanel.UNIT_SIZE;

        for (Taxi taxi : taxis) {
            closestTaxi.setShouldMove(false);
        }

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                moveToIntersection(destinationX, destinationY, UNITSIZE, colour);
                return null;
            }
        };
        worker.execute();
    }

    public static void moveToIntersection(int destinationX, int destinationY, int UNITSIZE, String colour) {
        if(collected){
            chooseBuilding(destinationX, destinationY, UNITSIZE, colour);
        }
        if (Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY())) {
            while (!(Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() + UNITSIZE)))) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() + UNITSIZE));
            }
            checkUpOrDown(destinationX, destinationY, UNITSIZE,colour);
        }
        else if(Map.isOnGreySquare(closestTaxi.getTaxiX(), (closestTaxi.getTaxiY() + UNITSIZE))) {
            while (!(Map.isOnGreySquare((closestTaxi.getTaxiX() + UNITSIZE), closestTaxi.getTaxiY()))) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() + UNITSIZE));
            }
            checkUpOrDown(destinationX, destinationY, UNITSIZE, colour);
        }
        else{
            checkUpOrDown(destinationX, destinationY, UNITSIZE, colour);
        }
    }

    public static void checkUpOrDown(int destinationX, int destinationY, int UNITSIZE, String colour) {
        if (Map.isOnGreySquare((destinationX + UNITSIZE), destinationY)) {
            moveToY(destinationX, destinationY, UNITSIZE, colour);
        } else {
            moveToX(destinationX, destinationY, UNITSIZE, colour);
        }
    }

    public static void moveToX(int destinationX, int destinationY, int UNITSIZE, String colour) {
        int difference = destinationX - closestTaxi.getTaxiX();
        if ((destinationX == closestTaxi.getTaxiX()) && (destinationY == closestTaxi.getTaxiY())) {
            collected(destinationX, destinationY, UNITSIZE, colour);
        }
        if (difference >= 0) {
            while (closestTaxi.getTaxiX() != destinationX) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() + UNITSIZE));
            }
            moveToY(destinationX, destinationY, UNITSIZE,colour);
        }
        if (difference < 0) {
            while (closestTaxi.getTaxiX() != destinationX) {
                delayAndMove(() -> closestTaxi.setTaxiX(closestTaxi.getTaxiX() - UNITSIZE));
            }
            moveToY(destinationX, destinationY, UNITSIZE, colour);
        }
    }

    public static void moveToY(int destinationX, int destinationY, int UNITSIZE, String colour) {
        int difference = destinationY - closestTaxi.getTaxiY();

        if ((destinationX == closestTaxi.getTaxiX()) && (destinationY == closestTaxi.getTaxiY())) {
            collected(destinationX, destinationY, UNITSIZE, colour);
        }
        if (difference >= 0) {
            while (closestTaxi.getTaxiY() != destinationY) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() + UNITSIZE));
            }
            moveToX(destinationX, destinationY, UNITSIZE, colour);
        }
        if (difference < 0) {
            while (closestTaxi.getTaxiY() != destinationY) {
                delayAndMove(() -> closestTaxi.setTaxiY(closestTaxi.getTaxiY() - UNITSIZE));
            }
            moveToX(destinationX, destinationY, UNITSIZE, colour);
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

    public static void collected(int destinationX, int destinationY, int UNITSIZE, String colour) {
        System.out.println("Collected");
        MapPanel.setShowPerson(false);
            collected = true;
            moveToIntersection(destinationX, destinationY, UNITSIZE, colour);
        }

    public static void chooseBuilding(int destinationX, int destinationY, int UNITSIZE, String colour){
        int buildingX;
        int buildingY;
        if(colour.equals("blue")){
            buildingX = 350;
            buildingY = 500;
            destinationX = buildingX;
            destinationY = buildingY;
        }
        else if(colour.equals("red")){
            buildingX = 50;
            buildingY = 100;
            destinationX = buildingX;
            destinationY = buildingY;
        }
        else if(colour.equals("yellow")){
            buildingX = 650;
            buildingY = 200;
            destinationX = buildingX;
            destinationY = buildingY;
        }
        else{
            Person.setPersonX(closestTaxi.getTaxiX());
            Person.setPersonY(closestTaxi.getTaxiY());
            MapPanel.setShowPerson(true);
            closestTaxi.setShouldMove(true);
        }
        collected = false;
        moveToIntersection(destinationX, destinationY, UNITSIZE, "colour");
    }



    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {

        taxis = new ArrayList<>();  // Initialize taxis with appropriate values
    }
}
