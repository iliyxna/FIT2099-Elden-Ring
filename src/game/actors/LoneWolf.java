package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy {
//    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
        this.addCapability(Status.LONE_WOLF);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

}