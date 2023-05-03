package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;
import game.weapons.Purchasable;
import game.actors.Player;

/**
 * Not used yet
 */
public class BuyItemAction extends Action {
    private Purchasable item;

    public BuyItemAction(Item item){
        this.item = (Purchasable) item;
    }

    @Override
    public String execute(Actor buyer, GameMap map) {
        String retString="";
        if (buyer.hasCapability(Status.PLAYER)){
            Player player = (Player) buyer;
            int playerRune = player.getRuneManager().getTotalRunes().getRuneValue();
            int itemPrice = item.getBuyPrice().getRuneValue();

            // Transaction
            if (playerRune >= itemPrice){
                player.addItemToInventory((Item) item);
                player.getRuneManager().subtractRunes(itemPrice);
                retString = item.toString() + " has been successfully added to inventory.";
            } else {
                retString = "Purchase failed. Player does not have enough runes.";
            }
        }
        return retString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy Item: " + item.toString() + " for $" + item.getBuyPrice() + " from Merchant Kale";
    }
}
