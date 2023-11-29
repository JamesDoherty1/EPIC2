import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class MapPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 825;
    static final int SCREEN_HEIGHT = 725;
    static final int UNIT_SIZE = 25;
    int personX;
    int personY;
    boolean running = false;
    Timer timer;
    Random random;
    Uber uberTaxi;
    Uber uberTaxi1;

    MapPanel() {
        random = new Random();
        uberTaxi = new Uber();
        uberTaxi1 = new Uber();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        newPerson();
        running = true;
        timer = new Timer(20, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                for (int j = 0; j < SCREEN_WIDTH / UNIT_SIZE; j++) {
                    graphics.drawRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

                    // Check if this square should be a road (you can define your own condition)
                    if (shouldDrawRoad(i, j)) {
                        graphics.setColor(Color.GRAY); // Set color to grey for roads
                        graphics.fillRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                        graphics.setColor(Color.BLACK); // Reset color to black for other elements
                    }
                }
            }

            graphics.setColor(Color.red);
            graphics.fillOval(personX, personY, UNIT_SIZE, UNIT_SIZE);
            uberTaxi.setColour(graphics);
            uberTaxi1.setColour(graphics);

        } else {
            gameOver(graphics);
        }
    }

    // Define your condition for drawing roads
    static boolean shouldDrawRoad(int row, int col) {
        // Add your condition here, for example, every second row or column is a road
        return row % 4 == 0 || col % 4 == 0;
    }

    public void newPerson() {
        personX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        personY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        while (!Taxi.isOnGreySquare(personX, personY)) {
            personX++;
            personY++;
        }
    }

    public void checkCollisions() {
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // Handle game over graphics if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            Taxi.move(uberTaxi, SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
            Taxi.move(uberTaxi1, SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
            checkCollisions();
        }
        repaint();
    }
}
