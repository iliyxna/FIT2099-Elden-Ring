package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.reset.ResetManager;
import game.utils.Status;

/**
 * An action executed when the player is in the Lost Grace.
 * @see Action
 */
public class RestAction extends Action {


    /**
     * When executed, the Reset action will be executed.
     * The player will have their last site of last grace visited updated to the
     * current location.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @see ResetAction
     * @see Player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = "";
        Location actorLocation = map.locationOf(actor);
        Player player = (Player) actor;

        player.setLastSiteOfLostGrace(actorLocation);

        if (actorLocation.getGround().hasCapability(Status.SITE_OF_LOSTGRACE)){
            actor.addCapability(Status.RESTING);
            ResetAction resetAction = new ResetAction();
            res = resetAction.execute(actor, map) + "\n";
        }
        // Remove this capability after executing reset
        // Prevent player from having this status even when not in site of lost grace
        actor.removeCapability(Status.RESTING);
        return res;
    }

    /**
     * Description of the resting action of the player.
     * @param actor The actor performing the action.
     * @return description of the resting action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at Site of Lost Grace";
    }
}
