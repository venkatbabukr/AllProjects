package venkat.java.samples.snakeboard.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;
import venkat.java.samples.snakeboard.config.GameConfig;
import venkat.java.samples.snakeboard.model.UnmodifiableBoard;
import venkat.java.samples.snakeboard.model.UnmodifiableFood;
import venkat.java.samples.snakeboard.ui.GameUI;
import venkat.java.samples.snakeboard.ui.shell.ShellGameUI;

@Slf4j
public class Game {

	private GameConfig gameConfig;
	
	private Board gameBoard;
	private Food gameFood;
	private Snake gameSnake;
	
	private int score;

	private GameUI gameUI;

	private Timer snakeMovementJobTimer;

	public Game() {
	    this(new GameConfig(), ShellGameUI.class);
		log.debug("Created game {} using default config {} as no config given", gameBoard.getId(), gameConfig.getId());
	}
	
	public Game(GameConfig cfg, Class<? extends GameUI> guiClass) {
		this.gameConfig = cfg;
		log.debug("Game config: {}", this.gameConfig);

		gameBoard = new Board(cfg);
		gameFood = new Food(gameConfig, this);
		gameSnake = new Snake(cfg, this);
		
		score = 0;
		
		try {
			gameUI = guiClass.getConstructor(Game.class).newInstance(this);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			log.error("Encountered exception when creating Game UI instance! ", e);
			throw new IllegalStateException("Encountered problem when creating Game UI!");
		}
		snakeMovementJobTimer = new Timer();
	}

	Board getGameBoard() {
		return gameBoard;
	}

	public UnmodifiableBoard getUnmodifiableGameBoard() {
		return new UnmodifiableBoard(gameBoard);
	}
	
	Food getGameFood() {
		return gameFood;
	}
	
	public UnmodifiableFood getUnmodifiableGameFood() {
		return new UnmodifiableFood(gameFood);
	}

	Snake getGameSnake() {
		return gameSnake;
	}

	public int getScore() {
		return score;
	}

	public void start() {
		log.debug("Placing food");
		gameFood.randomPlaceFood();
		log.debug("Showing game on UI!");
		gameUI.refreshDisplay();

		log.debug("Scheduling snake movement timed job to run in {} intervals", gameConfig.getSnakeMovementTaskInterval());
		snakeMovementJobTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				log.debug("Running Snake movement timed task");
				score += gameSnake.moveOneStep();
				log.debug("Refreshing display!");
				gameUI.refreshDisplay();
			}

		}, 0, gameConfig.getSnakeMovementTaskInterval().toMillis());
	}

	public static void main(String[] args) {
		Game g1 = new Game();
		g1.start();
	}

}
