package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;
import game.actors.Player;
import game.utils.Status;

/**
 * An action to consume Consumable items.
 * @see Action
 * @see Consumable
 */
public class ConsumeAction extends Action {
    /**
     * Item to be consumed
     */
    private Item consumableItem;

    /**
     * Constructor for ConsumeAction class.
     * @param consumableItem
     */
    public ConsumeAction(Item consumableItem){
        this.consumableItem = consumableItem;
    }

    /**
     * When executed, check the number of uses for the item left. If number of uses have not exceeded,
     * perform the consume action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
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
                retString = consumableItem.toString() + " has been consumed.";
            } else if (player.getCrimsonFlaskCount() == 0) {
                return consumableItem.toString() + " use count has exceeded.";
            }
        }
        return retString;
    }

    /**
     * Description of the consume action.
     * @param actor The actor performing the action.
     * @return a description of the consume action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + consumableItem.toString();
    }
}
