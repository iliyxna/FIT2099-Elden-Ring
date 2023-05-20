package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ResetAction;
import game.utils.PlayerResetStatus;
import game.utils.Status;

/**
 * A class that represents a cliff.
 * @author Mustafa Khan
 */
public class Cliff extends Ground {
    /**
     * Constructor for Cliff.
     */
    public Cliff() {
        super('+');
    }

    /**
     * To check which actor can enter Cliff.
     * @param actor the Actor to check
     * @return true if the actor can enter cliff, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // Only players and traders are allowed to enter floor
        // This is to prevent enemies from entering
        return actor.hasCapability(Status.PLAYER);
    }

    /**
     * A method that is responsible for dealing with player's death when player steps into cliff.
     * @param location The location of the cliff
     */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor() && location.getActor().hasCapability(Status.PLAYER)){
            Actor player = location.getActor();
            location.getActor().hurt(5000);

            // Reset game when player dies
            player.addCapability(PlayerResetStatus.DIED);
            ResetAction resetAction = new ResetAction();
            resetAction.execute(player, location.map());
        }
    }



}
