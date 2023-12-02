import javax.swing.*;

public class Collection {

    private static Taxi taxi1;
    private static Taxi taxi2;
    private static Taxi taxi3;
    private static Taxi taxi4;
    private static Taxi taxi5;
    private static Taxi taxi6;

    public static void getClosestTaxi() {
        int personX = Person.getPersonX();
        int personY = Person.getPersonY();

        int taxiX1 = taxi1.getTaxiX();
        int taxiY1 = taxi1.getTaxiY();
        int distance1 = calculateDistance(personX, personY, taxiX1, taxiY1);

        int taxiX2 = taxi2.getTaxiX();
        int taxiY2 = taxi2.getTaxiY();
        int distance2 = calculateDistance(personX, personY, taxiX2, taxiY2);

        int taxiX3 = taxi3.getTaxiX();
        int taxiY3 = taxi3.getTaxiY();
        int distance3 = calculateDistance(personX, personY, taxiX3, taxiY3);

        int taxiX4 = taxi1.getTaxiX();
        int taxiY4 = taxi1.getTaxiY();
        int distance4 = calculateDistance(personX, personY, taxiX4, taxiY4);

        int taxiX5 = taxi2.getTaxiX();
        int taxiY5 = taxi2.getTaxiY();
        int distance5 = calculateDistance(personX, personY, taxiX5, taxiY5);

        int taxiX6 = taxi3.getTaxiX();
        int taxiY6 = taxi3.getTaxiY();
        int distance6 = calculateDistance(personX, personY, taxiX6, taxiY6);

    }
    public static int calculateDistance(int x1, int y1, int x2, int y2){
        // Implement the distance calculation (e.g., Euclidean distance)
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main (String[]args){
        SwingUtilities.invokeLater(() -> {
        });
    }
}
