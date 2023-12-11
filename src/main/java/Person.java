import java.awt.*;
import java.util.Random;

class Person {
    private static int personX;
    private static int personY;
    private final Map map;

    Person(Map map) {
        this.map = map;
    }

    void newPerson() {
        Random random = new Random();
        personX = random.nextInt((int) (MapPanel.SCREEN_WIDTH / MapPanel.UNIT_SIZE)) * MapPanel.UNIT_SIZE;
        personY = random.nextInt((int) (MapPanel.SCREEN_HEIGHT / MapPanel.UNIT_SIZE)) * MapPanel.UNIT_SIZE;
        while (!map.isOnGreySquare(personX, personY)) {
            personX++;
            personY++;
        }
    }

    void draw(Graphics graphics) {
        graphics.fillOval(personX, personY, MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE);
    }

    public static int getPersonX() {
        return personX;
    }

    public static int getPersonY() {
        return personY;
    }
    public static void setPersonX(int newPersonX){
        personX = newPersonX;
    }
    public static void setPersonY(int newPersonY){
        personY = newPersonY;
    }
}

