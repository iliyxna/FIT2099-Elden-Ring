package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.utils.Status;


/**
 * A class that represents RawMeatDumpling. Consuming it will increase
 * health by 75, but at the same time it will inflict 25 damage
 * to actors for 3 rounds due to it being poisonous.
 * @author Damia
 *
 */
public class RawMeatDumpling extends Item implements Consumable, Poisonous{
    /**
     * Heal amount of a Raw Meat dumpling.
     */
    private final int HEAL_AMOUNT = 75;

    /**
     * Poison damage inflicted by eating a Raw Meat dumpling.
     */
    private final int POISON_DAMAGE = 25;

    /**
     * The total of rounds the player will be poisoned.
     */
    private int poisonRounds;

    /**
     * Constructor for Raw meat dumpling.
     */
    public RawMeatDumpling() {
        super("Raw Meat Dumpling", 'ლ', true);
        this.addCapability(Status.CONSUMABLE);
        this.addCapability(Status.POISONOUS_ITEM);
        this.poisonRounds = 3;
    }

    /**
     * A method that contains the consequence of consuming a raw meat dumpling.
     * @param actor actor that consumes the raw meat dumpling.
     * @return a string that represents the consumption of the raw meat dumpling
     */
    @Override
    public String consumeItem(Actor actor) {
        if (actor.hasCapability(Status.PLAYER)){
            ((Player)actor).setLastPoisonousItemConsumed(this);
        }
        actor.heal(HEAL_AMOUNT);
        inflictDamage(actor);
        actor.addCapability(Status.POISONED);
        actor.removeItemFromInventory(this);
        return "Raw meat dumpling has been consumed. HP increased by 75, Poison status inflicted.";
    }

    /**
     * A method that is resposible for inflicting damage to actors.
     * @param actor actor that will be dealt with damage
     */
    @Override
    public void inflictDamage(Actor actor) {
        actor.hurt(POISON_DAMAGE);
    }

    /**
     * A method to get the damage that will be dealt to an actor.
     * @return damage that will be dealt to an actor
     */
    @Override
    public int getDamage() {
        return POISON_DAMAGE;
    }

    /**
     * A method to get the number of rounds that the actor will be dealt with damage.
     * @return number of rounds that the actor will be dealt with damage.
     */
    @Override
    public int getRounds() {
        return this.poisonRounds;
    }

    /**
     * A method to decrease the number of rounds by 1.
     */
    @Override
    public void decreaseRounds() {
        this.poisonRounds--;
    }


}
