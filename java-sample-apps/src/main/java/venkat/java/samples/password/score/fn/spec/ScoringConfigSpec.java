package venkat.java.samples.password.score.fn.spec;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoringConfigSpec {

	private List<ScoringFunctionSpec> scoringFunctions;

}
