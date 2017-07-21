package be.cegeka.battle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Army {

	List<Soldier> soldiers = new LinkedList<>();
	IHeadquarters headquarters;
	
	public Army(IHeadquarters headquarters) {
		this.headquarters = headquarters;
	}

	public void addSoldier(Soldier soldier) {
		addSoldiers(soldier);
	}

	public Soldier getFrontSoldier() {
		return soldiers.size() > 0 ? soldiers.get(0) : null;
	}

	public void addSoldiers(Soldier... soldiersToAdd) {
		soldiers.addAll(Arrays.asList(soldiersToAdd));
		for (Soldier s : soldiersToAdd) {
			s.setId(headquarters.reportEnlistment(s.getName()));
		}
	}

	public Army attack(Army otherArmy) {
		while (moreSoldierToKill(otherArmy)) {
			this.getFrontSoldier().attack(otherArmy.getFrontSoldier());
			solveArmyState();
			otherArmy.solveArmyState();
		}
		headquarters.reportVictory(getWinner(otherArmy).getSoldiersCount());
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
		Soldier soldier = this.soldiers.remove(0);
		this.headquarters.reportCasualty(soldier.getId());
	}
}
