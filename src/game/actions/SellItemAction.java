package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.Sellable;
import game.actors.Player;

public class SellItemAction extends Action {

    private Sellable item;

    public SellItemAction(Item item){
        this.item = (Sellable) item;
    }

    @Override
    public String execute(Actor seller, GameMap map) {
        String ret;
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
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " sold " + item.toString() +  "for $" + item.getSellPrice();
    }
}
