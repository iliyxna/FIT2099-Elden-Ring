package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;
import game.transactions.Purchasable;
import game.transactions.Sellable;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Damia
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor for Club
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }

    /**
     * Getter method to retrieve the buying price of the item.
     * @return buying price of the item
     */
    @Override
    public Rune getBuyPrice() {
        return new Rune(600);
    }

    /**
     * Getter method to retrieve the selling price of the item.
     * @return selling price of the item
     */
    @Override
    public Rune getSellPrice() {
        return new Rune(100);
    }
}
