import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainDAO {
    public boolean bookTrain(Train train) {
        String sql = "INSERT INTO reservation (name, age, train_number, seat_number, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, train.getName());
            pstmt.setInt(2, train.getAge());
            pstmt.setString(3, train.getTrainNumber());
            pstmt.setString(4, train.getSeatNumber());
            pstmt.setDate(5, java.sql.Date.valueOf(train.getDate()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
