package venkat.java.samples.password.score.fn;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import venkat.java.samples.password.score.fn.spec.SpecialCharsScorerParams;
import venkat.java.samples.password.score.util.PatternMatchCounter;

@Slf4j
public class SpecialCharsMatchScorer implements ScorerFunction {

	private int minReqdSpecialChars;
	
	private PatternMatchCounter matchCounter;

	public SpecialCharsMatchScorer(SpecialCharsScorerParams scorerParams) {
		minReqdSpecialChars = scorerParams.getMinSpecialChars();
		String matchPatternStr = Arrays.stream(scorerParams.getPermittedSpecialChars().split(""))
									.map(spCh -> String.format("\\%s", spCh))
									.collect(Collectors.joining("|"));
		matchCounter = new PatternMatchCounter(matchPatternStr);
	}

	@Override
	public int score(String password) {
		long pwNumSpecialChars = matchCounter.patternMatchCount(password);
		int finalScore = pwNumSpecialChars > minReqdSpecialChars ? (int) pwNumSpecialChars : 0;
		log.debug("#specialCharCount({})={}, minReqdSpecialChars={}. score = {}", password, pwNumSpecialChars, minReqdSpecialChars, finalScore);
		return finalScore;
	}

}
