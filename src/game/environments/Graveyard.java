package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HeavySkeletalSwordsman;

/**
 * Class representing the graveyard.
 */
public class Graveyard extends Ground {
    /**
     * Constructor for graveyard class.
     */
    public Graveyard() {
        super('n');
    }

    /**
     * A method that is responsible for spawning Heavy Skeletal Swordsman.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if(Math.random() <= 0.27 && !location.containsAnActor()){
            location.addActor(new HeavySkeletalSwordsman());
        }
    }
}
