package game.items;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * A poisonous interface.
 * @author Damia
 */
public interface Poisonous {

    /**
     * A method that is resposible for inflicting damage to actors.
     * @param actor actor that will be dealt with damage
     */
    void inflictDamage(Actor actor);

    /**
     * A method to get the damage that will be dealt to an actor.
     * @return damage that will be dealt to an actor
     */
    int getDamage();

    /**
     * A method to get the number of rounds that the actor will be dealt with damage.
     * @return number of rounds that the actor will be dealt with damage.
     */
    int getRounds();

    /**
     * A method to decrease the number of rounds by 1.
     */
    void decreaseRounds();
}
