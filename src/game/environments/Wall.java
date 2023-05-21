package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor for Wall class.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * To check if actor can enter the wall
	 * @param actor the Actor to check
	 * @return false as no actor can enter
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * To check if the wall is able to block thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
