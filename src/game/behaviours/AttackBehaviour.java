package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.actors.Enemy;
import game.utils.Status;

/**
 * A class that figures out an Attack action that will be invoked by the actor (used for enemies).
 * @see AttackAction
 * @see AreaAttackAction
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction to attack an Actor close by, if possible.
     * If not possible, return null.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an AttackAction that the actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        WeaponItem weapon;

        // Get exits of the current actor's location
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Check if the current exit contains an actor, if yes, may perform attack depending on type.
            if (destination.containsAnActor()){
                Actor target = destination.getActor();

                // For AOE attacks which have 50% chance to occur.
                // Will not intentionally attack actors with the same type, but if AOE attack is
                // executed while having the same type nearby, the actor with the same type will also be hit.
                if (Math.random() >= 0.5){
                    // Crab slam attack
                    if(actor.hasCapability(Status.GIANT_CRAB) && (!target.hasCapability(Status.WATER_TYPE))){
                        return new AreaAttackAction();
                    }
                    // For weapon skills (Grossmesser, Scimitar)
                    if (actor.getWeaponInventory().size() != 0){
                        weapon = actor.getWeaponInventory().get(0);
                        // Both Grossmesser and Scimitar users have 50% chance of casting Spinning attack
                        if (weapon.hasCapability(Status.ENEMY_WEAPON_SKILL) && !target.hasCapability(Status.SKELETAL_TYPE)){
                            return weapon.getSkill(destination.getActor(), destination.toString());
                        }

                    }
                }
                // Here onwards are all normal attacks.
                // When the target is a Player
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    if (actor.getWeaponInventory().size() != 0) {
                        weapon = actor.getWeaponInventory().get(0);
                        return new AttackAction(destination.getActor(), exit.getName(), weapon);
                    } else {
                        return new AttackAction(destination.getActor(), exit.getName());
                    }
                }

                // When the target is an enemy
                if (actor.hasCapability(Status.ENEMY)){
                    Enemy attacker = (Enemy) actor;
                    // Pile of bones is not a subclass of Enemy, so cannot be cast.
                    // Enemies that are not the same type can also attack pile of bones
                    if (target.hasCapability(Status.PILE_OF_BONES)){
                        if (attacker.getSelfType() != Status.SKELETAL_TYPE) {
                            if (actor.getWeaponInventory().size() != 0) {
                                weapon = actor.getWeaponInventory().get(0);
                                return new AttackAction(destination.getActor(), exit.getName(), weapon);
                            } else {
                                return new AttackAction(destination.getActor(), exit.getName());
                            }
                        }
                    }

                    else {
                        // For enemy subclasses only
                        Enemy target_ = (Enemy) destination.getActor();
                        if (attacker.getSelfType() != target_.getSelfType()) {
                            if (actor.getWeaponInventory().size() != 0) {
                                weapon = actor.getWeaponInventory().get(0);
                                return new AttackAction(destination.getActor(), exit.getName(), weapon);
                            } else {
                                return new AttackAction(destination.getActor(), exit.getName());
                            }
                        }
                    }
                }
            }

        }
        return null;
    }
}



