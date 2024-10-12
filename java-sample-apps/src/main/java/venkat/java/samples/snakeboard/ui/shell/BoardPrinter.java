package venkat.java.samples.snakeboard.ui.shell;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import venkat.java.samples.snakeboard.enums.BoardPixel;
import venkat.java.samples.snakeboard.model.UnmodifiableBoard;
import venkat.java.samples.snakeboard.ui.shell.enums.BoardPixelCharMapper;

public class BoardPrinter {

	public void printBoard(UnmodifiableBoard gameBoard) {
		if (gameBoard != null) {
			BoardPixel[][] pixArray = gameBoard.getPixArray();
			String boardMatrixStr =
				Arrays.stream(pixArray)
					.map(pixRow -> Arrays.stream(pixRow)
											.map(pix -> BoardPixelCharMapper.INSTANCE.map(pix))
											.map(pixC -> pixC != null ? pixC.getDisplayChar() : " ")
											.collect(Collectors.joining("", "|", "|")))
					.collect(Collectors.joining(System.lineSeparator()));

			String boardHBorder = new String("-").repeat(pixArray.length + 2);
			String boardPrintStr = new StringJoiner(System.lineSeparator())
										.add(boardHBorder)
										.add(boardMatrixStr)
										.add(boardHBorder)
										.toString();
			System.out.println(boardPrintStr);
		}
	}
	
}
