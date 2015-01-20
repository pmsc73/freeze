package freeze;

import java.util.List;


public class BattleManager {
	private static int KILLING_ROLL = 4;
	public int roll() {
		return (int)(Math.random()*6);
	}
	private int getTarget(int current,Territory terr) {
		return (int)(Math.random()*(terr.getUnitsOnFront().size()));
	}
	public void resolve(Territory terr) {
		boolean[] contesting = new boolean[Game.MAX_PLAYERS];
		boolean battling = false;
		contesting[terr.getOwner()] = true;
		for(int i=0;i<Game.MAX_PLAYERS;i++) {
			if(terr.getUnitsOnFront().get(i) > 0 && i!=terr.getOwner()) {
				battling=true;
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
			int kills[] = new int[Game.MAX_PLAYERS];
			int units[] = new int[Game.MAX_PLAYERS];
			for(int i=0;i<Game.MAX_PLAYERS;i++) {
				units[i]=terr.getUnitsOnFront().get(i);
			}
			for(int p=0;p<contesting.length;p++) {
				// Current player = p
				for(int u=0;u<units[p];u++) {
					// Current unit from army p = u
					int roll = roll();
					if(roll>KILLING_ROLL) {
						// D6 roll equivalent: 5,6
						int target = getTarget(p,terr);
						kills[p]++;
						units[target]--;
					}
				}
			}
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
	}
}
