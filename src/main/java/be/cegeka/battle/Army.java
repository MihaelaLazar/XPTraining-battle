package be.cegeka.battle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Army {

	List<Soldier> soldiers = new LinkedList<>();

	public void addSoldier(Soldier soldier) {
		soldiers.add(soldier);
	}

	public Soldier getFrontSoldier() {
		return soldiers.get(0);
	}

	public void addSoldiers(Soldier... soldiersToAdd) {
		soldiers.addAll(Arrays.asList(soldiersToAdd));
	}
}
