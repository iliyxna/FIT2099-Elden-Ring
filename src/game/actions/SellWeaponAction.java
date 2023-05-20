package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.transactions.Sellable;
import game.actors.Player;
import game.actors.Trader;

/**
 * An action class that allows Player to sell weapon to Trader.
 * Note that it's from PLAYER to TRADER
 * @author Iliyana Samsudin
 * @see Action
 * @see Trader
 * @version 1.0
 */
public class SellWeaponAction extends Action {
    /**
     * Weapon to be sold.
     */
    private Sellable weapon;

    /**
     * Constructor for SellWeaponAction.
     * @param weapon weapon to be sold
     */
    public SellWeaponAction(WeaponItem weapon){
        this.weapon = (Sellable) weapon;
    }

    /**
     * When executed, check if the item is in the player's inventory. If present, proceed
     * with transaction.
     * @param seller The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor seller, GameMap map) {
        String ret = "";
        if (seller.hasCapability(Status.PLAYER)){
            Player player = (Player) seller;
            int weaponPrice = weapon.getSellPrice().getRuneValue();
            // Transaction
            // check if player have the weapon in their inventory
            for (int i = 0; i < player.getWeaponInventory().size(); i++){
                if(player.getWeaponInventory().get(i).getClass() == weapon.getClass()){
                    player.removeWeaponFromInventory((WeaponItem) weapon);
                    player.getRuneManager().addRunes(weaponPrice);
                    ret =  weapon.toString() + " has been removed from inventory.";
                } else {
                    ret =  weapon.toString() + " not in inventory.";
                }
            }
        }
        return ret;
    }

    /**
     * Description of the sell weapon action.
     * @param actor The actor performing the action.
     * @return a description of the sell weapon action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell Weapon: " + weapon.toString() +  " for $" + weapon.getSellPrice().getRuneValue() + " to Merchant Kale";
    }

}


