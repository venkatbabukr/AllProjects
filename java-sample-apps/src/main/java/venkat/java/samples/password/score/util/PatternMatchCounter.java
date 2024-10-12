package venkat.java.samples.password.score.util;

import java.util.Objects;
import java.util.regex.Pattern;

public class PatternMatchCounter {

	private Pattern matchPattern = null;
	
	public PatternMatchCounter(String matchPatternStr) {
		Objects.requireNonNull(matchPatternStr, "Matching pattern string required!");
		matchPattern = Pattern.compile(matchPatternStr);
	}

	public long patternMatchCount(String password) {
		return matchPattern.matcher(password).results().count();
	}

}
