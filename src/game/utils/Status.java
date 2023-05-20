package game.utils;

/**
 * Use this enum class to give `buff` or `debuff`.
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    HOSTILE_TO_ENEMY,
    RESPAWNABLE,
    RESTING,
    CAN_DESPAWN_WHEN_RESET, // For future implementation, new enemy added may not be able to be de-spawned
    HEAVY_SKELETAL_SWORDSMAN,
    GIANT_CRAB,
    LONE_WOLF,
    GIANT_DOG,
    GIANT_CRAYFISH,
    PILE_OF_BONES,
    CAN_ENTER_FLOOR,
    SITE_OF_LOSTGRACE,
    ENEMY,
    PLAYER,
    CONSUMABLE,  // for items
    SKELETAL_TYPE, // HSS, Skeletal bandit
    DOG_TYPE,   // Lone wolf, Giant dog
    WATER_TYPE, // Giant crab, Giant crayfish
    ENEMY_WEAPON_SKILL,
    UNIQUE_SKILL,
    RANGED_ATTACK,
    DOG,
    SC_CREATURES_TYPE, // Dog
    GODRICK_SOLDIER
}
