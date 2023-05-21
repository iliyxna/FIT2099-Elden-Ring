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
import game.actions.*;
import game.items.*;
import game.reset.ResetManager;
import game.rune.Rune;
import game.rune.RuneManager;
import game.utils.MapSize;
import game.utils.PlayerResetStatus;
import game.utils.RoleManager;
import game.weapons.*;
import game.reset.Resettable;
import game.utils.Status;

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

	/**
	 * Attribute for the Menu
	 */
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
	 * Map of the last lost grace.
	 */
	private GameMap mapOfLastLostGrace;

	/**
	 * A list of locations of the movements of the player.
	 */
	private List<Location> movementList = new ArrayList<>();

	/**
	 * The previous location of the dropped runes when the player died.
	 */
	private Location previousRuneLocation;

	/**
	 * Stores the map that contains the runes that was previously dropped by the player.
	 */
	private GameMap mapOfPreviousRune;

	/**
	 * For calculating the price for upgrading HP in site of lost grace.
	 */
	private int upgradeHPCount;

	/**
	 * For other classes to check if player is dead
	 */
	private static boolean isPlayerDead;

	/**
	 * Attribute for last poisonous item consumed by the player
	 */

	private Poisonous lastPoisonousItemConsumed;

	/**
	 * Constructor for Player class. The chooseClass() method is invoked in the
	 * constructor so that player's will get to choose their player class
	 * (Samurai, Bandit, Wretch) at the start of the game.
	 * @see Player#chooseClass()
	 */
	public Player() {
		super("Tarnished", '@', 300);
		// add to list of resettables for game reset
		ResetManager.getInstance().registerResettable(this);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.addCapability(Status.CAN_ENTER_FLOOR);
		this.addCapability(Status.SITE_OF_LOSTGRACE);
		this.upgradeHPCount = 0;
		// depending on class, the hitpoints will change
		chooseClass();

		// Add flask of crimson tears
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new CrimsonTears());
		this.addItemToInventory(new NeutralisingBolus());
		this.addItemToInventory(new RawMeatDumpling());
		this.addItemToInventory(new GoldenSeeds());

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

		// To record which map player is on
		MapSize.setPlayerMap(map);

		// To record the movement of players (Used for dropping runes at previous location before player died)
		movementList.add(playerLocation);

		if (this.hasCapability(Status.POISONED)){
			display.println("PLAYER IS POISONED");
			checkPoisonStatus(lastPoisonousItemConsumed);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display number of runes player is holding
		display.println(this + "'s current hit points: " + this.printHp());
		display.println(this + "'s rune value: $" + runeManager.getTotalRunes().getRuneValue());
		display.println("Crimson Flask available uses remaining: " + this.getCrimsonFlaskCount());

		// Deal with AOE attack to avoid repetition of lines (multiple AOE attack lines for same area) in menu
		if (this.getWeaponInventory().size() != 0){
			WeaponItem currentWeapon = this.getWeaponInventory().get(0);
			if (currentWeapon.getClass() == (Grossmesser.class) || currentWeapon.getClass() == (Scimitar.class) ||
					currentWeapon.getClass() == (AxeOfGodrick.class) || currentWeapon.getClass() == (GraftedDragon.class)) {
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
	 * A method to increase the number of uses for Flask of Crimson Tears by 1.
	 */
	public void increaseCrimsonFlaskCount() {
		this.crimsonFlaskCount++;
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
	 * Getter method to get the map of last lost grace visited.
	 * @return the last lost grace visited by the player.
	 */
	public GameMap getMapOfLastLostGrace() {
		return mapOfLastLostGrace;
	}

	/**
	 * Setter method to set the map of last lost grace visited.
	 * @param mapOfLastLostGrace last lost grace visited by the player
	 */
	public void setMapOfLastLostGrace(GameMap mapOfLastLostGrace) {
		this.mapOfLastLostGrace = mapOfLastLostGrace;
	}

	/**
	 * Getter method for the list of movements of the player.
	 * @return the list of movements of the player.
	 */
	public List<Location> getMovementList() {
		return movementList;
	}


	/**
	 * Setter method for the previous rune location of the player.
	 * @param previousRuneLocation the previous rune location of the player.
	 */
	public void setPreviousRuneLocation(Location previousRuneLocation) {
		this.previousRuneLocation = previousRuneLocation;
	}

	/**
	 * Getter method for the previous rune location of the player.
	 * @return  previousRuneLocation the previous rune location of the player.
	 */
	public Location getPreviousRuneLocation() {
		return previousRuneLocation;
	}

	/**
	 * Getter to set previous map of the dropped rune.
	 * @return  mapOfPreviousRune previous map of the dropped rune.
	 */
	public GameMap getMapOfPreviousRune() {
		return mapOfPreviousRune;
	}

	/**
	 * Setter to set previous map of the dropped rune.
	 * @param mapOfPreviousRune previous map of the dropped rune.
	 */
	public void setMapOfPreviousRune(GameMap mapOfPreviousRune) {
		this.mapOfPreviousRune = mapOfPreviousRune;
	}

	/**
	 * A method to print the Role menu item to the console at the start of the game.
	 * @see RoleManager
	 */
	public void chooseClass(){
		int selection;
		selection = roleManager.menuItem();
		roleManager.selectedClass(this, selection);
	}

	/**
	 * Method that will be invoked during the reset of the game.
	 * @param map the game map the player is on
	 */
	@Override
	public void reset(GameMap map) {
		// Reset player's hit points to maximum
		this.resetMaxHp(getPlayerMaxHP());
		new Display().println(this + "'s HP has been reset to: " + this.hitPoints);
		// Reset the number of uses of crimson flask to max num of uses
		this.crimsonFlaskCount = 2;

		// If player is dead then drop runes. If player is just resting, runes will not be dropped.
		if (getIsPlayerDead()){
			// Dropping on runes
			// Retrieve the location before player died.
			Location dropLocation;
			if (this.getMovementList().size() <= 1){
				dropLocation = map.locationOf(this);
			} else {
				dropLocation = this.getMovementList().get(this.getMovementList().size()-2);
			}

			Rune playerRune = this.getRuneManager().getTotalRunes();

			// Use this instead of drop action because we want to drop at the previous location.
			this.getRuneManager().removeRunes();
			map.at(dropLocation.x(),dropLocation.y()).addItem(playerRune);
			// need to keep track of previous rune location and which map it was dropped on
			this.setPreviousRuneLocation(dropLocation);
			this.setMapOfPreviousRune(map);
		}
	}

	/**
	 * Getter method for the intrinsic weapon used by player
	 * @return the intrinsic weapon used by player
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches", 100);
	}

	/**
	 * Getter method to get the number of HP upgrades.
	 * @return number of HP upgrades done by player
	 */
	public int getUpgradeHPCount() {
		return upgradeHPCount;
	}

	/**
	 * Increase the number of HP upgrades by 1.
	 */
	public void increaseUpgradeHPCount(){
		this.upgradeHPCount++;
	}

	/**
	 * Getter method to retrieve the max HP of player.
	 * @return max HP of player
	 */
	public int getPlayerMaxHP(){
		return this.maxHitPoints;
	}

	/**
	 * Getter method that returns a boolean value depending on the player being dead or not.
	 * @return true if player is dead, false otherwise.
	 */
	public static boolean getIsPlayerDead() {
		return isPlayerDead;
	}

	/**
	 * Setter method to set the boolean value to true if player is dead and false if not dead.
	 * @param isPlayerDead true if player is dead, false otherwise.
	 */
	public static void setIsPlayerDead(boolean isPlayerDead) {
		Player.isPlayerDead = isPlayerDead;
	}

	/**
	 * Method that checks whether player is poisoned or not. If poisoned, then inflict damage.
	 * During the last round, remove the poisoned status.
	 * @param poisonousItem a poisonous item that was consumed by the player
	 */
	public void checkPoisonStatus(Poisonous poisonousItem){
		if (poisonousItem.getRounds() > 0){
			poisonousItem.inflictDamage(this);
			poisonousItem.decreaseRounds();
		}
		if (poisonousItem.getRounds() == 0){
			this.removeCapability(Status.POISONED);
		}
	}

	/**
	 * Setter method to set the last poisonous item consumed by the player.
	 * @param lastPoisonousItemConsumed last poisonous item consumed by the player.
	 */
	public void setLastPoisonousItemConsumed(Poisonous lastPoisonousItemConsumed) {
		this.lastPoisonousItemConsumed = lastPoisonousItemConsumed;
	}
}
