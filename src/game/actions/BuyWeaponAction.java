package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.weapons.Purchasable;
import game.actors.Player;
import game.actors.Trader;

/**
 * An action class that allows Player to buy weapon from Trader.
 * Note that it's from PLAYER to TRADER
 * @author Iliyana Samsudin
 * @see Action
 * @see Trader
 */
public class BuyWeaponAction extends Action {
    /**
     * Weapon to be purchased.
     */
    private Purchasable weapon;

    /**
     * Constructor for BuyWeaponAction.
     * @param weapon weapon to be purchased
     */
    public BuyWeaponAction(WeaponItem weapon){
        this.weapon = (Purchasable) weapon;
    }

    /**
     * When executed, player's rune value will ge retrieved first. If player has enough
     * runes, proceed with transaction.
     * @param buyer The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor buyer, GameMap map) {
        String retString="";
        if (buyer.hasCapability(Status.PLAYER)){
            Player player = (Player) buyer;
            int playerRune = player.getRuneManager().getTotalRunes().getRuneValue();
            int weaponPrice = weapon.getBuyPrice().getRuneValue();

            // Transaction
            if (playerRune >= weaponPrice){
                player.addWeaponToInventory((WeaponItem) weapon);
                player.getRuneManager().subtractRunes(weaponPrice);
                retString = weapon.toString() + " has been successfully added to inventory.";
            } else {
                retString = "Purchase failed. Player does not have enough runes.";
            }
        }
        return retString;
    }

    /**
     * Description of the buy weapon action
     * @param actor The actor performing the action.
     * @return Description of the buy weapon action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Weapon: " + weapon.toString() + " for $" + weapon.getBuyPrice() + " from Merchant Kale.";
    }

}

