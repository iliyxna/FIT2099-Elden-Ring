package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;

public class Grossmesser extends WeaponItem implements Sellable {
    public Grossmesser(){
        super("Grossmesser", '?', 115, "swish",85 );
    }

    @Override
    public Rune getSellPrice(){
        return new Rune(100);
    }
}
