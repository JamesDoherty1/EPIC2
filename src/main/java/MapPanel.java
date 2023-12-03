import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MapPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 825;
    static final int SCREEN_HEIGHT = 725;
    static final int UNIT_SIZE = 25;
    boolean running = false;
    Timer timer;
    ArrayList<Taxi> taxis;
    Map map;
    Person person;

    MapPanel() {
        map = new Map();
        taxis = new ArrayList<>();
        taxis.add(new Big(map));
        taxis.add(new Big(map));
        taxis.add(new Medium(map));
        taxis.add(new Medium(map));
        taxis.add(new Small(map));
        taxis.add(new Small(map));
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
            for (Taxi taxi : taxis) {
                taxi.draw(graphics);
            }
        } else {
            gameOver(graphics);
        }
    }

    public void gameOver(Graphics g) {
        // Handle game over graphics if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            for (Taxi taxi : taxis) {
                taxi.move();
            }
        }
        repaint();
    }
}