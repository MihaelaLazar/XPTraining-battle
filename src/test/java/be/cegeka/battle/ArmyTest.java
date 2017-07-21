package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ArmyTest {
	
	@Test
    public void Army_NoSoldierToArmy_ArmyHasNoFrontSoldier() {
        Army army = new Army();
        
        Assertions.assertThat(army.getFrontSoldier()).isNull();
    }

    @Test
    public void Army_AddOneSoldierToArmy_ArmyHasFrontSoldier() {
        Soldier soldier = new Soldier("S1");
        Army army = new Army();
        army.addSoldier(soldier);
        
        Assertions.assertThat(army.getFrontSoldier()).isEqualTo(soldier);
    }
    
    @Test
    public void Army_AddTwoSoldiersToArmy_ArmyHasFrontSoldier() {
        Soldier soldierS1 = new Soldier("S1");
        Soldier soldierS2 = new Soldier("S1");
        
        Army army = new Army();
        army.addSoldier(soldierS1);
        army.addSoldier(soldierS2);
        
        Assertions.assertThat(army.getFrontSoldier()).isEqualTo(soldierS1);
    }
    
    @Test
    public void Army_AddMultipleSoldiersToArmy_ArmyHasFrontSoldier() {
        Soldier soldierS1 = new Soldier("S1");
        Soldier soldierS2 = new Soldier("S1");
        
        Army army = new Army();
        army.addSoldiers(soldierS1, soldierS2);
        
        Assertions.assertThat(army.getFrontSoldier()).isEqualTo(soldierS1);
    }
    
    @Test
    public void Army_attackArmyOfOneSoldier_ThanWinnerIsAttacker() {
    	Army armyA = getArmy("A", 1);
    	Army armyB = getArmy("B", 1);
    	
    	Army winner = armyA.attack(armyB);
    	
        Assertions.assertThat(winner.getFrontSoldier()).isNotNull();
        Assertions.assertThat(winner).isEqualTo(armyA);
        Assertions.assertThat(armyB.getFrontSoldier()).isNull();
    }
    
    @Test
    public void Army_attackArmyOfTwoSoldiers_ThanWinnerIsAttacker() {
    	Army armyA = getArmy("A", 2);
    	Army armyB = getArmy("B", 2);
    	
    	Army winner = armyA.attack(armyB);
    	
        Assertions.assertThat(winner.getFrontSoldier()).isNotNull();
        Assertions.assertThat(winner).isEqualTo(armyA);
        Assertions.assertThat(armyB.getFrontSoldier()).isNull();
    }

	private Army getArmy(String soldierPefix, int nbOfSoldiers) {       
        Army army = new Army();
        for (int i=0; i < nbOfSoldiers; i++)
        {
        	Soldier soldier = new Soldier(soldierPefix + i);
        	army.addSoldier(soldier);
        }
        
        return army;
	}
}
