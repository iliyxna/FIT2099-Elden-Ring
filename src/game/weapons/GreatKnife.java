package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.rune.Rune;

public class GreatKnife extends WeaponItem implements Purchasable, Sellable {
    private final Rune BUY_PRICE = new Rune(3500);
    private final Rune SELL_PRICE = new Rune(350);
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "shwing", 70);

    }

    @Override
    public Rune getBuyPrice(){
        return BUY_PRICE;
    }

    @Override
    public Rune getSellPrice(){
        return SELL_PRICE;
    }

}
