package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Consumable;
import game.actions.AreaAttackAction;
import game.actions.ConsumeAction;
import game.actions.UnsheatheAttackAction;
import game.items.CrimsonTears;
import game.rune.Rune;
import game.rune.RuneManager;
import game.weapons.Club;
import game.reset.Resettable;
import game.utils.Status;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;

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

	private RoleManager roleManager = RoleManager.getInstance();

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

		chooseClass();

		// Add flask of crimson tears
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new CrimsonTears());
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Location playerLocation = map.locationOf(this);
//		boolean haveEnemy = false;

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display number of runes player is holding
		System.out.println("Player's current health: " + this.printHp());
		System.out.println("Player's rune value: $" + this.getTotalRunes());

		// Deal with Grossmesser AOE attack to avoid repetition of lines (multiple AOE attack lines for same area) in menu
		if (this.getWeaponInventory().size() != 0){
			WeaponItem currentWeapon = this.getWeaponInventory().get(0);
			if (currentWeapon.getClass() == Grossmesser.class ){
				for (Exit exit : playerLocation.getExits()) {
					Location destination = exit.getDestination();
					if (destination.containsAnActor()) {
						actions.add(new AreaAttackAction(getWeaponInventory().get(0)));
						break;
					}
				}
			}
		}

		// Only print consume action if consumable item is in inventory
		for (Item item: this.getItemInventory()){
			if (item.hasCapability(Status.CONSUMABLE)){
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

	public int getCrimsonFlaskCount() {
		return crimsonFlaskCount;
	}

	public void decreaseCrimsonFlaskCount() {
		this.crimsonFlaskCount--;
	}

	public void chooseClass(){
		int selection;

		selection = roleManager.menuItem();
		switch (selection){
			case 1:
				this.addWeaponToInventory(new Uchigatana());
				this.resetMaxHp(455);
				break;
			case 2:
				this.addWeaponToInventory(new GreatKnife());
				this.resetMaxHp(414);
				break;
			case 3:
				this.addWeaponToInventory(new Club());
				this.resetMaxHp(414);
				break;
			}
		}
}
