import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
class TaxiApp {
    private JTextField locationField;
    private JTextArea resultArea;
    private JTextField destinationField;
    private JPanel panel;
    private JFrame parentFrame;

    public TaxiApp(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
    }

    private void initComponents() {
        locationField = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        destinationField = new JTextField();

        JButton findTaxisButton = new JButton("Find Taxis");
        findTaxisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findTaxis();
            }
        });

        // Use GridBagLayout to center components
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints location = new GridBagConstraints();
        location.insets = new Insets(10, 10, 10, 10);
        location.fill = GridBagConstraints.HORIZONTAL;

        // Enter Location
        location.gridx = 0;
        location.gridy = 0;
        location.gridwidth = 2;
        panel.add(new JLabel("Enter Location:"), location);

        location.gridx = 0;
        location.gridy = 1;
        location.gridwidth = 2;
        locationField.setPreferredSize(new Dimension(300, 40));
        panel.add(locationField, location);

        // Enter Destination
        location.gridx = 0;
        location.gridy = 2;
        location.gridwidth = 2;
        panel.add(new JLabel("Enter Destination:"), location);

        location.gridx = 0;
        location.gridy = 3;
        location.gridwidth = 2;
        destinationField.setPreferredSize(new Dimension(300, 40));
        panel.add(destinationField, location);

        // Find Taxis Button
        location.gridx = 0;
        location.gridy = 4;
        location.gridwidth = 2;
        panel.add(findTaxisButton, location);
    }

    private List<Driver> readDriversFromFile(String filePath) {
        List<Driver> drivers = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0];
                    String carType = parts[1];
                    String licensePlate = parts[2];
                    double rating = Double.parseDouble(parts[3]);
                    double distance = Double.parseDouble(parts[4]);
                    drivers.add(new Driver(name, carType, licensePlate, rating, distance));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    private void findTaxis() {
        String location = locationField.getText().trim();
        String destination = destinationField.getText().trim();

        if (location.isEmpty() || destination.isEmpty()) {
            displayErrorMessage("Please enter both location and destination.");
            return;
        }

        List<Driver> drivers = readDriversFromFile("/options.txt");

        resultArea.setText("Available Taxis:\n");
        for (int i = 0; i < drivers.size(); i++) {
            resultArea.append((i + 1) + ". " + drivers.get(i).toString() + "\n");
        }

        JComboBox<Driver> driverComboBox = new JComboBox<>(drivers.toArray(new Driver[0]));
        driverComboBox.setSelectedIndex(-1);

        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Select a Driver:"));
        selectionPanel.add(driverComboBox);

        int option = JOptionPane.showConfirmDialog(parentFrame, selectionPanel, "Select Driver", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Driver selectedDriver = (Driver) driverComboBox.getSelectedItem();
            displayMessage("Your driver, " + selectedDriver.getName() +
                    ", will be with you soon. Destination: " + destination);
        }
    }

    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
        locationField.setForeground(Color.RED);
        destinationField.setForeground(Color.RED);
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getPanel() {
        return panel;
    }

    static class ImagePanel extends JPanel {
        private BufferedImage backgroundImage;

        public ImagePanel(String imagePath, JFrame parentFrame) {
            try {
                this.backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}


