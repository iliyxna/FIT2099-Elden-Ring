package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.rune.Rune;
import game.transactions.Purchasable;
import game.transactions.Sellable;
import game.utils.Status;

/**
 * A class that represents a Scimitar
 * @see WeaponItem
 * @see Sellable
 * @see Purchasable
 * @author Damia
 */
public class Scimitar extends WeaponItem implements Purchasable,Sellable{
    /**
     * Constructor for Scimitar.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slices", 88);
        this.addCapability(Status.ENEMY_WEAPON_SKILL);
    }

    /**
     * Getter method to retrieve the buying price of the item.
     * @return buying price of the item.
     */
    @Override
    public Rune getBuyPrice() {
        return new Rune(600);
    }

    /**
     * Getter method to retrieve the selling price of the item.
     * @return selling price of the item.
     */
    public Rune getSellPrice(){
        return new Rune(100);
    }

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill against one targeted Actor
     * @param target target actor
     * @param direction direction of the attack
     * @return a new area attack action
     */
    public Action getSkill(Actor target, String direction){
        return new AreaAttackAction(this);
    }
}
