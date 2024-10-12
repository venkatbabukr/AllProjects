package venkat.java.samples.password.score.fn.spec;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoringFunctionSpec {

	private String name;
	private int weight;
	private String specParams;
	
	public <T> T getSpecParams(Class<T> specParamsClass) {
		return new Gson().fromJson(specParams, specParamsClass);
	}
	
	public <T> T getSpecParams(TypeToken<T> specParamsTypeToken) {
		return new Gson().fromJson(specParams, specParamsTypeToken);
	}

}
