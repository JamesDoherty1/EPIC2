import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
class MapPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 825;
    static final int SCREEN_HEIGHT = 725;
    static final int UNIT_SIZE = 25;
    boolean running = false;
    Timer timer;
    Taxi uberTaxi;
    Taxi uberTaxi1;
    Map map;
    Person person;

    MapPanel() {
        map = new Map();
        uberTaxi = new Uber(map);
        uberTaxi1 = new Uber(map);
        person = new Person(map);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        person.newPerson();
        running = true;
        timer = new Timer(20, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        map.draw(graphics);
        if (running) {
            graphics.setColor(Color.red);
            person.draw(graphics);
            uberTaxi.draw(graphics);
            uberTaxi1.draw(graphics);
        } else {
            gameOver(graphics);
        }
    }

    static boolean shouldDrawRoad(int row, int col) {
        return row % 4 == 0 || col % 4 == 0;
    }

    public void gameOver(Graphics g) {
        // Handle game over graphics if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            uberTaxi.move();
            uberTaxi1.move();
        }
        repaint();
    }
}