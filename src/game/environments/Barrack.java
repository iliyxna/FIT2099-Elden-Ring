package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.GodrickSoldier;
import game.utils.MapSize;

/**
 * A class that represents a barrack.
 * @author Damia
 */
public class Barrack extends Ground {

    /**
     * Constructor for Barrack class.
     */
    public Barrack() {
        super('B');
    }

    /**
     * A method that is responsible for spawning Godrick Soldiers.
     * @param location The location of the Ground
     */
    public void tick(Location location) {

        if(MapSize.getPlayerMap() !=  null){
            if(Math.random() <= 0.45 && !location.containsAnActor()) {
                location.addActor(new GodrickSoldier());

            }
        }

    }
}