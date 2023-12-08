import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaxiAppGUI extends JFrame {

    private TaxiApp taxiApp;
    private JButton depositButton;
    private JLabel amountLabel;
    private double currentAmount = 0;

    // Constructor for the TaxiAppGUI class
    public TaxiAppGUI() throws IOException {
        // Set the content pane with a background image
        setContentPane(new TaxiApp.ImagePanel("TaxiApp.png", this));
        setSize(400, 700);  // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation when the frame is closed

        // Create an instance of the TaxiApp class
        taxiApp = new TaxiApp(this);

        // Set the layout of the frame using GridBagLayout
        getContentPane().setLayout(new GridBagLayout());

        // Center the main box in the frame
        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weightx = 1.0;
        frameConstraints.weighty = 1.0;
        getContentPane().add(taxiApp.getPanel(), frameConstraints);

        // Add the deposit button on top with the amount label beside it
        depositButton = new JButton("Deposit");
        depositButton.setPreferredSize(new Dimension(120, 40));
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositMoney();
            }
        });

        // Set constraints for the deposit button
        GridBagConstraints depositButtonConstraints = new GridBagConstraints();
        depositButtonConstraints.anchor = GridBagConstraints.NORTH;
        depositButtonConstraints.gridx = 0;
        depositButtonConstraints.gridy = 0;
        depositButtonConstraints.insets = new Insets(150, 100, 100, 100);
        getContentPane().add(depositButton, depositButtonConstraints);

        // Create a label to display the current amount
        amountLabel = new JLabel("$" + currentAmount);
        amountLabel.setForeground(Color.WHITE);

        // Set constraints for the amount label
        GridBagConstraints amountLabelConstraints = new GridBagConstraints();
        amountLabelConstraints.anchor = GridBagConstraints.NORTH;
        amountLabelConstraints.gridx = 1;
        amountLabelConstraints.gridy = 0;
        amountLabelConstraints.insets = new Insets(20, -60, 10, 10);
        getContentPane().add(amountLabel, amountLabelConstraints);

        // Set the location of the frame to the center of the screen
        setLocationRelativeTo(null);
    }

    // Method to handle depositing money
    private void depositMoney() {
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
    private void updateAmountLabel() {
        amountLabel.setText("$" + currentAmount);
    }

    // Main method to create and display the TaxiAppGUI frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TaxiAppGUI().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
