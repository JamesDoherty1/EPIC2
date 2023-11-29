import javax.swing.*;

class MapFrame extends JFrame {

    MapFrame() {
        this.add(new MapPanel());
        this.setTitle("Map");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

