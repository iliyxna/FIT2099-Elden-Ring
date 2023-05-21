package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.*;
import game.utils.PlayerResetStatus;
import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Damia, Iliyana
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		int damage = weapon.damage();
		result += attackSequence(actor, map, damage);
		return result;
	}

	/**
	 * Describes which target the actor is attacking with which weapon.
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}

	/**
	 * Getter method to retrieve the target.
	 * @return target of attack
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * Setter method to set the target.
	 * @param target target of attack
	 */
	public void setTarget(Actor target) {
		this.target = target;
	}

	/**
	 * Getter method to get the weapon used for attack.
	 * @return weapon used for attack.
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Setter method to set the weapon used for attack.
	 * @param weapon weapon used for attack
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Getter method to get the direction of the attack.
	 * @return direction of the attack
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Setter method to set the direction of the attack
	 * @param direction direction of the attack
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String hurtActor(Actor attacker, int damage){

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return attacker + " misses " + target + ".";
		}

		target.hurt(damage);
		return attacker + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
	}

	/**
	 * A method to check if player is dead. If player is dead, the reset action will be called
	 * to be executed.
	 * @param map GameMap the player is on.
	 * @return a string representing the death of player.
	 */
	public String checkPlayerDeath(GameMap map){
		String result = "";
		if (target.hasCapability(Status.PLAYER)){
			Player player = (Player) target;
			if (!target.isConscious()) {
				// Reset game when player dies
				player.addCapability(PlayerResetStatus.DIED);
				ResetAction resetAction = new ResetAction();
				result += resetAction.execute(player, map);
//                        result += "Value of runes dropped: $" + playerRune.getRuneValue();
			}
		}
		return result;
	}

	public String checkEnemyDeath(Actor attacker, GameMap map){
		String result = "";
		if (!target.isConscious()) {
			// Deals with spawning and de-spawning of skeletal types.
			// Pile of bones to be added to map
			PileOfBones pileOfBones = new PileOfBones();
			if (target instanceof HeavySkeletalSwordsman) {
				pileOfBones.setPreviousEnemy((Enemy)target);
				Location pos = map.locationOf(target);
				map.removeActor(target);
				map.addActor(pileOfBones, pos);
				pileOfBones.addWeaponToInventory(new Grossmesser());
				System.out.println("Heavy Skeletal Swordsman turns into Pile of Bones.");
			} else if (target instanceof SkeletalBandit){
				pileOfBones.setPreviousEnemy((Enemy)target);
				Location pos = map.locationOf(target);
				map.removeActor(target);
				map.addActor(pileOfBones, pos);
				pileOfBones.addWeaponToInventory(new Scimitar());
				System.out.println("Skeletal Bandit turns into Pile of Bones.");
			}else{
				result += new DeathAction(attacker).execute(target, map) ;
			}
		}
		return result;
	}

	/**
	 * After hurting the actor, check deaths of actor:
	 * 1. if the actor is a player, drop runes at previous location and execute ResetAction.
	 * 2. if the actor is an enemy, deal with the pile of bones to check previous enemy to know which
	 * item to drop
	 * @param attacker actor that is performing attack action
	 * @param map game map the actor is on
	 * @param damage damage to the attack
	 * @return description of the consequences of the attack
	 */
	public String attackSequence(Actor attacker, GameMap map, int damage){
		String result = "";

		result += hurtActor(attacker, damage);
		result += checkPlayerDeath(map);
		result += checkEnemyDeath(attacker,map);

		return result;
	}
}
