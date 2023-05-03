package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public interface Resettable {

    /**
     * Method that will be invoked during the reset of the game.
     * @param map the game map the actor is on.
     */
    void reset(GameMap map);
}
