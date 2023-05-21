package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.utils.FancyMessage;
import game.utils.PlayerResetStatus;
import game.utils.Status;

/**
 * An action executed when the player is in the Lost Grace.
 * @see Action
 * @see ResetAction
 * @see game.environments.SiteOfLostGrace
 * @author Mustafa Khan
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
        actor.addCapability(PlayerResetStatus.RESTING);
        Location actorLocation = map.locationOf(actor);
        if (actor.hasCapability(Status.PLAYER)) {
            Player player = (Player) actor;

            Location prevLocation = player.getLastSiteOfLostGrace();

            // Set the last site of lost grace visited to current site, and set the map to which map the site is on
            player.setLastSiteOfLostGrace(actorLocation);
            player.setMapOfLastLostGrace(map);

            if (prevLocation != player.getLastSiteOfLostGrace()) {
                new Display().println(FancyMessage.LOST_GRACE_FOUND);
            }
        }

        ResetAction resetAction = new ResetAction();
        res += resetAction.execute(actor, map) + "\n";

        // Remove this capability after executing reset
        // Prevent player from having this status even when not in site of lost grace
        actor.removeCapability(PlayerResetStatus.RESTING);
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
