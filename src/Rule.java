import java.util.List;

public interface Rule {

    String getName();

    List<Violation> check(Observation observation);
}
