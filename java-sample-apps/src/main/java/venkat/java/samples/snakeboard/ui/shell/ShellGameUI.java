package venkat.java.samples.snakeboard.ui.shell;

import venkat.java.samples.snakeboard.entities.Game;
import venkat.java.samples.snakeboard.ui.GameUI;

public class ShellGameUI implements GameUI {

	private Game game;

	private BoardPrinter printer;

	public ShellGameUI(Game game) {
		this.game = game;
		printer = new BoardPrinter();
	}

	@Override
	public void refreshDisplay() {
		printer.printBoard(game.getUnmodifiableGameBoard());
		System.out.println(String.format("Score: %d", game.getScore()));
	}

}
