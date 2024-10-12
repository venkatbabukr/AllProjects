package venkat.java.samples.password.score;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import venkat.java.samples.password.score.fn.PasswordScoreFunctionsRegistry;
import venkat.java.samples.password.score.fn.ScorerFunction;
import venkat.java.samples.password.score.fn.spec.ScoringConfigSpec;
import venkat.java.samples.password.score.fn.spec.ScoringFunctionSpec;

@Slf4j
public class PasswordScorer {
	
	private ScoringConfigSpec scoringConfig;

	public PasswordScorer(ScoringConfigSpec spec) {
		scoringConfig = spec;
	}
	public double score(String password) {
		int totalScore = scoringConfig.getScoringFunctions()
							.stream()
							.mapToInt(sfSpec -> {
								int sfScore = 0;
								ScorerFunction scorer = PasswordScoreFunctionsRegistry.getScorer(sfSpec);
								if (scorer != null) {
									sfScore = scorer.score(password);
								}
								int weightedSfScore = sfSpec.getWeight() * sfScore;
								log.debug("weightedSfScore({}, {})={}", password, sfSpec.getName(), weightedSfScore);
								return weightedSfScore;
							})
							.sum();
		int totalWeight = scoringConfig.getScoringFunctions().stream().mapToInt(ScoringFunctionSpec::getWeight).sum();
		log.debug("Total weight: {}", totalWeight);
		return (1.0 * totalScore) / totalWeight;
	}

	public static void main(String[] args) {
		List<ScoringFunctionSpec> allScoringFuncSpec = new ArrayList<>();
		allScoringFuncSpec.add(ScoringFunctionSpec.builder()
									.name(PasswordScoreFunctionsRegistry.LCASE.name().toLowerCase())
									.weight(2)
									.build());
		allScoringFuncSpec.add(ScoringFunctionSpec.builder()
									.name(PasswordScoreFunctionsRegistry.UCASE.name().toLowerCase())
									.weight(1)
									.build());

		ScoringConfigSpec configSpec = ScoringConfigSpec.builder()
											.scoringFunctions(allScoringFuncSpec)
											.build();
		PasswordScorer scorer = new PasswordScorer(configSpec);
		String password = "#$94aA";
		double totalScore = scorer.score(password);
		System.out.format("For ...%s, totalScore: %s", password, totalScore);
	}

}
