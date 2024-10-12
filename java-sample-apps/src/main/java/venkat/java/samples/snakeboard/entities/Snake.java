package venkat.java.samples.snakeboard.entities;

import java.util.LinkedList;

import venkat.java.samples.snakeboard.config.GameConfig;
import venkat.java.samples.snakeboard.enums.BoardPixel;
import venkat.java.samples.snakeboard.enums.SnakeMoveDirection;

public class Snake {

	private Point snakeHead;
	private LinkedList<Point> snakePoints;
	private SnakeMoveDirection moveDirection;

	private Board gameBoard;
	private Food gameFood;
	
	public Snake(GameConfig cfg, Game game) {
		gameBoard = game.getGameBoard();
		gameFood = game.getGameFood();

		int sSize = cfg.getSnakeSize();
		snakePoints = new LinkedList<>();
		for (int c = 0; c < sSize ; c++) {
			Point p = new Point(0, c);
			snakePoints.addFirst(p);
			gameBoard.setBoardPix(BoardPixel.SNAKE_BODY, p);
		}
		snakeHead = snakePoints.getFirst();
		gameBoard.setBoardPix(BoardPixel.SNAKE_HEAD, snakeHead);

		moveDirection = SnakeMoveDirection.RIGHT;
	}

	public SnakeMoveDirection getMoveDirection() {
		return moveDirection;
	}

	public synchronized void setMoveDirection(SnakeMoveDirection dir) {
		moveDirection = dir;
	}

	public synchronized int moveOneStep() {
		Point nextHeadPoint = snakeHead.adjustBy(moveDirection.getAdjustCoordinates());
		
		int foodScore = 0;
		
		if (gameBoard.isPointInBoard(nextHeadPoint) && !snakePoints.contains(nextHeadPoint)) {
			boolean foodPointScored = nextHeadPoint.equals(gameFood.getFoodPoint());
			if (foodPointScored) {
				foodScore++;
			} else {
				// Snake doesn't get elongated, so, remove the tail of snake.
				Point pruneSnakeTail = snakePoints.removeLast();
				gameBoard.clearBoardPix(pruneSnakeTail);
			}
			gameBoard.setBoardPix(BoardPixel.SNAKE_BODY, snakeHead);
			snakePoints.addFirst(nextHeadPoint);

			gameBoard.setBoardPix(BoardPixel.SNAKE_HEAD, nextHeadPoint);
			snakeHead = nextHeadPoint;
		} else {
			// Snake collision happened!
		}
		return foodScore;
	}

}
