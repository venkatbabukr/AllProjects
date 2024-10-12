package venkat.java.samples.password.score.fn;

import java.util.Optional;

import venkat.java.samples.password.score.fn.factory.HistoryMatchScorerFactory;
import venkat.java.samples.password.score.fn.factory.LengthScorerFactory;
import venkat.java.samples.password.score.fn.factory.ScorerFunctionFactory;
import venkat.java.samples.password.score.fn.factory.SpecialCharsScorerFactory;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;

public enum PasswordScoreFunctionsRegistry {
	LCASE(new PatternMatchScorer("[a-z]"), null),
    UCASE(new PatternMatchScorer("[A-Z]"), null),
    SPCHAR(null, new SpecialCharsScorerFactory()),
    LENGTH(null, new LengthScorerFactory()),
    HISTORY(null, new HistoryMatchScorerFactory());

	private ScorerFunction scorerFunction;
	
	private ScorerFunctionFactory scorerFunctionFactory;
	
	private PasswordScoreFunctionsRegistry(ScorerFunction scf, ScorerFunctionFactory scff) {
		scorerFunction = scf;
		scorerFunctionFactory = scff;
	}

	public static ScorerFunction getScorer(ScoringFunctionSpec sfSpec) {
		return Optional
					.ofNullable(sfSpec)
					.map(ScoringFunctionSpec::getName)
					.map(sfName -> PasswordScoreFunctionsRegistry.valueOf(sfName.toUpperCase()))
					.map(sfEnum -> {
						return sfEnum.scorerFunction != null ? 
									sfEnum.scorerFunction :
									sfEnum.scorerFunctionFactory.create(sfSpec);
					})
					.orElse(null);
	}

}
