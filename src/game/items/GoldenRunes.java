package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A class representing golden runes.
 * @see Item
 * @see Consumable
 * @author Iliyana
 */
public class GoldenRunes extends Item implements Consumable{
    /***
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Rune", '*', true);
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * A method that contains the consequence of consuming a golden rune.
     * @param actor actor that consumes golden rune.
     * @return a string that represents the consumption a golden rune.
     */
    @Override
    public String consumeItem(Actor actor) {
        int runeValue = RandomNumberGenerator.getRandomInt(200,10000);
        if (actor.hasCapability(Status.PLAYER)){
            Player player = (Player) actor;
            player.getRuneManager().addRunes(runeValue);
            player.removeItemFromInventory(this);
        }
        return this + " has been consumed. " +  "\nValue of runes gained: $" + runeValue;
    }
}
