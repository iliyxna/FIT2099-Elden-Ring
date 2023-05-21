package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.utils.Status;

/**
 * A class representing Golden seeds. Consuming it will increase the number of uses for
 * Crimson Flasks.
 * @see Item
 * @see Consumable
 * @see Player
 * @author Damia
 */
public class GoldenSeeds extends Item implements Consumable{
    /***
     * Constructor for golden seeds.
     */
    public GoldenSeeds() {
        super("Golden Seeds", ',', true);
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * A method that contains the consequence of consuming a golden seed.
     * @param actor actor that consumes the golden seed.
     * @return a string that represents the consumption of a golden seed.
     */
    @Override
    public String consumeItem(Actor actor) {
        String ret = "";
        if (actor.hasCapability(Status.PLAYER)){
            Player player = (Player) actor;
            player.increaseCrimsonFlaskCount();
            player.removeItemFromInventory(this);
            ret += this + " has been consumed. Number of Crimson Flask uses: " + player.getCrimsonFlaskCount();
        }
        return ret;
    }
}
