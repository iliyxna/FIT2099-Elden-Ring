package game.actors;

import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.HeavyCrossbow;

/**
 * Class representing the Godrick Soldier
 * @see Enemy
 * @author Damia
 */
public class GodrickSoldier extends Enemy{
    /**
     * Constructor for Godrick Soldier class.
     */
    public GodrickSoldier() {
        super("Godrick Soldier",'p',198,38, 70);
        this.addCapability(Status.GODRICK_SOLDIER);
        this.addCapability(Status.SC_CREATURES_TYPE);
        this.setSelfType(Status.SC_CREATURES_TYPE);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
        // add HeavyCrossbow to inventory
        this.addWeaponToInventory(new HeavyCrossbow());
    }


}