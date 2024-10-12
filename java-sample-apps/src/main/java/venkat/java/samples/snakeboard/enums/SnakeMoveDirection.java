package venkat.java.samples.snakeboard.enums;

import lombok.Getter;
import venkat.java.samples.snakeboard.entities.Point;

@Getter
public enum SnakeMoveDirection {
	UP(new Point(-1, 0)),
    DOWN(new Point(1, 0)),
    LEFT(new Point(0, -1)),
    RIGHT(new Point(0, 1));

	private Point adjustCoordinates;

	private SnakeMoveDirection(Point ac) {
		this.adjustCoordinates = ac;
	}

}
