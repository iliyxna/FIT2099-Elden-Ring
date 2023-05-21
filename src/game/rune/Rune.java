package game.rune;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RecoverRunesAction;
import game.reset.Resettable;

/**
 * Rune class that represents the currency used in this game.
 * @see Item
 * @author Iliyana
 */
public class Rune extends Item  {
    /**
     * Integer representing rune value.
     */
    private int runeValue;

    /**
     * Constructor for rune class.
     * @param runeValue value of rune
     */
    public Rune(int runeValue) {
        super("Rune", '$', true);
        this.runeValue = runeValue;
    }

    /**
     * Getter method to get rune value
     * @return value of rune
     */
    public int getRuneValue(){
        return runeValue;
    }

    @Override
    public String toString() {
        return runeValue + "";
    }

    /**
     * Creates and returns an action to retrieve the runes.
     * @param actor player that picks up the rune
     * @return a new RecoverRunesAction
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new RecoverRunesAction(this);
    }

}
