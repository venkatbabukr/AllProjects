package venkat.java.samples.password.score.fn;

import java.util.Objects;

import venkat.java.samples.password.score.util.PatternMatchCounter;

public class PatternMatchScorer implements ScorerFunction {

	private PatternMatchCounter matchCounter;
	
	public PatternMatchScorer(String matchPatternStr) {
		Objects.requireNonNull(matchPatternStr, "Matching pattern string required!");
		matchCounter = new PatternMatchCounter(matchPatternStr);
	}
	
	@Override
	public int score(String password) {
		return (int)matchCounter.patternMatchCount(password);
	}

}
