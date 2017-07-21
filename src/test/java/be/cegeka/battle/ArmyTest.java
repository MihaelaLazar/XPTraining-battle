package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ArmyTest {

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
}
