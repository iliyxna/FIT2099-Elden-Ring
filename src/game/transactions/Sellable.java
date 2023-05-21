package game.transactions;

import game.rune.Rune;
/**
 * A consumable interface.
 * @author Iliyana
 */
public interface Sellable {
    /**
     * A method to get the sell price of an item or weapon
     * @return rune
     */
    Rune getSellPrice();
}
