package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.rune.Rune;
import game.utils.PlayerResetStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * An action to reset the state of the game.
 * @see Action
 * @author Mustafa Khan
 */
public class ResetAction extends Action {
    /**
     * An instance of the reset manager.
     */
    ResetManager resetManager = ResetManager.getInstance();

    /**
     * When executed, we check if the player is not conscious (dead). If player is dead,
     * the player will be respawned at the last site of lost grace the player last visited. If there are any runes left
     * on the map when the player dies, the runes will be removed. If the player rests and the game resets, the runes
     * will still be left on the map. During the reset of the game, all the enemies that can be de-spawned will be removed
     * from the game map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = "";
        List<Resettable> tempResettableList = new ArrayList<>();

        // When player DIES
        if (actor.hasCapability(PlayerResetStatus.DIED)){
            res += "YOU DIED.\nGAME IS BEING RESET.\n";
            map.removeActor(actor);
            (((Player)actor).getMapOfLastLostGrace()).at(((Player)actor).getLastSiteOfLostGrace().x(),((Player)actor).getLastSiteOfLostGrace().y()).addActor(actor);
            Player.setIsPlayerDead(true);
            actor.removeCapability(PlayerResetStatus.DIED);
            if (((Player)actor).getPreviousRuneLocation() != null){
                for (Item item : ((Player)actor).getPreviousRuneLocation().getItems()) {
                    if (item.getClass() == Rune.class) {
                        (((Player) actor).getMapOfPreviousRune()).at(((Player) actor).getPreviousRuneLocation().x(), ((Player) actor).getPreviousRuneLocation().y()).removeItem(item);
                        break;
                    }
                }
            }
        }

        // When player RESTS
        else if (actor.hasCapability(PlayerResetStatus.RESTING)){
            res += "GAME IS BEING RESET.\n";
            res += actor + " is resting at Site of Lost Grace\n";
        }

        // this will loop through the resettables list and execute their respective reset() methods
        resetManager.run(map);

        // set back to false after resetting.
        Player.setIsPlayerDead(false);
        // Add back player into list of resettables after reset.
        resetManager.registerResettable((Resettable) actor);

        res += "Maximum uses of Crimson Flask has been reset\n";
        res += "All enemies have been de-spawned\n";
        return res;
    }

    /**
     * Description of the reset action.
     * @param actor The actor performing the action.
     * @return a description of the reset action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Game has been reset.";
    }
}
