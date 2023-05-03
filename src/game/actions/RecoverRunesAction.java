package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.rune.Rune;
import game.utils.Status;

/**
 * An action executed when runes are retrieved.
 * @see PickUpItemAction
 */
public class RecoverRunesAction extends PickUpItemAction {
    /**
     * Rune to be recovered
     */
    Rune rune;

    /**
     * Constructor for the RecoverRunesAction class.
     *
     * @param item the item to pick up
     */
    public RecoverRunesAction(Item item) {
        super(item);
        rune = ((Rune)item);
    }

    /**
     * When executed, the actor will recover their previously dropped runes.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(rune);
        // Recover rune (added to the number of runes they currently hold)
        ((Player)actor).getRuneManager().addRunes(rune.getRuneValue());
        return actor + " retrieves Runes (value: $"+ rune.getRuneValue() + ")";
    }

    /**
     * Description of the recover runes action.
     * @param actor The actor performing the action.
     * @return a description of the recover runes action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves Runes (value: $"+ rune.getRuneValue() + ")";
    }
}
