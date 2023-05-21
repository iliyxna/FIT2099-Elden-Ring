package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;
import game.utils.RoleManager;
import game.utils.Status;
import game.weapons.AstrologerStaff;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents an Ally.
 * @see Actor
 * @see Resettable
 * @author Iliyana
 */
public class Ally extends Actor implements Resettable {

    /**
     * A hashmap representing a key-pair value between an integer which represents the priority of the behaviour and
     * the behaviour of the enemy.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for Ally class.
     *
     */
    public Ally() {
        super("Ally", 'A', 0);
        behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        // assign random role for Ally
        int selection = RandomNumberGenerator.getRandomInt(1,4);
        RoleManager.getInstance().selectedClass(this, selection);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * At each turn, select a valid action to perform.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        System.out.println(this + "'s current HP: " + this.printHp());

        Location allyLocation = map.locationOf(this);

        for (Exit exit : allyLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()){
                Actor actor = map.getActorAt(destination);
                if (actor.hasCapability(Status.ENEMY)){
                    // check if got weapon
                    if (this.getWeaponInventory().size() != 0){
                        WeaponItem weapon = this.getWeaponInventory().get(0);
                        if (weapon.hasCapability(Status.UNIQUE_SKILL)){
                            // 50% chance to cast skill
                            if (Math.random() >= 0.5){
                                return weapon.getSkill(actor,exit.getName());
                            }
                        }
                    }
                    return new AttackAction(actor, exit.getName(), this.getWeaponInventory().get(0));
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Method that will be invoked during the reset of the game.
     * @param map the game map the actor is on.
     */
    @Override
    public void reset(GameMap map) {
        if (Player.getIsPlayerDead()){
            map.removeActor(this);
        }
    }
}
