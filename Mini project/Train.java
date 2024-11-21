import java.time.LocalDate;

public class Train {
    private String name;
    private int age;
    private String trainNumber;
    private String seatNumber;
    private LocalDate date;

    public Train(String name, int age, String trainNumber, String seatNumber, LocalDate date) {
        this.name = name;
        this.age = age;
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.date = date;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public LocalDate getDate() {
        return date;
    }
}
