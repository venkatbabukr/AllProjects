package venkat.java.samples.password.score.fn.spec;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialCharsScorerParams {
	
	private String permittedSpecialChars;
	
	private int minSpecialChars;

}
