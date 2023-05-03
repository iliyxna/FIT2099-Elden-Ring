package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the Site of Lost Grace.
 */
public class SiteOfLostGrace extends Ground {

    /**
     * Constructor for LostGrace .
     *
     */
    public SiteOfLostGrace() {
        super('U');
        this.addCapability(Status.SITE_OF_LOSTGRACE);
    }

    /**
     * To check which actor can enter Site of Lost grace
     * @param actor the Actor to check
     * @return true if the actor can enter site of lost grace, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // Only players can enter site of lost grace
        // This is to prevent enemies from entering
        return actor.hasCapability(Status.SITE_OF_LOSTGRACE);
    }
}
