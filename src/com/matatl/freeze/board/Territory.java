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
	
	/**
	 * Territory
	 * @param x,y: Coordinates of territory
	 * Creates a new object with x and y coordinates set
	 */
	public Territory(int x, int y) {
		this.x = x;
		this.y = y;
		owner = -1;
		unitsOnFront = new ArrayList<Integer>(Game.MAX_PLAYERS);
		transferredUnits = new ArrayList<Integer>(Game.MAX_PLAYERS);
	}
	/**
	 * isAdjacent
	 * @param other: Territory to test adjacency to
	 * @return whether or not the target is adjacent
	 * NOTE: this method is using HEXAGONAL base for calculations
	 */
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
	/**
	 * GETTERS
	 * getColor, getOwner, getTransfers, getUnitsOnFront 
	 */
	public String getColor() {
		if(owner<Game.MAX_PLAYERS)
			return Game.PLAYER_COLORS[owner];
		return "#000";
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
	/**
	 * size
	 * @return the number of OWNER units in territory
	 */
	public int size() {
		return unitsOnFront.get(owner);
	}
	/**
	 * reinforce
	 * @param count: the number of units to reinforce with
	 * This method is to use for transferring FRIENDLY --> FRIENDLY
	 */
	public void reinforce(int count) {
		Integer army = size();
		army+=count;
	}
	/**
	 * removeCasualties
	 * @param player: which player is having casualties removed
	 * @param casualties: how many units are being removed
	 * Removes units owned by the given player from the territory
	 */
	public void removeCasualties(int player, int casualties) {
		Integer pArmy = unitsOnFront.get(player);
		pArmy -= casualties;
		if(pArmy < 0) {
			pArmy = 0;
		}
	}
	/**
	 * transferUnits
	 * @param target: where you are moving units
	 * @param units: how many units you are moving
	 * Attempts to move the indicated number of units into target territory
	 */
	public void transferUnits(Territory target, int units) {
		if(units > size()) {
			// error in transfer
			return;
		}
		if(target.getOwner() == this.owner) {
			target.reinforce(units);
			Integer u = unitsOnFront.get(owner);
			u-=units;
		}
	}
	/**
	 * setOwner
	 * @param player
	 * Sets owner of territory to player
	 */
	public void setOwner(int player) {
		owner = player;
	}
	
	/**
	 * resolve 
	 * This method is invoked by the battle manager
	 * to implement the outcome of a battle on the tile
	 */
	public void resolveBattle() {
		battler.resolve(this);
	}
}
