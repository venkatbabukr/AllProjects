package venkat.java.samples.password.score.fn.factory;

import venkat.java.samples.password.score.fn.ScorerFunction;
import venkat.java.samples.password.score.fn.SpecialCharsMatchScorer;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;
import venkat.java.samples.password.score.fn.spec.SpecialCharsScorerParams;

public class SpecialCharsScorerFactory implements ScorerFunctionFactory {

	@Override
	public ScorerFunction create(ScoringFunctionSpec functionSpec) {
		SpecialCharsScorerParams scorerParams = functionSpec.getSpecParams(SpecialCharsScorerParams.class);
		return new SpecialCharsMatchScorer(scorerParams);
	}

	public static void main(String[] args) {
		ScorerFunction spCharsScoringFn = new SpecialCharsScorerFactory()
												.create(ScoringFunctionSpec.builder()
															.name("specialChars")
															.specParams("{\"permittedSpecialChars\": \"$^%#@\"}")
															.build());
		System.out.println(spCharsScoringFn.score("Adien&*&$^#*"));
	}
}
