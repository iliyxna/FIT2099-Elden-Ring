package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.actors.Player;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.rune.Rune;
import game.utils.Status;

/**
 * An action to reset the state of the game.
 * @see Action
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

        Player player = (Player) actor;
        // Add player to resettables list
        resetManager.registerResettable((Resettable) actor);

        if (!player.isConscious()){
            res += "YOU DIED.\nGAME IS BEING RESET.\n";
            map.removeActor(player);
            map.at(player.getLastSiteOfLostGrace().x(),player.getLastSiteOfLostGrace().y()).addActor(player);

            // To remove the runes that are left on the map when the player dies.
            if (player.getPreviousRuneLocation() != null){
                for (Item item : player.getPreviousRuneLocation().getItems()){
                    if (item.getClass() == Rune.class) {
                        map.at(player.getPreviousRuneLocation().x(), player.getPreviousRuneLocation().y()).removeItem(item);
                        break;
                    }
                }
            }

        } else {
            res += "GAME IS BEING RESET.\n";
            res += actor + " is resting at Site of Lost Grace\n";
        }

        // Iterate through the whole gameMap to find the other actors (enemies)
        for (int x : map.getXRange()){
            for (int y : map.getYRange()){
                // check every location if it contains an enemy
                Location checkLocation = map.at(x,y);
                if (map.isAnActorAt(checkLocation)){
                    // if there is an enemy at the current location, check if that enemy is respawnable
                    Actor actorAtLocation = map.getActorAt(checkLocation);
                    if (actorAtLocation.hasCapability(Status.CAN_DESPAWN_WHEN_RESET)){
                        resetManager.registerResettable((Resettable) actorAtLocation);
                    }
                }
            }
        }
        // this will loop through the resettables list and execute their respective reset() methods
        resetManager.run(map);

        res += actor + " has recovered full HP by resting\n";
        res += "Maximum uses of Crimson Flask has been reset to " + player.getCrimsonFlaskCount() +"\n";
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
