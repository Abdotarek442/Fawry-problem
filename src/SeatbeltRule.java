import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeatbeltRule implements Rule {

    private static final String NAME = "Seatbelt";
    private final int fee;

    public SeatbeltRule() {
        this(100);
    }

    public SeatbeltRule(int fee) {
        this.fee = fee;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Violation> check(Observation observation) {
        if (observation.getSeatbeltStatus() == SeatbeltStatus.NOT_FASTENED) {
            List<Violation> violations = new ArrayList<>();
            violations.add(new Violation(NAME, "Seatbelt not fastned", fee));
            return violations;
        }
        return Collections.emptyList();
    }
}
