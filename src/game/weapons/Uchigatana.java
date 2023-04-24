package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.rune.Rune;

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {
    private final Rune BUY_PRICE = new Rune(5000);
    private final Rune SELL_PRICE = new Rune(500);

    /**
     * Constructor for Uchigatana.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "ting", 80);
    }


    @Override
    public Rune getBuyPrice() {
        return BUY_PRICE;
    }

    @Override
    public Rune getSellPrice() {
        return SELL_PRICE;
    }
}
