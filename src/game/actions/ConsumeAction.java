package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Consumable;
import game.actors.Player;
import game.utils.Status;

public class ConsumeAction extends Action {
    private Item consumableItem;
    public ConsumeAction(Item consumableItem){
        this.consumableItem = consumableItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String retString = "";
        Player player = (Player) actor;
        Consumable consumable = (Consumable) consumableItem;

        // check if player has the item
        if (player.getItemInventory().contains(consumableItem)){
            if (consumableItem.hasCapability(Status.CONSUMABLE) && player.getCrimsonFlaskCount() != 0){
                player.heal(consumable.getHealAmount());
                player.removeItemFromInventory(consumableItem);
                player.decreaseCrimsonFlaskCount();
                retString = consumableItem.toString() + " has been consumed. Number of uses left: " +
                        player.getCrimsonFlaskCount();
            } else if (player.getCrimsonFlaskCount() == 0) {
                return consumableItem.toString() + " use count has exceeded.";
            }
        }
        return retString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + consumableItem.toString();
    }
}




//package game.actions;
//
//import edu.monash.fit2099.engine.actions.Action;
//import edu.monash.fit2099.engine.actors.Actor;
//import edu.monash.fit2099.engine.items.Item;
//import edu.monash.fit2099.engine.positions.GameMap;
//import game.Consumable;
//import game.actors.Player;
//import game.utils.Status;
//
//public class ConsumeAction extends Action {
//    private Item consumableItem;
//    public ConsumeAction(Item consumableItem){
//        this.consumableItem = consumableItem;
//    }
//
//    @Override
//    public String execute(Actor actor, GameMap map) {
//        String retString = "";
//        Player player = (Player) actor;
//        Consumable consumable = (Consumable) consumableItem;
//
//        // check if player has the item
//        if (player.getItemInventory().contains(consumableItem)){
//            if (consumableItem.hasCapability(Status.CONSUMABLE) && player.getCrimsonFlaskCount() != 0){
//                player.increaseHP(consumable.getHealAmount());
//                player.removeItemFromInventory(consumableItem);
//                player.decreaseCrimsonFlaskCount();
//                retString = consumableItem.toString() + " has been consumed. Number of uses left: " +
//                        player.getCrimsonFlaskCount();
//            } else if (player.getCrimsonFlaskCount() == 0) {
//                return consumableItem.toString() + " use count has exceeded.";
//            }
//        }
//        return retString + "\nPlayer's current hit points: " + player.getHitPoints();
//    }
//
//    @Override
//    public String menuDescription(Actor actor) {
//        return "Consume " + consumableItem.toString();
//    }
//}
