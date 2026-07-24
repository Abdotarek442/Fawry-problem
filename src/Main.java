import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Radar radar = new Radar();

        radar.addRule(new SeatbeltRule());
        radar.addRule(new SpeedLimitRule(CarType.PRIVATE, 80, 300));
        radar.addRule(new SpeedLimitRule(CarType.TRUCK, 60, 300));
        radar.addRule(new SpeedLimitRule(CarType.BUS, 70, 300));

        LocalDate today = LocalDate.now();

        Observation obs1 = new Observation("ABC1234", today, CarType.PRIVATE, 94, SeatbeltStatus.NOT_FASTENED);
        Observation obs2 = new Observation("XYZ987", today, CarType.TRUCK, 55, SeatbeltStatus.FASTENED);
        Observation obs3 = new Observation("TRK555", today, CarType.TRUCK, 72, SeatbeltStatus.FASTENED);
        Observation obs4 = new Observation("ABC1234", today, CarType.PRIVATE, 85, SeatbeltStatus.FASTENED);

        for (Observation obs : new Observation[]{obs1, obs2, obs3, obs4}) {
            Optional<Fine> fine = radar.processObservation(obs);
            if (fine.isPresent()) {
                System.out.println(fine.get().format());
                System.out.println();
            }
        }

        System.out.println("=== All fines (plate -> total amount) ===");
        for (Map.Entry<String, Integer> entry : radar.getAllFines().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " EGP");
        }

        System.out.println();
        System.out.println("=== Violated rules (rule -> count) ===");
        for (Map.Entry<String, Integer> entry : radar.getAllViolatedRules().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
