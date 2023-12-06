import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapFrame mapFrame = new MapFrame();
            Phone phone = new Phone(mapFrame.getMapPanel());
        });
    }
}