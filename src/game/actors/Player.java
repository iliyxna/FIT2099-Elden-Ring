package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Consumable;
import game.actions.ConsumeAction;
import game.items.CrimsonTears;
import game.rune.Rune;
import game.rune.RuneManager;
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
public class Player extends Actor implements Resettable, RuneManager {

	private final Menu menu = new Menu();

	// player's runes in value (not rune object. RuneManager manages it)
	private int totalRunes = 0;
	private int crimsonFlaskCount = 2;

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

		// Add flask of crimson tears
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new CrimsonTears()); 	// extra flask for debugging purpose
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display number of runes player is holding
		System.out.println("Player's rune value: $" + this.getTotalRunes());

		// Only print consume action if consumable item is in inventory
		for (Item item: this.getItemInventory()){
			if (item instanceof Consumable){
				actions.add(new ConsumeAction(item));
			}
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void reset() {}


	@Override
	/**
	 * Getter method to retrieve the total runes owned by the player.
	 * @return total runes owned by player
	 */
	public Rune getTotalRunes() {
		return new Rune(totalRunes);
	}

	@Override
	/**
	 * Setter method to set the new rune value the player has.
	 * @param totalRunes the total runes owned by player
	 */
	public void setTotalRunes(int value) {
		this.totalRunes = value;
	}

	@Override
	public void addRunes(int value){
		int newValue = totalRunes + value;
		setTotalRunes(newValue);
	}

	@Override
	public void subtractRunes(int value){
		int newValue = totalRunes - value;
		setTotalRunes(newValue);
	}

	public int getHitPoints(){
		return this.hitPoints;
	}
	public void increaseHP(int hp){
		this.hitPoints += hp;
	}

	public int getCrimsonFlaskCount() {
		return crimsonFlaskCount;
	}

	public void decreaseCrimsonFlaskCount() {
		this.crimsonFlaskCount--;
	}
}
