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
		while (moreSoldierToKill(otherArmy)) {
			this.getFrontSoldier().attack(otherArmy.getFrontSoldier());
			solveArmyState();
			otherArmy.solveArmyState();
		}

		return getWinner(otherArmy);
	}

	private Army getWinner(Army otherArmy) {
		if (this.getSoldiersCount() == 0)
			return otherArmy;
		
		return this;
	}

	private void solveArmyState() {
		if (!this.getFrontSoldier().isAlive()) {
			removeFrontSoldier();
		}
	}

	private boolean moreSoldierToKill(Army otherArmy) {
		return getSoldiersCount() > 0 && otherArmy.getSoldiersCount() > 0;
	}

	private int getSoldiersCount() {
		return this.soldiers.size();
	}

	private void removeFrontSoldier() {
		this.soldiers.remove(0);
	}
}
