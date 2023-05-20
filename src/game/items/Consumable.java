package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A consumable interface.
 * @author Mustafa Khan
 * Modified by: Iliyana
 */
public interface Consumable {

    /**
     * A method that contains the consequence of consuming the item.
     * @param actor actor that consumes the consumable item.
     * @return a string that represents the consumption of the item
     */
    String consumeItem(Actor actor);

}
