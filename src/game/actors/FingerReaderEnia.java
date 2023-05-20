package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BuyWeaponAction;
import game.actions.ExchangeWeaponAction;
import game.actions.SellItemAction;
import game.actions.SellWeaponAction;
import game.items.RemembranceOfTheGrafted;
import game.utils.Status;
import game.weapons.*;

/**
 * A class that represents the FingerReaderEnia
 * @see Trader
 * @see BuyWeaponAction
 * @see SellWeaponAction
 * @author Iliyana
 */
public class FingerReaderEnia extends Trader{

    /**
     * Constructor for Trader class.
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E');
    }

    /**
     * Method that deals with the transaction with a Player.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     *
     * @param otherActor the Actor that is buying a weapon
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that can be performed by actors that can use the console menu. (e.g. player)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = new ActionList();

        // Player sell weapon to trader
        for (WeaponItem weapon : otherActor.getWeaponInventory()){
            actionList.add(new SellWeaponAction(weapon, this));
        }

        // Player sell item to trader
        for (Item item : otherActor.getItemInventory()){
            if (item.hasCapability(Status.SELLABLE)){
                actionList.add(new SellItemAction(item, this));
            }
        }

        for (Item item: otherActor.getItemInventory()){
            if (item.getClass() == RemembranceOfTheGrafted.class){
                actionList.add(new ExchangeWeaponAction(new AxeOfGodrick(), this));
                actionList.add(new ExchangeWeaponAction(new GraftedDragon(), this));
            }
        }
        return actionList;
    }
}
