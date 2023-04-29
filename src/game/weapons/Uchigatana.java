package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor for Uchigatana.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "ting", 80);
    }


    @Override
    public Rune getBuyPrice() {
        return new Rune(5000);
    }

    @Override
    public Rune getSellPrice() {
        return new Rune(500);
    }
}
