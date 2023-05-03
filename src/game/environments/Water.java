package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.GiantCrab;

/**
 * Class representing the puddle of water class
 */
public class Water extends Ground {
    /**
     * Constructor for water class.
     */
    public Water() {
        super('~');
    }

    /**
     * A method that is responsible for spawning Giant Crab.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if(Math.random() <= 0.02 && !location.containsAnActor()){
            location.addActor(new GiantCrab());
        }
    }

}
