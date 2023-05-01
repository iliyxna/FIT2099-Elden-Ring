package game.actors;

import game.utils.Status;
import game.weapons.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy {

    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q',153,
                35,892);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        this.setSelfType(Status.SKELETAL_TYPE);
    }

}