import java.awt.Color;
import java.util.ArrayList;

public class Board {
	
	private ArrayList<Territory> territories;
	private int players = Game.MAX_PLAYERS; // default number of players
	private int width,height;
	public Board(ArrayList<Territory> territories) {
		this.territories = territories;
		int xmin = Integer.MAX_VALUE;
		int xmax = Integer.MIN_VALUE; 
		int ymin = Integer.MAX_VALUE;
		int ymax = Integer.MIN_VALUE;
		for(Territory terr : territories) {
			tx = terr.getX();
			ty = terr.getY();
			xmax = (tx > xmax) ? tx : xmax;
			xmin = (tx < xmin) ? tx : xmin;
			ymax = (ty > ymax) ? ty : ymax;
			ymin = (ty < ymin) ? ty : ymin;
		}
		this.width = (xmax - xmin);
		this.height= (ymax - ymin);
	}
	/**
	 * @return width of the Board
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @return height of the Board
	 */
	public int getHeight() {
		return height;
	}
	/** 
	 * This method is a shorthand for inner ArrayList access
	 * @param i index in the territories AL
	 * @return Territory referenced by index
	 */
	public Territory getTerritory(int i) {
		return territories.get(i);
	}
	/**
	 * @return territories on the Board
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
}
