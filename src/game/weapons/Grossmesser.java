package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.UnsheatheAttackAction;
import game.rune.Rune;
import game.utils.Status;

public class Grossmesser extends WeaponItem implements Sellable {
    public Grossmesser(){
        super("Grossmesser", '?', 115, "swings",85 );
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        this.addCapability(Status.ENEMY_WEAPON_SKILL);
    }

    @Override
    public Rune getSellPrice(){
        return new Rune(100);
    }

    public Action getSkill(Actor target, String direction){
        return new AreaAttackAction(this);
    }
}
