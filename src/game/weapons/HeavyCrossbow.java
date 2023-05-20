package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;
import game.transactions.Purchasable;
import game.transactions.Sellable;


/**
 * A class that represents a Heavy Crossbow
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 * @author Damia
 */
public class HeavyCrossbow extends WeaponItem implements Purchasable, Sellable{

    /**
     * Constructor for heavy crossbow
     */
    public HeavyCrossbow() {
        super("Heavy Crossbow", '}', 64, "bonks", 57);
    }

    /**
     * Getter method to retrieve the buying price of the item.
     * @return buying price of the item.
     */
    @Override
    public Rune getBuyPrice() {
        return new Rune(1500);
    }

    /**
     * Getter method to retrieve the selling price of the item.
     * @return selling price of the item.
     */
    @Override
    public Rune getSellPrice() {

        return new Rune(100);
    }
}