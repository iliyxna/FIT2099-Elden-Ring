package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Trader;
import game.utils.Status;
import game.transactions.Sellable;
import game.actors.Player;

/**
 * An action class that allows Player to sell item to Trader.
 * Note that it's from PLAYER to TRADER
 * @author Iliyana
 * @see Action
 * @see Trader
 * @version 1.0
 */
public class SellItemAction extends Action {

    /**
     * Item to be sold.
     */
    private Sellable item;

    /**
     * Trader that deals with transaction.
     */
    private Trader trader;

    /**
     * Constructor for SellItemAction.
     * @param item item to be sold
     * @param trader trader that deals with transaction
     */
    public SellItemAction(Item item, Trader trader){
        this.item = (Sellable) item;
        this.trader = trader;
    }

    /**
     * When executed, check if the item is in the player's inventory. If present, proceed
     * with transaction.
     * @param seller The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor seller, GameMap map) {
        String ret="";
        if (seller.hasCapability(Status.PLAYER)) {
            Player player = (Player) seller;
            int itemPrice = item.getSellPrice().getRuneValue();
            // Transaction
            // check if player have the item in their inventory
            if (player.getItemInventory().contains((Item) item)) {
                player.removeItemFromInventory((Item) item);
                player.getRuneManager().addRunes(itemPrice);
                ret = item.toString() + " has been removed from inventory.";
            } else {
                ret = "Player does not have " + item.toString();
            }
        }
        return ret;
    }

    /**
     * Description of the sell weapon action.
     * @param actor The actor performing the action.
     * @return a description of the sell weapon action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell Item: " + item.toString() +  " for $" + item.getSellPrice() + " to " + trader;
    }
}
