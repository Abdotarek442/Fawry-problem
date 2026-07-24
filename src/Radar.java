import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Radar {

    private final List<Rule> rules = new ArrayList<>();
    private final List<Fine> issuedFines = new ArrayList<>();

    public Radar() {
    }

    public Radar(List<Rule> rules) {
        this.rules.addAll(rules);
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public Optional<Fine> processObservation(Observation observation) {
        List<Violation> violations = new ArrayList<>();
        for (Rule rule : rules) {
            violations.addAll(rule.check(observation));
        }

        if (violations.isEmpty()) {
            return Optional.empty();
        }

        Fine fine = new Fine(observation.getPlateNumber(), observation.getDate(), violations);
        issuedFines.add(fine);
        return Optional.of(fine);
    }

    public List<Fine> getIssuedFines() {
        return new ArrayList<>(issuedFines);
    }

    public Map<String, Integer> getAllFines() {
        Map<String, Integer> summary = new LinkedHashMap<>();
        for (Fine fine : issuedFines) {
            summary.merge(fine.getPlateNumber(), fine.getTotalAmount(), Integer::sum);
        }
        return summary;
    }

    public Map<String, Integer> getAllViolatedRules() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Fine fine : issuedFines) {
            for (Violation v : fine.getViolations()) {
                counts.merge(v.getRuleName(), 1, Integer::sum);
            }
        }
        return counts;
    }
}
