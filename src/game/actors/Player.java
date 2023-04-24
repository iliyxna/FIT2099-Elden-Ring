package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.rune.Rune;
import game.weapons.Club;
import game.reset.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();

	// player's runes
	private Rune totalRunes = new Rune(0);

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.addCapability(Status.CAN_ENTER_FLOOR);
		this.addWeaponToInventory(new Club());
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void reset() {}

	/**
	 * Getter method to retrieve the total runes owned by the player.
	 * @return total runes owned by player
	 */
	public Rune getTotalRunes() {
		return totalRunes;
	}

	/**
	 * Setter method to set the new rune value the player has.
	 * @param totalRunes the total runes owned by player
	 */
	public void setTotalRunes(int totalRunes) {
		this.totalRunes.setRuneValue(totalRunes);
	}

	public void addRunes(int value){
		int newValue = totalRunes.getRuneValue() + value;
		setTotalRunes(newValue);
	}

	public void subtractRunes(int value){
		int newValue = totalRunes.getRuneValue() - value;
		setTotalRunes(newValue);
	}
}
