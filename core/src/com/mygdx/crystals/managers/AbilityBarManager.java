package com.mygdx.crystals.managers;

import com.mygdx.crystals.interfaces.Ability;

/**
 * Created by Trent on 8/3/2014.
 */
public class AbilityBarManager {
    public static final int TOTAL_ABILITY_COUNT = 12;
    public static final int TOTAL_GLOBAL_COOLDOWN = 500;

    public static Ability[] currentAbilities;
    public static int globalCooldown = TOTAL_GLOBAL_COOLDOWN;

    public static void initialize() {
        currentAbilities = new Ability[TOTAL_ABILITY_COUNT];
        currentAbilities[0] = AbilityManager.ABILITY_FIREBALL;
    }
}
