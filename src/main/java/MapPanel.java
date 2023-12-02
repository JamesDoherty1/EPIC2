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
    Taxi taxi1;
    Taxi taxi2;
    Taxi taxi3;
    Taxi taxi4;
    Taxi taxi5;
    Taxi taxi6;
    Map map;
    Person person;

    MapPanel() {
        map = new Map();
        taxi1 = new Big(map);
        taxi2 = new Big(map);
        taxi3 = new Medium(map);
        taxi4 = new Medium(map);
        taxi5 = new Small(map);
        taxi6 = new Small(map);
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
            taxi1.draw(graphics);
            taxi2.draw(graphics);
            taxi3.draw(graphics);
            taxi4.draw(graphics);
            taxi5.draw(graphics);
            taxi6.draw(graphics);
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
            taxi1.move();
            taxi2.move();
            taxi3.move();
            taxi4.move();
            taxi5.move();
            taxi6.move();
        }
        repaint();
    }
}
