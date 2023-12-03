import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Phone extends JFrame implements ActionListener {

    private MapPanel mapPanel;

    public Phone(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton button = new JButton("Collect Me");
        button.addActionListener(this);

        // Add the JButton to the JFrame
        getContentPane().add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Collect Me")) {
            // Access taxis directly from MapPanel
            Collection.getClosestTaxi(mapPanel.getTaxis());
        }
    }
}
