package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.actions.ResetAction;
import game.actions.UnsheatheAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.rune.Rune;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract representing enemies. It implements the Resettable interface.
 * @see Actor
 * @see Resettable
 */
public abstract class Enemy extends Actor implements Resettable {
    /**
     * A hashmap representing a key-pair value between an integer which represents the priority of the behaviour and
     * the behaviour of the enemy.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Rune value that the enemy holds. It is generated using the random number generator
     */
    private int enemyRuneValue;

    /**
     * To check if the enemy is currently following the player.
     */
    private boolean isFollowingPlayer = false;

    /**
     * Self type of the enemy. This is to prevent enemies of the same type attacking each
     * other intentionally. (AOE attacks will still cause the enemies of the same type to be hit!)
     */
    private Status selfType;

    /**
     * Constructor for Enemy class.
     * @see RandomNumberGenerator#getRandomInt(int, int)
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     * @param minRuneValue the minimum value of the range of runes
     * @param maxRuneValue te maximum value of the range of runes
     *
     */
    public Enemy(String name, char displayChar, int hitPoints, int minRuneValue, int maxRuneValue) {
        super(name, displayChar, hitPoints);
        // rune value generated with random number generator
        enemyRuneValue = RandomNumberGenerator.getRandomInt(minRuneValue,maxRuneValue);
        this.addCapability(Status.ENEMY);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(997, new AttackBehaviour());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(Math.random() <= 0.1 && !isFollowingPlayer){
            return new DespawnAction();
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
     * Method that deal with what the Player can do to the current enemy.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be performed by actors that can use the console menu. (e.g. player)
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (!isFollowingPlayer){
                this.addBehaviour(998, new FollowBehaviour(otherActor));
                isFollowingPlayer = true;
            }

            // Player can choose to attack with intrinsic weapon
            actions.add(new AttackAction(this, direction, getIntrinsicWeapon()));

            // If player has weapon, can choose to fight with weaponItem
            if (otherActor.getWeaponInventory().size() != 0) {
                // Use first weapon in inventory to attack
                WeaponItem attackWeapon = otherActor.getWeaponInventory().get(0);
                actions.add(new AttackAction(this, direction, attackWeapon));
                // Some weapons have unique skills
                if (attackWeapon.hasCapability(Status.UNIQUE_SKILL)) {
                    actions.add(attackWeapon.getSkill(this, direction));
                }
            }
        }
        return actions;
    }

    /**
     * Getter method for the type of the enemy.
     * @return the enemy type
     */
    public Status getSelfType() {
        return selfType;
    }

    /**
     * Setter method for the type of the enemy
     * @param selfType the type of the enemy
     */
    public void setSelfType(Status selfType) {
        this.selfType = selfType;
    }

    /**
     * A method to add the behaviours to the behaviour hashmap.
     * @param number number representing the priority of behaviour
     * @param behaviour behaviour of the enemy
     */
    public void addBehaviour(int number, Behaviour behaviour){
        behaviours.put(number,behaviour);
    }

    /**
     * Getter method to retrieve the enemy rune value.
     * NOTE THAT IT RETURNS RUNE TYPE, NOT INTEGER.
     * @see Rune
     * @return the Rune value of enemy
     */
    public Rune getEnemyRuneValue() {
        return new Rune(this.enemyRuneValue);
    }

    /**
     * Method that will be invoked during the reset of the game.
     * @param map the game map the actor is on.
     * @see DespawnAction
     */
    @Override
    public void reset(GameMap map) {
        // if the enemy has this type, they can be respawned
        // in the future, new enemies may not be able to be de-spawned during reset.
        if (this.hasCapability(Status.CAN_DESPAWN_WHEN_RESET)) {
            DespawnAction despawnAction = new DespawnAction();
            despawnAction.execute(this, map);
        }
    }
}
