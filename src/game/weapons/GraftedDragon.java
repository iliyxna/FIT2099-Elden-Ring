package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.transactions.Exchangeable;
import game.rune.Rune;
import game.transactions.Sellable;
import game.utils.Status;

/**
 * A class that represents a Grafted Dragon.
 * @see WeaponItem
 * @see Sellable
 * @see Exchangeable
 * @see AreaAttackAction
 * @author Iliyana
 */
public class GraftedDragon extends WeaponItem implements Sellable, Exchangeable {
    /**
     * Constructor for Grafted Dragon.
     *
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "hits", 90);
        this.addCapability(Status.ENEMY_WEAPON_SKILL);
        this.addCapability(Status.UNIQUE_SKILL);
    }

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill against one targeted Actor.
     * @param target target actor
     * @param direction direction of the attack
     * @return a new Area attack action
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(this);
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
