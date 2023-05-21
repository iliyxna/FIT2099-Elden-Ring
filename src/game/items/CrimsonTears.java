package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.utils.Status;

/**
 * Class representing the Flask of Crimson Tears.
 * @author Mustafa Khan
 * Modified by: Iliyana
 */
public class CrimsonTears extends Item implements Consumable {
    /**
     * Heal amount of a crimson tear object.
     */
    private final int HEAL_AMOUNT = 250;

    /**
     * Constructor for the Flask of Crimson Tears class.
     */
    public CrimsonTears() {
        super("Flask of Crimson Tears", 'f', false); // portable: false because cannot be dropped.
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * A method that contains the consequence of consuming a crimson tears.
     * @param actor actor that consumes crimson tears.
     * @return a string that represents the consumption a crimson tears.
     */
    @Override
    public String consumeItem(Actor actor) {
        String ret = "";
        if (actor.hasCapability(Status.PLAYER)){
            Player player = (Player) actor;
            if (player.getCrimsonFlaskCount() != 0){
                player.heal(HEAL_AMOUNT);
                player.removeItemFromInventory(this);
                player.decreaseCrimsonFlaskCount();
                ret +=  this + " has been consumed.";
            } else if (player.getCrimsonFlaskCount() == 0) {
                ret += this + " use count has exceeded.";
            }
        }
        return ret;
    }
}
