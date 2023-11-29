
import java.util.Random;

abstract class Taxi {
    static void move(Taxi taxi, int SCREEN_WIDTH, int SCREEN_HEIGHT, int UNIT_SIZE) {
        int newTaxiX = taxi.getTaxiX();
        int newTaxiY = taxi.getTaxiY();
        Random random = new Random();

        int randomDirection = random.nextInt(4);

        switch (randomDirection) {
            case 0:  // Up
                if (newTaxiY - UNIT_SIZE >= 0) {
                    newTaxiY -= UNIT_SIZE;
                }
                break;
            case 1:  // Down
                if (newTaxiY + UNIT_SIZE < SCREEN_HEIGHT) {
                    newTaxiY += UNIT_SIZE;
                }
                break;
            case 2:  // Left
                if (newTaxiX - UNIT_SIZE >= 0) {
                    newTaxiX -= UNIT_SIZE;
                }
                break;
            case 3:  // Right
                if (newTaxiX + UNIT_SIZE < SCREEN_WIDTH) {
                    newTaxiX += UNIT_SIZE;
                }
                break;
        }

        // Check if the new position is on a grey square
        if (isOnGreySquare(newTaxiX, newTaxiY)) {
            taxi.setTaxiX(newTaxiX);
            taxi.setTaxiY(newTaxiY);
        }
    }

    static boolean isOnGreySquare(int x, int y) {
        // Determine if the given coordinates (x, y) are on a grey square
        int row = y / MapPanel.UNIT_SIZE;
        int col = x / MapPanel.UNIT_SIZE;

        // Add your condition here based on the road pattern
        return MapPanel.shouldDrawRoad(row, col);
    }

    public abstract int getTaxiX();

    public abstract int getTaxiY();

    public abstract void setTaxiX(int taxiX);
    public abstract void setTaxiY(int taxiY);
}