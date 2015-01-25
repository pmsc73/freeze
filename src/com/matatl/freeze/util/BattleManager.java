package freeze;

import java.util.List;


public class BattleManager {
	private static int KILLING_ROLL = 4;
	
	/**
	 * Rolls a d6, return values from 0-5 inclusive
	 * @return random[0,5]
	 */
	public int roll() {
		return (int)(Math.random()*6);
	}
	
	/**
	 * Returns an integer player who is being targeted on terr
	 * where the player doing the targeting is specified by current
	 * @param current The player who is targeting
	 * @param terr The territory on which battle is taking place
	 * @return A target for the player
	 */
	private int getTarget(int current,Territory terr) {
		return (int)(Math.random()*(terr.getUnitsOnFront().size()));
	}
	
	/**
	 * Main method for BattleManager, this code calculates the 
	 * results of a battle on a given territory
	 * @param terr The battlefield territory
	 */
	public void resolve(Territory terr) {
		// this array holds flags for each player corresponding to their inclusion in battle
		boolean[] contesting = new boolean[Game.MAX_PLAYERS];
		// battling flag is given a true value whenever there is a battle ongoing
		boolean battling = false;
		// owner is always considered contesting the territory
		contesting[terr.getOwner()] = true;
		for(int i=0;i<Game.MAX_PLAYERS;i++) {
			if(terr.getUnitsOnFront().get(i) > 0 && i!=terr.getOwner()) {
				battling=true;
				// set battling to true if there is units not belonging to owner on the front
				contesting[i] = true;
			}
			else {
				contesting[i] = false;
			}
		}
		/*
		 * Main battle loop
		 */
		while(battling) {
			// kills[i] is the number of kills for player i
			int kills[] = new int[Game.MAX_PLAYERS];
			// units[i] is the unit count for player i
			int units[] = new int[Game.MAX_PLAYERS];
			
			// initialize values for units[]
			for(int i=0;i<Game.MAX_PLAYERS;i++) {
				units[i]=terr.getUnitsOnFront().get(i);
			}
			for(int p=0;p<contesting.length;p++) {
				// Current player = p
				for(int u=0;u<units[p];u++) {
					// Current unit from army p = u
					int roll = roll();
					if(roll>KILLING_ROLL) { // kill confirmed
						// D6 roll equivalent: 5,6
						int target = getTarget(p,terr);
						kills[p]++;
						units[target]--;
					}
				}
			}
			// The following logic resets battling if need be // 
			battling=false;
			for(int i=0;i<contesting.length;i++) {
				if(terr.getUnitsOnFront().get(i) > 0) {
					contesting[i] = true;
				}
				else {
					contesting[i] = false;
				}
			}
			int contestants = 0;
			for(boolean c : contesting) {
				contestants += (c)?1:0;
			}
			battling=(contestants>1);
		}
		// calculate new owner of territory 
		for(int p=0;p<Game.MAX_PLAYERS;p++) {
			if(terr.getUnitsOnFront().get(p) >0) {
				terr.setOwner(p);
			}
		}
	}
}
