import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Phone extends JFrame implements ActionListener {

    private MapPanel mapPanel;
    private ImageIcon backgroundImage = new ImageIcon("src/main/java/TaxiApp.png");
    private JLabel backgroundLabel = new JLabel(backgroundImage);
    private JLabel question;
    private JTextArea driverInfo;
    private JButton redButton;
    private JButton blueButton;
    private JButton yellowButton;

    public Phone(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        this.add(backgroundLabel);
        this.setContentPane(backgroundLabel);
        setSize(429, 762);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(1025, 30);

        question = new JLabel("Where would you like to go?");
        this.add(question);
        question.setBounds(75, 100, 300, 80);
        question.setBackground(Color.BLACK);
        question.setFont(new Font("Fugaz One", Font.PLAIN, 20));

        driverInfo = new JTextArea(" \n Name: John \n \n Car Type: Volkswagen  \n \n License Plate: 123  \n \n Rating: 3 stars");
        this.add(driverInfo);
        driverInfo.setEditable(false);
        driverInfo.setBounds(60, 200, 300, 300);
        driverInfo.setBackground(Color.GREEN);
        driverInfo.setFont(new Font("Fugaz One", Font.PLAIN, 20));
        driverInfo.setVisible(false);

        // Create buttons
        redButton = new JButton("Red");
        this.add(redButton);
        redButton.setBounds(100, 200, 200, 80);
        redButton.setBackground(Color.RED);
        redButton.setFont(new Font("Fugaz One", Font.PLAIN, 40));
        redButton.addActionListener(this);

        blueButton = new JButton("Blue");
        this.add(blueButton);
        blueButton.setBounds(100, 350, 200, 80);
        blueButton.setBackground(Color.BLUE);
        blueButton.setFont(new Font("Fugaz One", Font.PLAIN, 40));
        blueButton.addActionListener(this);

        yellowButton = new JButton("Yellow");
        this.add(yellowButton);
        yellowButton.setBounds(100, 500, 200, 80);
        yellowButton.setFont(new Font("Fugaz One", Font.PLAIN, 40));
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(this);

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

        // Update UI after button press
        question.setText("Drivers Information");
        redButton.setVisible(false);
        blueButton.setVisible(false);
        yellowButton.setVisible(false);
        driverInfo.setVisible(true);
    }
}
