package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action class that represents the summoning of an actor to the map.
 * @see Action
 * @author Iliyana
 */
public class SummonAction extends Action {

    /**
     * Location to be spawned
     */
    private Location location;

    /**
     * Actor to be spawned
     */
    private Actor actor;

    /**
     * Constructor for Summon Action class.
     * @param actor actor to be spawned
     * @param location location to be spawned
     */
    public SummonAction (Actor actor, Location location){
        this.actor = actor;
        this.location = location;
    }

    /**
     * When executed, the actor will be added to the map at the given location.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string representing the summon action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.addActor(this.actor, this.location);
        return menuDescription(actor);
    }

    /**
     * A descriptive string representing the summon action
     * @param actor The actor performing the action.
     * @return a string representing the summon action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Summon guest (50% chance to summon either Ally or Invader).";
    }
}
