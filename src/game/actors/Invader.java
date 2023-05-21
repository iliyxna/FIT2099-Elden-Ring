package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;
import game.utils.RoleManager;
import game.utils.Status;

/**
 * A class that represents an Ally.
 * @see Actor
 * @see Resettable
 * @author Iliyana
 */
public class Invader extends Enemy {

    /**
     * Constructor for Invader class.
     *
     */
    public Invader() {
        super("Invader", 'ඞ', 0, 1358, 5578);
        // assign random role for Invader
        int selection = RandomNumberGenerator.getRandomInt(1,4);
        RoleManager.getInstance().selectedClass(this, selection);
        this.addCapability(Status.INVADER);
        this.setSelfType(Status.INVADER);
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
        for (Behaviour behaviour : getBehaviours().values()) {
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
