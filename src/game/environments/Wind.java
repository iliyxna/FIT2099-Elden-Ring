package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.LoneWolf;
import game.actors.Player;

public class Wind extends Ground {


    /**
     * Constructor.
     */
    public Wind() {
        super('&');

    }

    // spawn lone wolf
    public void tick(Location location) {
        if(Math.random() <= 0.33 && !location.containsAnActor()){
            location.addActor(new LoneWolf());
        }
    }
}
