import java.awt.*;

class Map {

    private static final int NUM_BUILDINGS = 3;

    private int[][] buildingLocations = {
            {1, 5}, // Building 1 at row 2, col 2
            {13, 21}, // Building 2 at row 5, col 8
            {25, 9}  // Building 3 at row 8, col 5
    };

    void draw(Graphics graphics) {
        // Draw map grid
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

        // Draw buildings
        for (int k = 0; k < NUM_BUILDINGS; k++) {
            int x = buildingLocations[k][0] * MapPanel.UNIT_SIZE;
            int y = buildingLocations[k][1] * MapPanel.UNIT_SIZE;

            // Assign a fixed color to each building
            Color buildingColor;
            if (k == 0) {
                buildingColor = Color.RED;
            } else if (k == 1) {
                buildingColor = Color.BLUE;
            } else {
                buildingColor = Color.YELLOW;
            }

            graphics.setColor(buildingColor);
            graphics.fillRect(x, y, (MapPanel.UNIT_SIZE)*3, (MapPanel.UNIT_SIZE)*3);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, (MapPanel.UNIT_SIZE)*3, (MapPanel.UNIT_SIZE)*3);
        }
    }

    static boolean isOnGreySquare(int x, int y) {
        int row = y / MapPanel.UNIT_SIZE;
        int col = x / MapPanel.UNIT_SIZE;
        return shouldDrawRoad(row, col);
    }

    static boolean shouldDrawRoad(int row, int col) {
        return row % 4 == 0 || col % 4 == 0;
    }
}
