package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;
import game.utils.Status;

/**
 * A class representing an Astrologer Staff.
 * Note that the Ranged attack is not implemented. It acts like a normal melee weapon.
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 * @author Mustafa Khan
 */
public class AstrologerStaff extends WeaponItem implements Sellable, Purchasable{
    /**
     * Constructor for Astrologer Staff class.
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', 274, "zaps", 50);
        this.addCapability(Status.RANGED_ATTACK);
    }

    /**
     * Getter method to retrieve the buying price of the item
     * @return buying price of the item
     */
    @Override
    public Rune getBuyPrice() {
        return new Rune(800);
    }

    /**
     * Getter method to retrieve the selling price of the item.
     * @return selling price of the item
     */
    @Override
    public Rune getSellPrice() {
        return new Rune(100);
    }

}
