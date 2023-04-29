package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.rune.Rune;

public class GreatKnife extends WeaponItem implements Purchasable, Sellable {
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "shwing", 70);

    }

    @Override
    public Rune getBuyPrice(){
        return new Rune(3500);
    }

    @Override
    public Rune getSellPrice(){

        return new Rune(350);
    }

}
