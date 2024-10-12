package venkat.java.samples.password.score.fn.factory;

import venkat.java.samples.password.score.fn.ScorerFunction;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;

public interface ScorerFunctionFactory {

	ScorerFunction create(ScoringFunctionSpec functionSpec);
	
}
