package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Enemy;
import game.actors.PileOfBones;
import game.actors.HeavySkeletalSwordsman;
import game.actors.SkeletalBandit;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

import java.util.Random;

/**
 * An action to perform the Unsheathe unique skill.
 * This is the unique skill of the Uchigatana weapon.
 * @see AttackAction
 * @author Iliyana
 */
public class UnsheatheAttackAction extends AttackAction{

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor for UnsheatheAttackAction class.
     * @param target target of the attack
     * @param direction direction of the attack
     * @param weapon weapon used for the attack
     */
    public UnsheatheAttackAction(Actor target, String direction, WeaponItem weapon){
        super(target,direction,weapon);
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     * This unique skill will allow players to deal 2x damage with 2x hit rate from the original damage and hit rate.
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (!(rand.nextInt(100) <= 60)) {
            return actor + " misses " + this.getTarget() + ".";
        }

        // Unsheathe deals 2x damage
        int damage = 2 * this.getWeapon().damage();
//        String result = actor + " " + this.getWeapon().verb() + " " + this.getTarget() + " for " + damage + " damage.";
//        this.getTarget().hurt(damage);

        result += attackSequence(actor, map, damage);

        return result;
    }

    /**
     * Description of the Unsheathe attack action.
     * @param actor The actor performing the action.
     * @return a description of the Unsheathe attack action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Unsheathe Skill to " + this.getTarget() + " at " + this.getDirection() + " with " + this.getWeapon() ;
    }
}
