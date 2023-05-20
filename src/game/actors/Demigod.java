package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;

/**
 * An abstract class representing a Demigod
 * @see Enemy
 * @author Iliyana
 */
public abstract class Demigod extends Enemy{

    /**
     * Initial location of the demigod
     */
    protected Location initialLocation;

    /**
     * Constructor for Enemy class.
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     */
    public Demigod(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 0, 0);
    }

    public void setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
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
        return new DoNothingAction();
    }
}
