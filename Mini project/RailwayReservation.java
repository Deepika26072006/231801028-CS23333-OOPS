import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RailwayReservation {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Railway Reservation");

        // Create labels and text fields for user input
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(3);
        JLabel trainLabel = new JLabel("Train Number:");
        JTextField trainField = new JTextField(10);

        // Create a submit button
        JButton submitButton = new JButton("Submit");

        // Set layout for the frame and add components
        frame.setLayout(new java.awt.GridLayout(4, 2));
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(trainLabel);
        frame.add(trainField);
        frame.add(submitButton);

        // Add action listener for the button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String ageStr = ageField.getText().trim();
                String trainNumber = trainField.getText().trim();

                // Validate inputs
                if (name.isEmpty() || ageStr.isEmpty() || trainNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for age.");
                    return;
                }

                // Connect to MySQL and insert data
                try {
                    // Load the MySQL JDBC driver (optional for newer versions but good for explicit clarity)
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Establish connection
                    Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/railway", "root", ""
                    );

                    // Prepare SQL statement
                    String query = "INSERT INTO reservations (name, age, train_number) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);
                    pstmt.setString(3, trainNumber);

                    // Execute the insert operation
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(frame, "Reservation Confirmed!");
                    }

                    // Close the connection
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error connecting to the database: " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "MySQL Driver not found: " + ex.getMessage());
                }
            }
        });

        // Configure frame settings
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}