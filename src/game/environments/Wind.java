package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.LoneWolf;
import game.actors.Player;

/**
 * Class representing the gust of wind class.
 */
public class Wind extends Ground {

    /**
     * Constructor for wind class.
     */
    public Wind() {
        super('&');

    }

    /**
     * A method that is responsible for spawning Lone Wolf.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if(Math.random() <= 0.33 && !location.containsAnActor()){
            location.addActor(new LoneWolf());
        }
    }
}
