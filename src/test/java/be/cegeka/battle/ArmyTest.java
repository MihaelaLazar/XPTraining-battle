package be.cegeka.battle;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArmyTest {
	
	@Mock
	IHeadquarters headquarters;
	
	@Test
    public void Army_NoSoldierToArmy_ArmyHasNoFrontSoldier() {
        Army army = new Army(headquarters);
        
        Assertions.assertThat(army.getFrontSoldier()).isNull();
    }

    @Test
    public void Army_AddOneSoldierToArmy_ArmyHasFrontSoldier() {
        Soldier soldier = new Soldier("S1");
        Army army = new Army(headquarters);
        army.addSoldier(soldier);
        
        Assertions.assertThat(army.getFrontSoldier()).isEqualTo(soldier);
    }
    
    @Test
    public void Army_AddTwoSoldiersToArmy_ArmyHasFrontSoldier() {
        Soldier soldierS1 = new Soldier("S1");
        Soldier soldierS2 = new Soldier("S1");
        
        Army army = new Army(headquarters);
        army.addSoldier(soldierS1);
        army.addSoldier(soldierS2);
        
        Assertions.assertThat(army.getFrontSoldier()).isEqualTo(soldierS1);
    }
    
    @Test
    public void Army_AddMultipleSoldiersToArmy_ArmyHasFrontSoldier() {
        Soldier soldierS1 = new Soldier("S1");
        Soldier soldierS2 = new Soldier("S1");
        
        Army army = new Army(headquarters);
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
        Army army = new Army(headquarters);
        for (int i=0; i < nbOfSoldiers; i++)
        {
        	Soldier soldier = new Soldier(soldierPefix + i);
        	army.addSoldier(soldier);
        }
        
        return army;
	}
	
    @Test
    public void soldier_SoldierEnlisted_HQCalled() {
    	Soldier soldier = new Soldier("name");
    	Army army = new Army(headquarters);
    	army.addSoldier(soldier);
    	
    	verify(headquarters).reportEnlistment("name");
    }
    
    @Test
    public void soldier_SoldierEnlisted_HQCalledWithIdAssigned() {
    	Soldier soldier = new Soldier("name");
    	Army army = new Army(headquarters);
    	when(headquarters.reportEnlistment("name")).thenReturn(1000);
    	
    	army.addSoldier(soldier);
    	
    	verify(headquarters).reportEnlistment("name");
    	Assertions.assertThat(soldier.getId()).isEqualTo(1000);
    }

    @Test
    public void soldier_SoldierDies_HQCalledWIthSoldierID() {
        when(headquarters.reportEnlistment("B0")).thenReturn(1000);
        when(headquarters.reportEnlistment("A0")).thenReturn(2000);
        Army armyA = getArmy("A", 1);
        Army armyB = getArmy("B", 1);

        armyA.attack(armyB);

        verify(headquarters).reportCasualty(1000);
        verify(headquarters, never()).reportCasualty(2000);
    }

    @Test
    public void army_whenArmyWins_NumberOfRemainedSoldiersReturnedToHQ(){
        Army armyA = getArmy("A", 2);
        Army armyB = getArmy("B", 1);

        armyA.attack(armyB);

        verify(headquarters).reportVictory(2);
    }
}
