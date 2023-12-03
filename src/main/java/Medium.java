import java.util.Random;

public class Medium extends Taxi {
    Medium(Map map) {
        super(map);
        Random random = new Random();

        int randomX = random.nextInt(MapPanel.SCREEN_WIDTH / MapPanel.UNIT_SIZE) * MapPanel.UNIT_SIZE;
        int randomY = random.nextInt(MapPanel.SCREEN_HEIGHT / MapPanel.UNIT_SIZE) * MapPanel.UNIT_SIZE;
        randomY = Math.min(randomY, MapPanel.SCREEN_HEIGHT - MapPanel.UNIT_SIZE);
        randomX = Math.min(randomX, MapPanel.SCREEN_HEIGHT - MapPanel.UNIT_SIZE);

        while(!(map.isOnGreySquare(randomX, randomY))){
            randomX++;
            randomY++;
        }
        setTaxiX(randomX);
        setTaxiY(randomY);
    }
}
