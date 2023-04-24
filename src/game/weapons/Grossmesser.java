package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Sellable;
import game.rune.Rune;

public class Grossmesser extends WeaponItem implements Sellable {
    private final Rune SELL_PRICE = new Rune(100);
    public Grossmesser(){
        super("Grossmesser", '?', 115, "swish",85 );
    }

    @Override
    public Rune getSellPrice(){
        return SELL_PRICE;
    }
}
