package game.rune;

/**
 * A singleton RuneManager class that manages the runes of a player.
 * @version 1.0
 * @see Rune
 */
public class RuneManager {

    /**
     * Total value of runes a player currently holds.
     */
    private int totalRunes = 100;

    /**
     * A singleton rune manager instance.
     */
    private static RuneManager runeManagerIns;

    /**
     * Get the singleton instance of rune manager.
     * @return RuneManager singleton instance
     */
    public static RuneManager getInstance(){
        if (runeManagerIns == null){
            runeManagerIns = new RuneManager();
        }
        return runeManagerIns;
    }

    /**
     * Private constructor of the rune manager class.
     */
    private RuneManager(){}

    /**
     * Getter method to retrieve the total runes owned by the player.
     * @return total runes owned by player
     */
    public Rune getTotalRunes() {
        return new Rune(totalRunes);
    }

    /**
     * Setter method to set the new rune value the player has.
     * @param totalRunes the total runes owned by player
     */
    public void setTotalRunes(int totalRunes) {
        this.totalRunes = totalRunes;
    }

    /**
     * A method to add a value to the total rune value.
     * @param value value to be added
     */

    public void addRunes(int value){
        int newValue = totalRunes + value;
        setTotalRunes(newValue);
    }

    /**
     * A method to subtract the value from the total rune value
     * @param value
     */
    public void subtractRunes(int value){
        int newValue = totalRunes - value;
        setTotalRunes(newValue);
    }

    /**
     * A method to remove all the runes from the player.
     */
    public void removeRunes(){
        setTotalRunes(0);
    }
}
