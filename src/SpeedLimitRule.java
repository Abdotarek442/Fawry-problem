import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeedLimitRule implements Rule {

    private static final String NAME = "Speed";

    private final CarType applicableType;
    private final int maxSpeed;
    private final int fee;

    public SpeedLimitRule(CarType applicableType, int maxSpeed, int fee) {
        this.applicableType = applicableType;
        this.maxSpeed = maxSpeed;
        this.fee = fee;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Violation> check(Observation observation) {
        if (observation.getCarType() == applicableType && observation.getSpeed() > maxSpeed) {
            List<Violation> violations = new ArrayList<>();
            String description = "speed of " + observation.getSpeed()
                    + " exceeded max allowed " + maxSpeed;
            violations.add(new Violation(NAME, description, fee));
            return violations;
        }
        return Collections.emptyList();
    }
}
