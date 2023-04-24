package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.rune.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable {
    private final Rune BUY_PRICE = new Rune(600);
    private final Rune SELL_PRICE = new Rune(100);
    /**
     * Constructor for Club
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public Rune getBuyPrice() {
        return BUY_PRICE;
    }

    @Override
    public Rune getSellPrice() {
        return SELL_PRICE;
    }
}
