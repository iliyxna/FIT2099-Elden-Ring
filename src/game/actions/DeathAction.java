package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Enemy;
import game.actors.PileOfBones;
import game.actors.Player;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);

        // Display number of runes dropped and update player runes
        if (attacker.hasCapability(Status.PLAYER)) {
            Player player = (Player) attacker;

            if (target.hasCapability(Status.ENEMY)) {
                Enemy targetEnemy = (Enemy) target;
                player.getRuneManager().addRunes(targetEnemy.getEnemyRuneValue().getRuneValue());
                System.out.println("Runes dropped: " + targetEnemy.getEnemyRuneValue());
            } else if (target.hasCapability(Status.PILE_OF_BONES)) {
                // pile of bones could previously be HSS or Skeletal bandit
                PileOfBones pileOfBones = (PileOfBones) target;
                // HSS or skeletal bandit?
                Enemy targetEnemy = pileOfBones.getPreviousEnemy();
                player.getRuneManager().addRunes(targetEnemy.getEnemyRuneValue().getRuneValue());
                System.out.println("Runes dropped: " + targetEnemy.getEnemyRuneValue());
            }
        }
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + menuDescription(target);
            return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
