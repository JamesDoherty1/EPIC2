import javax.swing.*;
import java.awt.*;

public class TaxiAppGUI extends JFrame {

    private TaxiApp taxiApp;

    public TaxiAppGUI() {
        setContentPane(new TaxiApp.ImagePanel("TaxiApp.png", this));
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        taxiApp = new TaxiApp(this);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weightx = 1.0;
        frameConstraints.weighty = 1.0;
        getContentPane().add(taxiApp.getPanel(), frameConstraints);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaxiAppGUI().setVisible(true));
    }
}

