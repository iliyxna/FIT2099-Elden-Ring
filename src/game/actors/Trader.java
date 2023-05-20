package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;

/**
 * Class representing the Trader of the game.
 * @see Actor
 * @author Iliyana
 */
public abstract class Trader extends Actor {
    /**
     * Name of the trader
     */
    private String name;

    /**
     * Constructor for Trader class.
     */
    public Trader(String name, char displayChar) {
        super(name, displayChar, 0);
        addCapability(Status.CAN_ENTER_FLOOR);
    }

    /**
     * Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action that is executed during that turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Getter method for the trader's name.
     * @return the name of the trader
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the trader's name.
     * @param name the name of the trader
     */
    public void setName(String name) {
        this.name = name;
    }



}
