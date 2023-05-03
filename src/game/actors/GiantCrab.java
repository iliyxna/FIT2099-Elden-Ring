package game.actors;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * Class representing the Giant Crab.
 * @see Enemy
 */
public class GiantCrab extends Enemy {

    /**
     * Constructor for Giant Crab class.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407,
                318,4961);
        this.addCapability(Status.GIANT_CRAB);
        this.addCapability(Status.WATER_TYPE);
        this.setSelfType(Status.WATER_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
    }

    /**
     * Getter method for the intrinsic weapon used by the Giant crab.
     * @return the intrinsic weapon used by the Giant crab.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "claws",90);
    }
}
