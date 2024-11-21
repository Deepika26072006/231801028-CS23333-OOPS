import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
public class ReservationUI {
    private BookingService bookingService = new BookingService();

    public ReservationUI() {
        JFrame frame = new JFrame("Railway Reservation");
        frame.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> trainComboBox = new JComboBox<>();
        JComboBox<String> seatComboBox = new JComboBox<>();
        JTextField dateField = new JTextField();

        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Age:"));
        frame.add(ageField);
        frame.add(new JLabel("Train Number:"));
        frame.add(trainComboBox);
        frame.add(new JLabel("Seat Number:"));
        frame.add(seatComboBox);
        frame.add(new JLabel("Date (YYYY-MM-DD):"));
        frame.add(dateField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        // Populate train combo box when the frame is loaded
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String[] availableTrains = bookingService.getAvailableTrains();
                trainComboBox.setModel(new DefaultComboBoxModel<>(availableTrains));
            }
        });

        // Update seat combo box when a train is selected
        trainComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTrain = (String) trainComboBox.getSelectedItem();
                String[] availableSeats = bookingService.getAvailableSeats(selectedTrain);
                seatComboBox.setModel(new DefaultComboBoxModel<>(availableSeats));
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String trainNumber = (String) trainComboBox.getSelectedItem();
                String seatNumber = (String) seatComboBox.getSelectedItem();
                String dateString = dateField.getText();

                // Convert string date to LocalDate
                LocalDate date = LocalDate.parse(dateString);

                // Create Train object with the data
                Train train = new Train(name, age, trainNumber, seatNumber, date);

                // Book the train and show success/failure message
                if (bookingService.bookTrain(train)) {
                    JOptionPane.showMessageDialog(frame, "Reservation Successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Reservation Failed.");
                }
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ReservationUI();
    }
}
