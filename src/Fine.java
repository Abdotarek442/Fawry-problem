import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class Fine {

    private final String plateNumber;
    private final LocalDate date;
    private final List<Violation> violations;
    private final int totalAmount;

    public Fine(String plateNumber, LocalDate date, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.violations = Collections.unmodifiableList(violations);
        int sum = 0;
        for (Violation v : violations) {
            sum += v.getFee();
        }
        this.totalAmount = sum;
    }

    public String getPlateNumber() { return plateNumber; }
    public LocalDate getDate() { return date; }
    public List<Violation> getViolations() { return violations; }
    public int getTotalAmount() { return totalAmount; }

    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic fine for car ").append(plateNumber).append("\n\n");
        sb.append("Total amount: ").append(totalAmount).append(" EGP\n\n");
        sb.append("Violations:");
        for (Violation v : violations) {
            sb.append("\n- ").append(v.getDescription()).append(" : ")
                    .append(v.getFee()).append(" EGP");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return format();
    }
}
