import java.awt.Color;
import java.util.ArrayList;

public class Board {
	
	private static final int VAL_NONE =   0x6; // used for colors
	private static final int VAL_HIGH = 0xC; // used for colors
	public static final Color[] COLORS = {
		new Color(VAL_NONE,VAL_NONE,VAL_NONE), // black
		new Color(VAL_NONE,VAL_NONE,VAL_HIGH), // blue
		new Color(VAL_NONE,VAL_HIGH,VAL_NONE), // green
		new Color(VAL_HIGH,VAL_NONE,VAL_NONE), // red
		new Color(VAL_NONE,VAL_HIGH,VAL_HIGH), // cyan
		new Color(VAL_HIGH,VAL_NONE,VAL_HIGH), // yellow
		new Color(VAL_HIGH,VAL_HIGH,VAL_NONE) // magenta
	};
	public static final int MAX_PLAYERS = 6;
	private int players;
		
	
	private ArrayList<Tile> tiles;
	public Board(int players) {
		this.players = players;
		tiles = new BoardCreator(players).getTiles();
	}
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
}
