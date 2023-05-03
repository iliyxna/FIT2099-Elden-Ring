package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.*;
import game.reset.Resettable;
import game.rune.Rune;
import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
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
		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// DEALS WITH PLAYER'S DEATH
		if (target.hasCapability(Status.PLAYER)){
			Player player = (Player) target;
			if (!target.isConscious()){
				// Retrieve the location before player died.
				Location dropLocation;
				if (player.getMovementList().size() <= 1){
					dropLocation = map.locationOf(player);
				} else {
					dropLocation = player.getMovementList().get(player.getMovementList().size()-2);
				}
				Rune playerRune = player.getRuneManager().getTotalRunes();
				// Use this instead of drop action because we want to drop at the previous location.
				player.getRuneManager().removeRunes();
				map.at(dropLocation.x(),dropLocation.y()).addItem(playerRune);

				// Reset game when player dies
				ResetAction resetAction = new ResetAction();
				result += resetAction.execute(player,map);
				result += "Value of runes dropped: $" + playerRune.getRuneValue();

				return result;
			}
		}

		// Deals with unconscious actors
		else if (!target.isConscious()) {
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
				result += new DeathAction(actor).execute(target, map);
			}
		}
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
}
