import java.awt.*;
import java.util.Random;

abstract class Taxi {
    private int taxiX;
    private int taxiY;
    private final Map map;
    private boolean shouldMove = true;

    Taxi(Map map) {
        this.map = map;
    }

    void move() {
        int newTaxiX = getTaxiX();
        int newTaxiY = getTaxiY();
        Random random = new Random();

        int randomDirection = random.nextInt(4);

        switch (randomDirection) {
            case 0:  // Up
                if (newTaxiY - MapPanel.UNIT_SIZE >= 0) {
                    newTaxiY -= MapPanel.UNIT_SIZE;
                }
                break;
            case 1:  // Down
                if (newTaxiY + MapPanel.UNIT_SIZE < MapPanel.SCREEN_HEIGHT) {
                    newTaxiY += MapPanel.UNIT_SIZE;
                }
                break;
            case 2:  // Left
                if (newTaxiX - MapPanel.UNIT_SIZE >= 0) {
                    newTaxiX -= MapPanel.UNIT_SIZE;
                }
                break;
            case 3:  // Right
                if (newTaxiX + MapPanel.UNIT_SIZE < MapPanel.SCREEN_WIDTH) {
                    newTaxiX += MapPanel.UNIT_SIZE;
                }
                break;
        }

        if (map.isOnGreySquare(newTaxiX, newTaxiY)) {
            setTaxiX(newTaxiX);
            setTaxiY(newTaxiY);
        }
    }

    void draw(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRect(getTaxiX(), getTaxiY(), MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE);
    }
    void setShouldMove(boolean shouldMove) {
        this.shouldMove = shouldMove;
    }
    boolean shouldMove() {
        return shouldMove;
    }


    int getTaxiX() {
        return taxiX;
    }

    int getTaxiY() {
        return taxiY;
    }

    void setTaxiX(int taxiX) {
        this.taxiX = taxiX;
    }

    void setTaxiY(int taxiY) {
        this.taxiY = taxiY;
    }
}