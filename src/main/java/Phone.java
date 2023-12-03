import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Phone extends JFrame implements ActionListener {

    private TaxiListener taxiListener;

    public Phone(TaxiListener taxiListener) {
        this.taxiListener = taxiListener;
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton button = new JButton("Collect Me");
        button.addActionListener(this); // Add ActionListener to the button

        // Add the JButton to the JFrame
        getContentPane().add(button);

        setVisible(true);

        // Create instances of Person and Uber taxi

        // Set the instances in the Collection class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Collect Me")) {
            // Call the method in Collection when the button is pressed
            taxiListener.onCollectPressed();
        }
    }
}