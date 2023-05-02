package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.GiantCrab;

public class Water extends Ground {
    /**
     * Constructor.
     */
    public Water() {
        super('~');
    }

    // spawn Giant Crab
    public void tick(Location location) {
        if(Math.random() <= 0.02 && !location.containsAnActor()){
            location.addActor(new GiantCrab());
        }
    }

}
