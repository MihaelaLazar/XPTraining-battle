package be.cegeka.battle;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Soldier {

    private String name;
    private Weapon weapon;
    private boolean isAlive = true;

    public Soldier(String name) {
        Validate.isTrue(isNotBlank(name));

        this.name = name;
        this.weapon = Weapon.BARE_FIST;
    }

    public Soldier(String name, Weapon weapon) {
        this(name);
        this.weapon = weapon;
    }

    String getName() {
        return this.name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void attack(Soldier secondSoldier) {
        if (weapon.getDamage() > secondSoldier.getWeapon().getDamage()) {
            secondSoldier.died();
        } else this.died();
    }

    private void died() {
        this.isAlive =  false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
