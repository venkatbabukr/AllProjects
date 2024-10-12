package venkat.java.samples.password.score.fn.factory;

import venkat.java.samples.password.score.fn.ScorerFunction;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;

public class HistoryMatchScorerFactory implements ScorerFunctionFactory {

	public static final String MIN_HISTORY_SPEC_PARAM = "minDistinctPwds";

	@Override
	public ScorerFunction create(ScoringFunctionSpec functionSpec) throws IllegalArgumentException {
		return null;
	}

}
