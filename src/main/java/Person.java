import java.awt.*;
import java.util.Random;

class Person {
    private int personX;
    private int personY;
    private final Map map;

    Person(Map map) {
        this.map = map;
    }

    void newPerson() {
        Random random = new Random();
        personX = random.nextInt((int) (MapPanel.SCREEN_WIDTH / Map.UNIT_SIZE)) * Map.UNIT_SIZE;
        personY = random.nextInt((int) (MapPanel.SCREEN_HEIGHT / Map.UNIT_SIZE)) * Map.UNIT_SIZE;
        while (!map.isOnGreySquare(personX, personY)) {
            personX++;
            personY++;
        }
    }

    void draw(Graphics graphics) {
        graphics.fillOval(personX, personY, Map.UNIT_SIZE, Map.UNIT_SIZE);
    }
}
