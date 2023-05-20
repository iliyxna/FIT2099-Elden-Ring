package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.actions.UpgradeHPAction;
import game.utils.Status;

/**
 * A class that represents the Site of Lost Grace.
 * @author Mustafa Khan
 * Modified by: Iliyana
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


    /**
     * Method that deals with what players can do around the site of lost grace.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of actions that can be executed by the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.PLAYER)){
            actions.add(new RestAction());
            actions.add(new UpgradeHPAction());
        }
        return actions;
    }
}
