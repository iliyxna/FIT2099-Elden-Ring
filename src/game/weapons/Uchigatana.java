package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAttackAction;
import game.rune.Rune;
import game.utils.Status;

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor for Uchigatana.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        this.addCapability(Status.UNIQUE_SKILL);
    }

    @Override
    public Rune getBuyPrice() {
        return new Rune(5000);
    }

    @Override
    public Rune getSellPrice() {
        return new Rune(500);
    }


    @Override
    public Action getSkill(Actor target, String direction){
        return new UnsheatheAttackAction(target, direction, this);
    }
}
