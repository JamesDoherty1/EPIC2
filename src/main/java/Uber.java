import java.awt.*;
import java.util.Random;

class Uber extends Taxi {
    private int taxiX;
    private int taxiY;

    Uber() {
        Random random = new Random();

        int randomX = random.nextInt(MapPanel.SCREEN_WIDTH / MapPanel.UNIT_SIZE) * MapPanel.UNIT_SIZE;
        int randomY = random.nextInt(MapPanel.SCREEN_HEIGHT / MapPanel.UNIT_SIZE) * MapPanel.UNIT_SIZE;
        randomY = Math.min(randomY, MapPanel.SCREEN_HEIGHT - MapPanel.UNIT_SIZE);
        randomX = Math.min(randomY, MapPanel.SCREEN_HEIGHT - MapPanel.UNIT_SIZE);

        setTaxiX(randomX);  // Initialize the taxi position
        setTaxiY(randomY);
    }

    public void setColour(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRect(getTaxiX(), getTaxiY(), MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE);
    }

    @Override
    public int getTaxiX() {
        return taxiX;
    }

    @Override
    public void setTaxiX(int taxiX) {
        this.taxiX = taxiX;
    }

    @Override
    public int getTaxiY() {
        return taxiY;
    }

    @Override
    public void setTaxiY(int taxiY) {
        this.taxiY = taxiY;
    }
}