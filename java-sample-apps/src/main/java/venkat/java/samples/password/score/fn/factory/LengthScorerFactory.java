package venkat.java.samples.password.score.fn.factory;

import java.util.Map;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;

import venkat.java.samples.password.score.fn.ScorerFunction;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;

public class LengthScorerFactory implements ScorerFunctionFactory {

	public static final String MIN_LENGTH_SPEC_PARAM = "minLength";

	@Override
	public ScorerFunction create(ScoringFunctionSpec lengthScorerSpec) throws IllegalArgumentException {
		Map<String, Integer> specParamsMap = lengthScorerSpec.getSpecParams(new TypeToken<Map<String, Integer>>() {});
		int minPasswordLength = Optional.ofNullable(specParamsMap.get(MIN_LENGTH_SPEC_PARAM))
											.orElseThrow(() -> new IllegalArgumentException(String.format("%s spec param missing!", MIN_LENGTH_SPEC_PARAM)));
		return create(minPasswordLength);
	}

	public ScorerFunction create(int minPasswordLength) {
		return password -> password != null && password.length() > minPasswordLength ? 1 : 0;
	}

}
