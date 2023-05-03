package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.rune.Rune;
import game.utils.Status;

public class Scimitar extends WeaponItem implements Purchasable,Sellable{
    /**
     * Constructor.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slices", 88);
        this.addCapability(Status.ENEMY_WEAPON_SKILL);
    }

    @Override
    public Rune getBuyPrice() {
        return new Rune(600);
    }
    public Rune getSellPrice(){
        return new Rune(100);
    }

    public Action getSkill(Actor target, String direction){
        return new AreaAttackAction(this);
    }
}
