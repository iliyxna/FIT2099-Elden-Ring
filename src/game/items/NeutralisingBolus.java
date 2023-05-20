package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.utils.Status;

/**
 * A class representing a neutralising bolus. Consuming it will remove
 * Poisoned status from actors.
 * @see Item
 * @see Consumable
 * @author Damia
 */
public class NeutralisingBolus extends Item implements Consumable{
    /**
     * Constructor for Neutralising Bolus.
     */
    public NeutralisingBolus() {
        super("Neutralising Bolus", '0', true);
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * A method that contains the consequence of consuming a neutralising bolus.
     * @param actor actor that consumes the neutralising bolus
     * @return a string that represents the consumption of a neutralising bolus
     */
    @Override
    public String consumeItem(Actor actor) {
        if (actor.hasCapability(Status.POISONED)){
            actor.removeCapability(Status.POISONED);
        }
        actor.removeItemFromInventory(this);
        return "Neutralising Bolus has been consumed. Poisoned status has been removed.";
    }
}
