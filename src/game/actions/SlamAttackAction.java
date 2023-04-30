package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.HeavySkeletalSwordsman;
import game.actors.PileOfBones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlamAttackAction extends Action implements AreaAttack {
    /**
     * Random number generator
     */
    private Random rand = new Random();

    private List<Location> targetLocations;


    @Override
    public String execute(Actor actor, GameMap map) {
        IntrinsicWeapon weapon = actor.getIntrinsicWeapon();
        String result = "";
        // Get locations of the targets around the attacker
        targetLocations = attackArea(actor, map);

        // Every actor will be dealt with the same damage and attack accuracy (hitRate)
        int damage = weapon.damage();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses the attack.";
        } else {
            for (Location location : targetLocations){
                Actor target = location.getActor();
                target.hurt(damage);

                // Deals with unconscious actors
                if (!target.isConscious()) {
                    // Deals with spawning and de-spawning of heavy skeletal swordsman
                    if (target instanceof HeavySkeletalSwordsman) {
                        Location pos = map.locationOf(target);
                        map.removeActor(target);
                        map.addActor(new PileOfBones(), pos);
                    } else {
                        result += new DeathAction(actor).execute(target, map) + "\n";
                    }
                }
            }
            result += actor + " " + weapon.verb() + " every target for " + damage + " damage.";
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public List<Location> attackArea(Actor attacker, GameMap map) {
        ArrayList<Location> locations = new ArrayList<>();
        Location here = map.locationOf(attacker);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                locations.add(destination);
            }
        }
        return locations;
    }
}
