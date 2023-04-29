package game.rune;

public interface RuneManager {

    public Rune getTotalRunes();

    /**
     * A method to set the new rune value the player has.
     * @param value the total runes owned by player
     */
    public void setTotalRunes(int value);

    public void addRunes(int value);

    public void subtractRunes(int value);
}
