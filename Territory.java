package freeze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Territory {
	private static BattleManager battler;
	
	private int x;
	private int y;
	private int owner;
	private ArrayList<Integer> unitsOnFront;
	private ArrayList<Integer> transferredUnits;
	public Territory(int x, int y) {
		this.x = x;
		this.y = y;
		owner = -1;
		unitsOnFront = new ArrayList<Integer>(Game.MAX_PLAYERS);
		transferredUnits = new ArrayList<Integer>(Game.MAX_PLAYERS);
	}
	public boolean isAdjacent(Territory other) {
		int diffX = this.x-other.x;
		int diffY = this.y-other.y;
		if(diffY == 0) {
			return (Math.abs(diffX) == 1); // other.x = this.x +- 1
		}
		if(Math.abs(diffY) == 1) {
			return (diffX == 0 || (this.x%2==0 && diffX == this.x-1) || (this.x%2==1 && diffX==this.x+1));
			
		}
		return false;
	}
	public String getColor() {
		if(owner<Game.MAX_PLAYERS)
			return Game.PLAYER_COLORS[owner];
		return "#000";
	}
	
	public void reinforce(int count) {
		Integer army = unitsOnFront.get(owner);
		army+=count;
	}
	
	public void removeCasualties(int player, int casualties) {
		Integer pArmy = unitsOnFront.get(player);
		pArmy -= casualties;
		if(pArmy < 0) {
			pArmy = 0;
		}
	}
	
	public void setOwner(int player) {
		owner = player;
	}
	public int getOwner() {
		return owner;
	}
	
	public List<Integer> getTransfers() {
		return transferredUnits;
	}
	public List<Integer> getUnitsOnFront() {
		return unitsOnFront;
	}
	public void resolveBattle() {
		battler.resolve(this);
	}
}
