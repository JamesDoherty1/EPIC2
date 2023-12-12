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
    private JButton depositButton; // New button for deposit
    private JLabel amountLabel; // New label to display the current amount
    private double currentAmount = 0; // Current amount in the user's account

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
        driverInfo.setBounds(60, 200, 300, 350);
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

        // New deposit button
        depositButton = new JButton("Deposit");
        this.add(depositButton);
        depositButton.setBounds(250, 10, 150, 40);
        depositButton.setBackground(Color.CYAN);
        depositButton.setFont(new Font("Fugaz One", Font.PLAIN, 20));
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositMoney(50.0); // Call method to handle depositing money
            }
        });

        // New label to display the current amount
        amountLabel = new JLabel("$" + currentAmount);
        this.add(amountLabel);
        amountLabel.setBounds(250, 50, 150, 40);
        amountLabel.setBackground(Color.CYAN);
        amountLabel.setFont(new Font("Fugaz One", Font.PLAIN, 20));

        setVisible(true);
    }

    // Method to handle depositing money
    public void depositMoney(double v) {
        // Prompt the user to enter a deposit amount
        String depositAmountString = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        try {
            // Parse the input to a double and update the current amount
            double depositAmount = Double.parseDouble(depositAmountString);
            currentAmount += depositAmount;
            // Update the amount label to display the new amount
            updateAmountLabel();
        } catch (NumberFormatException | NullPointerException e) {
            // Handle invalid input with an error message
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update the amount label with the current amount
    void updateAmountLabel() {
        amountLabel.setText("$" + currentAmount);
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
                String formattedInfo = String.format("\nName: %s\n\nCar Type: %s\n\nNumber Plate: %s\n\nRating: %s\n\nCar Size: %s\n\nPrice: $%s",
                        info[0], info[1], info[2], info[3], info[4], info[5]); // Added info[5] for price
                driverInfo.setText(formattedInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            depositButton.setVisible(false);
            amountLabel.setVisible(false);
            driverInfo.setVisible(true);
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

    public JButton getDepositButton() {
        return depositButton;
    }

    public void setDepositButton(JButton depositButton) {
        this.depositButton = depositButton;
    }

    public JLabel getAmountLabel() {
        return amountLabel;
    }

    public void setAmountLabel(JLabel amountLabel) {
        this.amountLabel = amountLabel;
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

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
