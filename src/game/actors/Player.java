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
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.items.CrimsonTears;
import game.rune.RuneManager;
import game.utils.RoleManager;
import game.weapons.Club;
import game.reset.Resettable;
import game.utils.Status;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player. It implements the Resettable interface.
 * The player will have to choose their starting class at the start of the game, and based
 * on the class they have chosen, they are going to start with different WeaponItems in their
 * inventory and hit points.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Iliyana, Damia, Mustafa
 *
 * @see Actor
 * @see Resettable
 */
public class Player extends Actor implements Resettable{
	private final Menu menu = new Menu();

	/**
	 * Rune manager to manage player's runes.
	 */
	private RuneManager runeManager;

	/**
	 * Keeps count of the number of uses left for Flask of Crimson Tears.
	 */
	private int crimsonFlaskCount = 2;

	/**
	 * Role manager to allow players to choose their player class.
	 * Roles: Samurai, Bandit, Wretch
	 */
	private RoleManager roleManager = RoleManager.getInstance();

	/**
	 * Location of the last Site of Lost Grace visited.
	 */
	private Location lastSiteOfLostGrace;

	/**
	 * A list of locations of the movements of the player.
	 */
	private List<Location> movementList = new ArrayList<>();

	/**
	 * The previous location of the dropped runes when the player died.
	 */
	private Location previousRuneLocation;

	/**
	 * Constructor for Player class. The chooseClass() method is invoked in the
	 * constructor so that player's will get to choose their player class
	 * (Samurai, Bandit, Wretch) at the start of the game.
	 * @see Player#chooseClass()
	 */
	public Player() {
		super("Tarnished", '@', 300);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.addCapability(Status.CAN_ENTER_FLOOR);
		this.addCapability(Status.SITE_OF_LOSTGRACE);

		// depending on class, the hitpoints will change
		chooseClass();

		// Add flask of crimson tears
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new CrimsonTears());
		this.runeManager = RuneManager.getInstance();
	}

	/**
	 * Select and return an action to perform on the current turn.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the action to be performed during the turn
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Location playerLocation = map.locationOf(this);
		movementList.add(playerLocation);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display number of runes player is holding
		System.out.println(this + "'s current hit points: " + this.printHp());
		System.out.println(this + "'s rune value: $" + runeManager.getTotalRunes().getRuneValue());
		System.out.println("Crimson Flask available uses remaining: " + this.getCrimsonFlaskCount());

		// Check if player is at site of lost grace
		// If at site of lost grace, can choose to rest
		if (map.locationOf(this).getGround().hasCapability(Status.SITE_OF_LOSTGRACE)) {
			actions.add(new RestAction());
		}

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
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Getter method to get the rune manager.
	 * @return rune manager
	 */
	public RuneManager getRuneManager(){
		return this.runeManager;
	}

	/**
	 * Getter method to get the number of uses left for Flask of Crimson Tears.
	 * @return uses left for Flask of Crimson Tears
	 */
	public int getCrimsonFlaskCount() {
		return crimsonFlaskCount;
	}

	/**
	 * A method to decrease the number of uses for Flask of Crimson Tears by 1.
	 */
	public void decreaseCrimsonFlaskCount() {
		this.crimsonFlaskCount--;
	}

	/**
	 * Setter method to set the last site of Lost Grace visited by player.
	 * @param lastSiteOfLostGrace last site of Lost Grace visited by player
	 */
	public void setLastSiteOfLostGrace(Location lastSiteOfLostGrace) {
		this.lastSiteOfLostGrace = lastSiteOfLostGrace;
	}

	/**
	 * Getter method to get the last site of Lost Grace visited by player.
	 * @return last site of Lost Grace visited by player
	 */
	public Location getLastSiteOfLostGrace() {
		return lastSiteOfLostGrace;
	}

	/**
	 * Getter method for the list of movements of the player.
	 * @return the list of movements of the player.
	 */
	public List<Location> getMovementList() {
		return movementList;
	}

	/**
	 * Getter method for the previous rune location of the player.
	 * @return the previous rune location of the player.
	 */
	public Location getPreviousRuneLocation() {
		return previousRuneLocation;
	}

	/**
	 * Setter method for the previous rune location of the player.
	 * @param previousRuneLocation the previous rune location of the player.
	 */
	public void setPreviousRuneLocation(Location previousRuneLocation) {
		this.previousRuneLocation = previousRuneLocation;
	}

	/**
	 * A method to print the Role menu item to the console at the start of the game.
	 * @see RoleManager
	 */
	public void chooseClass(){
		int selection;

		selection = roleManager.menuItem();
		switch (selection) {
			case 1 -> {
				this.addWeaponToInventory(new Uchigatana());
				this.resetMaxHp(455);
			}
			case 2 -> {
				this.addWeaponToInventory(new GreatKnife());
				this.resetMaxHp(414);
			}
			case 3 -> {
				this.addWeaponToInventory(new Club());
				this.resetMaxHp(414);
			}
		}
	}

	/**
	 * Method that will be invoked during the reset of the game.
	 * @param map the game map the player is on
	 */
	@Override
	public void reset(GameMap map) {
		// Reset player's hit points to maximum
		this.resetMaxHp(this.maxHitPoints);

		// Reset the number of uses of crimson flask to max num of uses
		this.crimsonFlaskCount = 2;

		setPreviousRuneLocation(movementList.get(movementList.size()-3));
	}
}
