package game.rune;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class Rune extends Item {
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
}
