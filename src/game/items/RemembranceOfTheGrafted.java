package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.rune.Rune;
import game.transactions.Exchangeable;
import game.utils.Status;
import game.transactions.Sellable;

/**
 * A class representing Remembrance Of The Grafted. This item
 * is dropped when Godrick the Grafted is killed by a player.
 * @see game.actors.GodrickTheGrafted
 * @see Sellable
 * @see Exchangeable
 * @see Item
 * @author Iliyana
 */
public class RemembranceOfTheGrafted extends Item implements Sellable, Exchangeable {

    /***
     * Constructor for Remembrance of the Grafted.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Getter method for the selling price of this item.
     * @return selling price of this item.
     */
    @Override
    public Rune getSellPrice() {
        return new Rune(20000);
    }
}
