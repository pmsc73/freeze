import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class BoardCreator {
	private int players=1;
	public BoardCreator(int players) {
		this.players = players;
	}
	public ArrayList<Tile> getTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Scanner fsc = new Scanner("");
		try {
			fsc = new Scanner(new File("board2.dat"));
		}
		catch(FileNotFoundException f) {
			System.out.println(f.getMessage());
			System.exit(1);
		}
		while(fsc.hasNextLine()) {
			Tile t = new Tile((int)(Math.random()*players),fsc.nextLine());
			addTile(t,tiles);
			if(fsc.hasNextLine()) {
				Scanner line = new Scanner(fsc.nextLine());
				line.useDelimiter(",");
				while(line.hasNext()) {
					String name = line.next();
					Tile a = new Tile(0,name);
					addTile(a,tiles);
					setAdjacent(t.getName(),name,tiles);
				}
			}
		
		}
		return tiles;
	}
	public static void addTile(Tile t, ArrayList<Tile> tiles) {
		for(Tile tile : tiles) {
			if(tile.getName().equals(t.getName())) {
				return;
			}
		}
		tiles.add(t);
	}
	public static void setAdjacent(String name1,String name2, ArrayList<Tile> tiles) {
		for(Tile t : tiles) {
			if(t.getName().equals(name1)) {
				for(Tile a : tiles) {
					if(a.getName().equals(name2)) {
						t.setAdjacent(a);
						return;
					}
				}
			}
		}
		System.out.println("no such name");
	}
}
