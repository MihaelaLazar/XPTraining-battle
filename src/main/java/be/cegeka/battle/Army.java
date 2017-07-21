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
		return soldiers.size() > 0 ? soldiers.get(0) : null;
	}

	public void addSoldiers(Soldier... soldiersToAdd) {
		soldiers.addAll(Arrays.asList(soldiersToAdd));
	}

	public Army attack(Army otherArmy) {
		this.getFrontSoldier().attack(otherArmy.getFrontSoldier());
		
		if (!this.getFrontSoldier().isAlive()) {
			removeFrontSoldier();
		}
		
		if (!otherArmy.getFrontSoldier().isAlive()) {
			otherArmy.removeFrontSoldier();
		}
		
		return this;
	}

	private void removeFrontSoldier() {
		this.soldiers.remove(0);
	}
}
