package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.*;
import game.utils.MapSize;

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

        if(MapSize.getPlayerMap() !=  null){
            //spawn west
            if(location.x() <= MapSize.getHorizontalSize()/2 && Math.random() <= 0.33 && !location.containsAnActor()) {
                location.addActor(new LoneWolf());

                // spawn east
            }else if(location.x() > MapSize.getHorizontalSize()/2 && Math.random() <= 0.04 && !location.containsAnActor()){
                location.addActor(new GiantDog());
            }
        }
    }
}
