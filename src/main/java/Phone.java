import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Phone extends JFrame implements ActionListener {

    private MapPanel mapPanel;

    public Phone(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton redButton = new JButton("Red");
        redButton.addActionListener(this);

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(this);

        JButton yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(this);

        // Add the JButtons to the JFrame
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(redButton);
        getContentPane().add(blueButton);
        getContentPane().add(yellowButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = e.getActionCommand();

        switch (buttonLabel) {
            case "Red":
                Collection.getClosestTaxi(mapPanel.getTaxis(), "red");
                break;
            case "Blue":
                Collection.getClosestTaxi(mapPanel.getTaxis(), "blue");
                break;
            case "Yellow":
                Collection.getClosestTaxi(mapPanel.getTaxis(), "yellow");
                break;
        }
    }
}
