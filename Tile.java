import java.awt.Color;
import java.util.ArrayList;

public class Tile {
	private String name;
	private Color color;
	private int owner;
	private int[] units;
	private int[] transferred;
	private ArrayList<Tile> adjacents; 
	public Tile(int owner,String name) {
		this.name = name;
		this.owner = owner;
		this.color = Board.COLORS[owner];
		this.units = new int[Board.MAX_PLAYERS];
		this.transferred = new int[Board.MAX_PLAYERS];
		this.adjacents = new ArrayList<Tile>();
	}
	public Color getColor() {
		return color;
	}
	public String getName() {
		return name;
	}
	public int getOwner() {
		return owner;
	}
	public void setAdjacent(Tile t) {
		this.adjacents.add(t);
	}
	public ArrayList<Tile> getAdjacents() {
		return this.adjacents;
	}
	public ArrayList<Integer> getUnits() {
		ArrayList<Integer> unl = new ArrayList<Integer>();
		for(int u : units) {
			unl.add(new Integer(u));
		}
		return unl;
	}
	public ArrayList<Integer> getTransferred() {
		ArrayList<Integer> trans = new ArrayList<Integer>();
		for(int t : transferred) {
			trans.add(new Integer(t));
		}
		return trans;
	}
}
