package be.cegeka.battle;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Soldier {

    private String name;
    private Weapon weapon;
    private boolean isAlive = true;
    private int id;

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
        if (this.getWeaponDamage() < secondSoldier.getWeaponDamage()) {
            this.dies();
        } else {
            secondSoldier.dies();
        }
    }

    private void dies() {
        this.isAlive =  false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getWeaponDamage() {
        return weapon.getDamage();
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
