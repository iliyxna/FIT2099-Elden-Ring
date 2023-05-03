package game.actors;
import game.utils.Status;
import game.weapons.Grossmesser;


/**
 * Class representing the Heavy skeletal swordsman.
 * HSS carries a Grossmesser at all times.
 * @see Enemy
 */
public class HeavySkeletalSwordsman extends Enemy {

    /**
     * Constructor for the Heavy skeletal swordsman class.
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q',153,
                35,892);
        // add Grossmesser to inventory
        this.addWeaponToInventory(new Grossmesser());

        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        this.addCapability(Status.SKELETAL_TYPE);
        this.setSelfType(Status.SKELETAL_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
    }

}

