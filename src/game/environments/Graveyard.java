package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HeavySkeletalSwordsman;

public class Graveyard extends Ground {
    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
    }

    // spawn Heavy Skeletal Swordsman
    public void tick(Location location) {
        if(Math.random() <= 0.27 && !location.containsAnActor()){
            location.addActor(new HeavySkeletalSwordsman());
        }
    }
}
