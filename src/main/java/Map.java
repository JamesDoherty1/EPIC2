import java.awt.*;

class Map {

    void draw(Graphics graphics) {
        for (int i = 0; i < MapPanel.SCREEN_HEIGHT / MapPanel.UNIT_SIZE; i++) {
            for (int j = 0; j < MapPanel.SCREEN_WIDTH / MapPanel.UNIT_SIZE; j++) {
                graphics.drawRect(j * MapPanel.UNIT_SIZE, i * MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE);

                if (shouldDrawRoad(i, j)) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(j * MapPanel.UNIT_SIZE, i * MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE, MapPanel.UNIT_SIZE);
                    graphics.setColor(Color.BLACK);
                }
            }
        }
    }

    boolean isOnGreySquare(int x, int y) {
        int row = y / MapPanel.UNIT_SIZE;
        int col = x / MapPanel.UNIT_SIZE;
        return shouldDrawRoad(row, col);
    }

    static boolean shouldDrawRoad(int row, int col) {
        return row % 4 == 0 || col % 4 == 0;
    }
}