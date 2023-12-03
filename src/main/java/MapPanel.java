// MapPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MapPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 825;
    static final int SCREEN_HEIGHT = 725;
    static final int UNIT_SIZE = 25;
    boolean running = false;
    Timer timer;
    private ArrayList<Taxi> taxis;
    private TaxiListener taxiListener;
    Map map;
    private Person person;
    private Phone phone;

    MapPanel(Map map, TaxiListener taxiListener) {
        this.map = map;
        this.taxis = new ArrayList<>();
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

        this.taxiListener = taxiListener;
        this.phone = new Phone(this); // passing 'this.taxiListener' as TaxiListener
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

        graphics.setColor(Color.black); // Reset the color
    }


    public void gameOver(Graphics g) {
        // Handle game over graphics if needed
    }

    public void setTaxiListener(TaxiListener listener) {
        this.taxiListener = listener;
    }
    public ArrayList<Taxi> getTaxis() {
        return taxis;
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
