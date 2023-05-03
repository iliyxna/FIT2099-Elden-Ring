package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	/**
	 * Constructor for Floor class.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * To check which actor can enter floor.
	 * @param actor the Actor to check
	 * @return true if actor can enter, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		// Only players and traders are allowed to enter floor
		// This is to prevent enemies from entering
		return actor.hasCapability(Status.CAN_ENTER_FLOOR);
	}
}
