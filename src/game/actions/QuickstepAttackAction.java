package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
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
 * An action to perform the Quickstep unique skill.
 * This is the unique skill of the Great Knife weapon.
 * @see AttackAction
 * @author Iliyana
 */
public class QuickstepAttackAction extends AttackAction{
    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor for QuickstepAttackAction class.
     * @param target target of the attack
     * @param direction direction of the attack
     * @param weapon weapon used for the attack
     */
    public QuickstepAttackAction(Actor target, String direction, WeaponItem weapon){
        super(target,direction,weapon);
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     * This unique skill will allow players to move away from the target after the attack has been performed.
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (!(rand.nextInt(100) <= 60)) {
            return actor + " misses " + this.getTarget() + ".";
        }

//        // Quickstep action allows user to deal normal damage, and move away from the enemy to evade their attack.
//        int damage = this.getWeapon().damage();
//        String result = actor + " " + this.getWeapon().verb() + " " + this.getTarget() + " for " + damage + " damage.";
//        this.getTarget().hurt(damage);

        result += attackSequence(actor, map, this.getWeapon().damage());

        // Move away from target to prevent being attacked if the location does not contain an actor
        for (Exit exit: map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (!location.containsAnActor() && location.canActorEnter(actor)){
                map.moveActor(actor, location);
            }
        }

        return result;
    }

    /**
     * Description of the Quickstep attack action.
     * @param actor The actor performing the action.
     * @return a description of the Quickstep attack action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Quickstep Skill to " + this.getTarget() + " at " + this.getDirection() + " with " + this.getWeapon() ;
    }
}