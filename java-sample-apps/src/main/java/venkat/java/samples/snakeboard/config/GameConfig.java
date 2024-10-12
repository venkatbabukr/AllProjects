package venkat.java.samples.snakeboard.config;

import java.time.Duration;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class GameConfig {

	private final UUID id = UUID.randomUUID();
	private final int boardSize = 10;
	private final int maxApplePlacementAttempts = 5;

	// See how we can put assertion to validate that snake size is less than board
	// size at beginning...
	private final int snakeSize = 5;
	private final Duration snakeMovementTaskInterval = Duration.ofSeconds(1);

}
