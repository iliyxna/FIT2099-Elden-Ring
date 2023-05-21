package game.transactions;

import game.rune.Rune;
/**
 * A consumable interface.
 * @author Iliyana
 */
public interface Purchasable {

    /**
     * A method to get the buy price of an item or weapon
     * @return rune
     */
    Rune getBuyPrice();

}
