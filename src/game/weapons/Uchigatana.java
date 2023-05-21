package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAttackAction;
import game.rune.Rune;
import game.transactions.Purchasable;
import game.transactions.Sellable;
import game.utils.Status;

/**
 * A class that represents an Uchigatana
 * @see WeaponItem
 * @see UnsheatheAttackAction
 * @see Sellable
 * @see Purchasable
 * @author Damia
 * Modified by: Iliyana
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor for Uchigatana.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        this.addCapability(Status.UNIQUE_SKILL);
    }

    /**
     * Getter method to retrieve the buying price of the item.
     * @return buying price of the item.
     */
    @Override
    public Rune getBuyPrice() {
        return new Rune(5000);
    }

    /**
     * Getter method to retrieve the selling price of the item.
     * @return selling price of the item.
     */
    @Override
    public Rune getSellPrice() {
        return new Rune(500);
    }


    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill against one targeted Actor
     * @param target target actor
     * @param direction direction of the attack
     * @return a new Unsheathe attack action
     */
    @Override
    public Action getSkill(Actor target, String direction){
        return new UnsheatheAttackAction(target, direction, this);
    }
}
