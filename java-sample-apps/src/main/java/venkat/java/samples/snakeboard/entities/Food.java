package venkat.java.samples.snakeboard.entities;

import java.util.Random;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import venkat.java.samples.snakeboard.config.GameConfig;
import venkat.java.samples.snakeboard.enums.BoardPixel;

@Slf4j
public class Food {

	private int maxIters;

	private Board gameBoard;

	@Getter
	private Point foodPoint;
	
	public Food(GameConfig cfg, Game game) {
		this.maxIters = cfg.getMaxApplePlacementAttempts();
		this.gameBoard = game.getGameBoard();
	}

    public boolean randomPlaceFood() {
        Random r = new Random();
        boolean foodPlaced = false;
        int iter = 0;
        for (iter = 0, foodPlaced = false; !foodPlaced && iter < maxIters ; iter++) {
        	Point randomPoint = new Point(r.nextInt(gameBoard.getSize()), r.nextInt(gameBoard.getSize()));
        	log.debug("Iteration {}, trying to place apple in board[{}]={}", iter, randomPoint, gameBoard.getBoardPix(randomPoint));
        	if (!randomPoint.equals(foodPoint)
        			&& gameBoard.checkAndSetBoardPix(BoardPixel.APPLE, randomPoint)) {
    			log.debug("Found apple placement coordinates {}, placing/relocating apple from {}", randomPoint, foodPoint);
        		if (foodPoint != null) {
        			log.debug("Clearing previous placement point: {}", foodPoint);
        			gameBoard.clearBoardPix(foodPoint);
        		}
        		foodPoint = randomPoint;
        		foodPlaced = true;
        	}
        }
        log.debug("randomPlaceApple(Iterations: {}, Food coordinates: {}, placed={}", iter, foodPoint, foodPlaced);
        return foodPlaced;
    }

}
