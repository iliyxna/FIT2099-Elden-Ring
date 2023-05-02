package game.rune;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.reset.Resettable;

public class Rune extends Item implements Resettable {
    private int runeValue;

    public Rune(int runeValue) {
        super("Rune", '$', true);
        this.runeValue = runeValue;
    }

    public void setRuneValue(int runeValue) {
        this.runeValue = runeValue;
    }

    public int getRuneValue(){
        return runeValue;
    }

    @Override
    public String toString() {
        return Integer.toString(runeValue);
    }

    @Override
    public void reset() {

    }
}
