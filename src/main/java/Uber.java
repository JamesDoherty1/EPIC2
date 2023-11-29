import java.util.Random;
class Uber extends Taxi {

    Uber(Map map) {
        super(map);
        Random random = new Random();

        int randomX = random.nextInt(MapPanel.SCREEN_WIDTH / Map.UNIT_SIZE) * Map.UNIT_SIZE;
        int randomY = random.nextInt(MapPanel.SCREEN_HEIGHT / Map.UNIT_SIZE) * Map.UNIT_SIZE;
        randomY = Math.min(randomY, MapPanel.SCREEN_HEIGHT - Map.UNIT_SIZE);
        randomX = Math.min(randomY, MapPanel.SCREEN_HEIGHT - Map.UNIT_SIZE);

        setTaxiX(randomX);
        setTaxiY(randomY);
    }
}