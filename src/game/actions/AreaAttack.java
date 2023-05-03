package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * Area attack interface.
 */
public interface AreaAttack {
    /**
     * Returns a list of locations of actors surrounding the current actor
     * @param actor actor that performs the area attack
     * @param map the game map the actor is on
     * @return a list of locations of actors surrounding the current actor
     */
    public List<Location> attackArea(Actor actor, GameMap map);
}
