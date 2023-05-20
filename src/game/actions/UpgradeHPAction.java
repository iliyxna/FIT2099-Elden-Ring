package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.rune.Rune;
import game.utils.Status;

/**
 * An action class representing the Upgrade HP action.
 * @see Action
 * @author Iliyana
 */
public class UpgradeHPAction extends Action{

    /**
     * When executed, the player will pay a stated price to upgrade their HP by 48 points.
     * The price for every upgrade will increase by $100 (starting price is $200)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string representing the upgrade hp action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.PLAYER)){
            Player player = (Player) actor;
            int playerRunes = player.getRuneManager().getTotalRunes().getRuneValue();
            // Initial price to upgrade is $200
            // After every upgrade, increase price by 100
            int valueToPay = 200 + (player.getUpgradeHPCount() * 100);
            if (playerRunes > valueToPay){
                player.increaseMaxHp(48);
                player.increaseUpgradeHPCount();
                player.resetMaxHp(player.getPlayerMaxHP());
                player.getRuneManager().subtractRunes(valueToPay);
            }
            else {
                return actor + " does not have enough runes to upgrade HP.";
            }
        }
        return actor + " has successfully upgraded HP.";
    }

    /**
     * A descriptive string representing the upgrade HP action.
     * @param actor The actor performing the action.
     * @return a string representing the upgrade HP action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades HP at Site of Lost Grace.";
    }
}
