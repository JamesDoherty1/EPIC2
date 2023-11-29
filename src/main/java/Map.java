import java.awt.*;

class Map {
    static final int UNIT_SIZE = 25;

    void draw(Graphics graphics) {
        for (int i = 0; i < MapPanel.SCREEN_HEIGHT / UNIT_SIZE; i++) {
            for (int j = 0; j < MapPanel.SCREEN_WIDTH / UNIT_SIZE; j++) {
                graphics.drawRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

                if (MapPanel.shouldDrawRoad(i, j)) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    graphics.setColor(Color.BLACK);
                }
            }
        }
    }

    boolean isOnGreySquare(int x, int y) {
        int row = y / UNIT_SIZE;
        int col = x / UNIT_SIZE;
        return shouldDrawRoad(row, col);
    }

    private static boolean shouldDrawRoad(int row, int col) {
        return row % 4 == 0 || col % 4 == 0;
    }
}
