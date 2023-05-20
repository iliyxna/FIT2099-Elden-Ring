package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * Class representing the Dog.
 * @see Enemy
 * @author Damia
 */
public class Dog extends Enemy{
    /**
     * Constructor for Dog class.
     */
    public Dog() {
        super("Dog", 'a',104,52, 1390);
        this.addCapability(Status.DOG);
        this.addCapability(Status.SC_CREATURES_TYPE);
        this.setSelfType(Status.SC_CREATURES_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
    }

    /**
     * Getter method for the intrinsic weapon used by Dog
     * @return the intrinsic weapon used by Dog
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

}