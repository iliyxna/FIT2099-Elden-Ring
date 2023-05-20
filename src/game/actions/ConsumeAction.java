package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;
import game.actors.Player;
import game.items.CrimsonTears;
import game.items.GoldenRunes;
import game.rune.Rune;
import game.utils.Status;

/**
 * An action to consume Consumable items.
 * @see Action
 * @see Consumable
 * @author Mustafa Khan
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
        Consumable consumable = (Consumable) consumableItem;
        retString += consumable.consumeItem(actor);

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
