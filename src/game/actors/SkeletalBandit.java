package game.actors;

public class SkeletalBandit extends Enemy{
    /**
     * Constructor.
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     * @param minRuneValue
     * @param maxRuneValue
     */
    public SkeletalBandit(String name, char displayChar, int hitPoints, int minRuneValue, int maxRuneValue) {
        super(name, displayChar, hitPoints, minRuneValue, maxRuneValue);
    }
}
