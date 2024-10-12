package venkat.java.samples.snakeboard.ui.shell.enums;

import java.util.EnumMap;
import java.util.Map;

import venkat.java.samples.snakeboard.enums.BoardPixel;

public enum BoardPixelCharMapper {
	INSTANCE;
	
	private EnumMap<BoardPixel, BoardPixelChar> pixToCharMap;

	private BoardPixelCharMapper() {
		pixToCharMap = new EnumMap<>(
							Map.of(
								BoardPixel.SNAKE_HEAD, BoardPixelChar.O,
								BoardPixel.SNAKE_BODY, BoardPixelChar.X,
								BoardPixel.APPLE, BoardPixelChar.A));
	}

	public BoardPixelChar map(BoardPixel pix) {
		return pixToCharMap.get(pix);
	}

}
