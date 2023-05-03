package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.utils.Status;

/**
 * Class representing the Flask of Crimson Tears.
 */
public class CrimsonTears extends Item implements Consumable {
    /**
     * Heal amount of a crimson tear object.
     */
    private final int HEAL_AMOUNT = 250;

    /**
     * Constructor for the Flask of Crimson Tears class.
     */
    public CrimsonTears() {
        super("Flask of Crimson Tears", 'f', false); // portable: false because cannot be dropped.
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * Getter method for the heal amount.
     * @return the healing amount.
     */
    @Override
    public int getHealAmount() {
        return this.HEAL_AMOUNT;
    }


}
