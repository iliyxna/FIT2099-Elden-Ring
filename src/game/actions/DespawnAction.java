package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that de-spawns an actor from the map.
 * @see Action
 */
public class DespawnAction extends Action {
    /**
     * When executed, the actor is removed from the game map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Description of the de-spawn action.
     * @param actor The actor performing the action.
     * @return a description of the de-spawn action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " de-spawned.";
    }
}
