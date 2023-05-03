package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickstepAttackAction;
import game.actions.UnsheatheAttackAction;
import game.rune.Rune;
import game.utils.Status;

public class GreatKnife extends WeaponItem implements Purchasable, Sellable {
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "stabs", 70);
        this.addCapability(Status.UNIQUE_SKILL);
    }

    @Override
    public Rune getBuyPrice(){
        return new Rune(3500);
    }

    @Override
    public Rune getSellPrice(){

        return new Rune(350);
    }

    @Override
    public Action getSkill(Actor target, String direction){
        return new QuickstepAttackAction(target, direction, this);
    }
}
