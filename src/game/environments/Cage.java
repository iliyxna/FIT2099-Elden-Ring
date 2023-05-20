package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Dog;
import game.utils.MapSize;


/**
 * A class that represents the cage.
 * @author Damia
 */
public class Cage extends Ground {
    /**
     * Constructor for Cage class.
     */
    public Cage() {
        super('<');
    }

    /**
     * A method that is responsible for spawning Dogs.
     * @param location The location of the Ground
     */
    public void tick(Location location) {

        if(MapSize.getPlayerMap() !=  null){
            if(Math.random() <= 0.37 && !location.containsAnActor()) {
                location.addActor(new Dog());

            }
        }

    }
}