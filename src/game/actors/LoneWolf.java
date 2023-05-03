package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Enemy;
import game.behaviours.Behaviour;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy {

    /**
     * Constructor for the lone wolf class.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
        this.addCapability(Status.LONE_WOLF);
        this.addCapability(Status.DOG_TYPE);
        this.setSelfType(Status.DOG_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
    }

    /**
     * Getter method for the intrinsic weapon used by lone wolf
     * @return the intrinsic weapon used by lone wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

}
