import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Phone extends JFrame implements ActionListener {

    private MapPanel mapPanel;
    private ImageIcon backgroundImage = new ImageIcon("src/main/java/TaxiApp.png");
    private JLabel backgroundLabel = new JLabel(backgroundImage);
    private JLabel question;
    private static JTextArea driverInfo;
    private JButton redButton;
    private JButton blueButton;
    private JButton yellowButton;

    private boolean selectingColor = true;
    private String selectedColor;

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

        driverInfo = new JTextArea();
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
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setFont(new Font("Fugaz One", Font.PLAIN, 40));
        yellowButton.addActionListener(this);

        setVisible(true);
    }

    public static void displayInfo(int index) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/options.txt"))) {
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Check if the index is within bounds
            if (index >= 0 && index < lines.size()) {
                String[] info = lines.get(index).split(",");
                String formattedInfo = String.format("\nName: %s\n\nCar Type: %s\n\nNumber Plate: %s\n\nRating: %s\n\nCar Size: %s",
                        info[0], info[1], info[2], info[3], info[4]);
                driverInfo.setText(formattedInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    public void setBackgroundLabel(JLabel backgroundLabel) {
        this.backgroundLabel = backgroundLabel;
    }

    public JLabel getQuestion() {
        return question;
    }

    public void setQuestion(JLabel question) {
        this.question = question;
    }

    public static JTextArea getDriverInfo() {
        return driverInfo;
    }

    public static void setDriverInfo(JTextArea driverInfo) {
        Phone.driverInfo = driverInfo;
    }

    public JButton getRedButton() {
        return redButton;
    }

    public void setRedButton(JButton redButton) {
        this.redButton = redButton;
    }

    public JButton getBlueButton() {
        return blueButton;
    }

    public void setBlueButton(JButton blueButton) {
        this.blueButton = blueButton;
    }

    public JButton getYellowButton() {
        return yellowButton;
    }

    public void setYellowButton(JButton yellowButton) {
        this.yellowButton = yellowButton;
    }

    public boolean isSelectingColor() {
        return selectingColor;
    }

    public void setSelectingColor(boolean selectingColor) {
        this.selectingColor = selectingColor;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = e.getActionCommand();

        if (selectingColor) {
            // User is selecting color
            selectedColor = buttonLabel.toLowerCase();
            redButton.setText("Small");
            blueButton.setText("Medium");
            yellowButton.setText("Big");
            selectingColor = false;
        } else {
            // User is selecting size, call Collection.getClosestTaxi with color and size
            String selectedSize = buttonLabel.toLowerCase();
            Collection.getClosestTaxi(mapPanel.getTaxis(), selectedColor, selectedSize);

            // Update UI after button press
            question.setText("Drivers Information");
            redButton.setVisible(false);
            blueButton.setVisible(false);
            yellowButton.setVisible(false);
            driverInfo.setVisible(true);
        }
    }

}
