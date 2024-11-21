public class BookingService {

    private TrainDAO trainDAO = new TrainDAO();  // Add TrainDAO instance

    public String[] getAvailableTrains() {
        // Simulate fetching available trains from a database or service
        return new String[]{"Train 101", "Train 102", "Train 103"};
    }

    public String[] getAvailableSeats(String trainNumber) {
        // Simulating fetching available seats for the selected train
        if ("Train 101".equals(trainNumber)) {
            return new String[]{"Seat 1", "Seat 2", "Seat 3"};
        } else if ("Train 102".equals(trainNumber)) {
            return new String[]{"Seat 4", "Seat 5", "Seat 6"};
        } else {
            return new String[]{"Seat 7", "Seat 8", "Seat 9"};
        }
    }

    public boolean bookTrain(Train train) {
        // Now this method uses the TrainDAO to insert the reservation into the database
        return trainDAO.bookTrain(train);
    }
}
