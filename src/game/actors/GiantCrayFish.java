package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * Class representing the Giant Cray Fish.
 * @see Enemy
 * @author Damia
 */
public class GiantCrayFish extends Enemy{

    /**
     * Constructor for Enemy class.
     */
    public GiantCrayFish() {
        super("Giant CrayFish", 'R', 4803, 500, 2374);
        this.addCapability(Status.GIANT_CRAYFISH);
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
        return new IntrinsicWeapon(527, "slams",100);
    }
}
