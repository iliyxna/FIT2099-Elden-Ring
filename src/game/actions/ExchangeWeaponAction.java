package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.actors.Trader;
import game.transactions.Exchangeable;
import game.items.RemembranceOfTheGrafted;
import game.utils.Status;

/**
 * An action class that allows Player to exchange Remembrance of the Grafted
 * for one of Godrick The Grafted's weapon (Axe of Godrick or Grafted Dragon).
 * @see Action
 * @see Trader
 * @author Iliyana
 */
public class ExchangeWeaponAction extends Action {

    /**
     * Weapon to be traded.
     */
    private Exchangeable weapon;

    /**
     * Trader that deals with transaction.
     */
    private Trader trader;

    /**
     * Constructor for ExchangeWeaponAction
     * @param weaponItem weapon to be traded
     * @param trader trader that deals with transaction
     */
    public ExchangeWeaponAction(WeaponItem weaponItem, Trader trader){
        this.weapon = (Exchangeable) weaponItem;
        this.trader = trader;
    }

    /**
     * When executed, check if player has the Remembrance of the Grafted item.
     * If present, proceed to trade weapon of player's choice with the item.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (actor.hasCapability(Status.PLAYER)){
            Player player = (Player) actor;
            for (Item item : player.getItemInventory()){
                if (item.getClass() == RemembranceOfTheGrafted.class){
                    actor.removeItemFromInventory(item);
                    break; // in case player have more than one remembrance of the grafted
                }
            }
            player.addWeaponToInventory((WeaponItem) weapon);
            result += player + " traded Remembrance of The Grafted for " + weapon.toString();
        }
        return result;
    }

    /**
     * Description of the exchange item action
     * @param actor The actor performing the action.
     * @return a description of the exchange item action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Trade Remembrance of The Grafted for " + weapon.toString() + " with " + trader;
    }
}
