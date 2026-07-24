import java.time.LocalDate;

public final class Observation {
    private final String plateNumber;
    private final LocalDate date;
    private final CarType carType;
    private final int speed;
    private final SeatbeltStatus seatbeltStatus;

    public Observation(String plateNumber, LocalDate date, CarType carType,
                        int speed, SeatbeltStatus seatbeltStatus) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speed = speed;
        this.seatbeltStatus = seatbeltStatus;
    }

    public String getPlateNumber() { return plateNumber; }
    public LocalDate getDate() { return date; }
    public CarType getCarType() { return carType; }
    public int getSpeed() { return speed; }
    public SeatbeltStatus getSeatbeltStatus() { return seatbeltStatus; }
}
