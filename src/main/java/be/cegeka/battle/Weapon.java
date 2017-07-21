package be.cegeka.battle;

/**
 * Created by mihaelal on 21.07.2017.
 */
public enum Weapon {

    BARE_FIST(1),
    AXE(3),
    SWORD(2),
    SPEAR(2);

    private int damage;

    Weapon(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
