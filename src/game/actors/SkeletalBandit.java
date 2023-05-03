package game.actors;

import game.utils.Status;
import game.weapons.Scimitar;

public class SkeletalBandit extends Enemy{

    /**
     * Constructor for Skeletal Bandit class.
     */

    public SkeletalBandit() {
        super("Skeletal Bandit", 'b',184, 35, 892);
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        this.addCapability(Status.SKELETAL_TYPE);
        this.setSelfType(Status.SKELETAL_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);

        // add Scimitar to inventory
        this.addWeaponToInventory(new Scimitar());
    }

}
