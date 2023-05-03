
package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.*;
import game.rune.Rune;
import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An action to attack other actors at the attacker's surroundings.
 * @see Action
 * @see AreaAttack
 */
public class AreaAttackAction extends Action implements AreaAttack {
    /**
     * Weapon used for the AOE attack.
     */
    private Weapon weapon;

    /**
     * Random number generator.
     */
    private Random rand = new Random();

    /**
     * A list of Locations of the actors surrounding the attacker.
     */
    private List<Location> targetLocations;

    /**
     * To check if attacker is using a WeaponItem
     */
    private boolean containWeapon = false;

    /**
     * Zero parameter Constructor for AreaAttackAction.
     * Attacks with IntrinsicWeapon.
     */
    public AreaAttackAction(){
    }

    /**
     * One parameter Constructor for AreaAttackAction.
     * Attacks with WeaponItem.
     */
    public AreaAttackAction(WeaponItem weapon){
        this.weapon = weapon;
        this.containWeapon = true;
    }


    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if(!containWeapon) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = "";

        // Get locations of the targets around the attacker
        targetLocations = attackArea(actor, map);

        // Every actor will be dealt with the same damage and attack accuracy (hitRate)
        int damage = weapon.damage();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses the attack.";
        } else {

            result += actor + " " + weapon.verb() + " every target for " + damage + " damage.\n";

            for (Location location : targetLocations){
                Actor target = location.getActor();
                target.hurt(damage);

                /// DEALS WITH PLAYER'S DEATH
                if (target.hasCapability(Status.PLAYER)){
                    Player player = (Player) target;
                    if (!target.isConscious()){
                        // Retrieve the location before player died.
                        Location dropLocation;
                        if (player.getMovementList().size() <= 1){
                            dropLocation = map.locationOf(player);
                        } else {
                            dropLocation = player.getMovementList().get(player.getMovementList().size()-2);
                        }
                        Rune playerRune = player.getRuneManager().getTotalRunes();
                        // Use this instead of drop action because we want to drop at the previous location.
                        player.getRuneManager().removeRunes();
                        map.at(dropLocation.x(),dropLocation.y()).addItem(playerRune);

                        // Reset game when player dies
                        ResetAction resetAction = new ResetAction();
                        result += resetAction.execute(player,map);
                        result += "Value of runes dropped: $" + playerRune.getRuneValue();

                        return result;
                    }
                }

                // Deals with unconscious actors
                else if (!target.isConscious()) {
                    // Deals with spawning and de-spawning of skeletal types.
                    // Pile of bones to be added to map
                    PileOfBones pileOfBones = new PileOfBones();
                    if (target instanceof HeavySkeletalSwordsman) {
                        pileOfBones.setPreviousEnemy((Enemy)target);
                        Location pos = map.locationOf(target);
                        map.removeActor(target);
                        map.addActor(pileOfBones, pos);
                        pileOfBones.addWeaponToInventory(new Grossmesser());
                        System.out.println("Heavy Skeletal Swordsman turns into Pile of Bones.");
                    } else if (target instanceof SkeletalBandit){

                        pileOfBones.setPreviousEnemy((Enemy)target);
                        Location pos = map.locationOf(target);
                        map.removeActor(target);
                        map.addActor(pileOfBones, pos);
                        pileOfBones.addWeaponToInventory(new Scimitar());
                        System.out.println("Skeletal Bandit turns into Pile of Bones.");
                    }else{
                        result += new DeathAction(actor).execute(target, map);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Describes the AOE attack action.
     * @param actor The actor performing the action.
     * @return a string describing the AOE attack action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks surrounding enemies with " + weapon;
    }

    /**
     * A method to add actors surrounding the attacker to a list.
     * @param attacker attacker
     * @param map current game map
     * @return a list of locations that contain actors
     */
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

