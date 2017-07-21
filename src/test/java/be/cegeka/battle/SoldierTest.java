package be.cegeka.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SoldierTest {

    @Test
    public void construction_ASoldierMustHaveANameAndBeAlive() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getName()).isEqualTo("name");
        assertThat(soldier.isAlive()).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeNull() {
        new Soldier(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeEmpty() {
        new Soldier("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeBlank() {
        new Soldier("    ");
    }

    @Test
    public void construction_ASoldierMustHaveDefaultWeapon_ItMustBeBearFist() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getWeapon()).isEqualTo(Weapon.BARE_FIST);
    }

    @Test
    public void attack_FirstSoldierWithWeakerWeapon_dies() {
        Soldier firstSoldier = new Soldier("name");
        Soldier secondSoldier = new Soldier("name", Weapon.AXE);

        firstSoldier.attack(secondSoldier);
        assertThat(firstSoldier.isAlive()).isFalse();
        assertThat(secondSoldier.isAlive()).isTrue();
    }

    @Test
    public void attack_FirstSoldierWithStrongerWeapon_wins() {
        Soldier firstSoldier = new Soldier("name", Weapon.AXE);
        Soldier secondSoldier = new Soldier("name");

        firstSoldier.attack(secondSoldier);
        assertThat(firstSoldier.isAlive()).isTrue();
        assertThat(secondSoldier.isAlive()).isFalse();
    }
}