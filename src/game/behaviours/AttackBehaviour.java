package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.AreaAttackAction;
import game.actors.Enemy;
import game.utils.Status;

public class AttackBehaviour implements Behaviour{
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
        Enemy attacker = (Enemy) actor;
        Location here = map.locationOf(actor);
        WeaponItem weapon;


        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Crab attacks
            if (Math.random() >= 0.5 && destination.containsAnActor() ){
                if(actor.hasCapability(Status.GIANT_CRAB)){
                    return new AreaAttackAction();
                }

//                if (actor.getWeaponInventory() != null){
//                    weapon = actor.getWeaponInventory().get(0);
//                    if (weapon.hasCapability(Status.GROSSMESSER_SKILL)){
//                        return new SpinningAttackAction(weapon);
//                    }
//                }
            }

            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                if (actor.getWeaponInventory() != null) {
                    weapon = actor.getWeaponInventory().get(0);
                    return new AttackAction(destination.getActor(), exit.getName(), weapon);
                } else {
                    return new AttackAction(destination.getActor(), exit.getName());
                }
            }

            if (destination.containsAnActor()){
                Enemy target = (Enemy) destination.getActor();
                if (attacker.getSelfType() != target.getSelfType()){
                    if (actor.getWeaponInventory() != null) {
                        weapon = actor.getWeaponInventory().get(0);
                        return new AttackAction(destination.getActor(), exit.getName(), weapon);
                    } else {
                        return new AttackAction(destination.getActor(), exit.getName());
                    }
                }
            }

        }
        return null;
    }

}

