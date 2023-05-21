package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * Class representing the Giant Dog.
 * @see Enemy
 * @author Damia
 */
public class GiantDog extends Enemy{

    /**
     * Constructor for GiantDog class.
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 313, 1808);
        this.addCapability(Status.GIANT_DOG);
        this.addCapability(Status.DOG_TYPE);
        this.setSelfType(Status.DOG_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
    }

    /**
     * Getter method for the intrinsic weapon used by the Giant crab.
     * @return the intrinsic weapon used by the Giant crab.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams",90);
    }
}
