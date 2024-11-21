import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Railway Reservation");
        JButton bookButton = new JButton("Book Train");

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReservationUI();
            }
        });

        frame.add(bookButton);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
