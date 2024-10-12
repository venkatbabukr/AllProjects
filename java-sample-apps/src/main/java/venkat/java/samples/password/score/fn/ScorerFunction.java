package venkat.java.samples.password.score.fn;

@FunctionalInterface
public interface ScorerFunction {

	int score(String password);

}
