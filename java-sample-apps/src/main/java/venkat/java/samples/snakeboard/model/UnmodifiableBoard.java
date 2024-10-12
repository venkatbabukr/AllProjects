package venkat.java.samples.snakeboard.model;

import venkat.java.samples.snakeboard.entities.Board;
import venkat.java.samples.snakeboard.entities.Point;
import venkat.java.samples.snakeboard.enums.BoardPixel;

public class UnmodifiableBoard {

	private Board gameBoard;
	
	public UnmodifiableBoard(Board gb) {
		this.gameBoard = gb;
	}

	public BoardPixel getBoardPix(Point point) {
		return gameBoard.getBoardPix(point);
	}

	public BoardPixel[][] getPixArray() {
		BoardPixel[][] pixArr = gameBoard.getPixArray();
		// TODO: Perform deep copy before returning...
		return pixArr;
	}

	public boolean isPointInBoard(Point point) {
		return gameBoard.isPointInBoard(point);
	}

}
