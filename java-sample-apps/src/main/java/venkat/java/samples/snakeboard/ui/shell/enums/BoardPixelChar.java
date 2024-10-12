package venkat.java.samples.snakeboard.ui.shell.enums;

import lombok.Getter;

@Getter
public enum BoardPixelChar {
    X("x"), O("o"), A("a");

	private String displayChar;
	
	private BoardPixelChar(String c) {
		this.displayChar = c;
	}
}
