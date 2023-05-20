package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BuyWeaponAction;
import game.actions.SellWeaponAction;
import game.weapons.*;

/**
 * A class that represents Merchant Kale.
 * @see Trader
 * @see BuyWeaponAction
 * @see SellWeaponAction
 * @author Iliyana
 */
public class MerchantKale extends Trader{

    /**
     * Constructor for Trader class.
     *
     */
    public MerchantKale() {
        super("Merchant Kale", 'K');
    }

    /**
     * Method that deals with the transaction with a Player.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     * @param otherActor the Actor that may be making transaction with Trader
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return  a list of actions that can be performed by actors that can use the console menu. (e.g. player)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = new ActionList();

        // Player buy weapon from trader
        actionList.add(new BuyWeaponAction(new Club(),this));
        actionList.add(new BuyWeaponAction(new GreatKnife(), this));
        actionList.add(new BuyWeaponAction(new Uchigatana(), this));
        actionList.add(new BuyWeaponAction(new Scimitar(), this));
        actionList.add(new BuyWeaponAction(new HeavyCrossbow(), this));
        actionList.add(new BuyWeaponAction(new AstrologerStaff(), this));
        // Player sell weapon to trader
        for (WeaponItem weapon : otherActor.getWeaponInventory()){
            actionList.add(new SellWeaponAction(weapon, this));
        }
        return actionList;
    }
}
