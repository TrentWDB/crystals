package com.mygdx.crystals.managers;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.crystals.abilities.Fireball;
import com.mygdx.crystals.interfaces.Ability;
import com.mygdx.crystals.interfaces.Renderer;

/**
 * Created by Trent on 8/3/2014.
 */
public class AbilityManager {
    public static final Ability ABILITY_FIREBALL = new Fireball();
    public static final Ability ABILITY_NAME_2 = new Fireball();
    public static final Ability ABILITY_NAME_3 = new Fireball();
    public static final Ability ABILITY_NAME_4 = new Fireball();
    public static final Ability ABILITY_NAME_5 = new Fireball();
    public static final Ability ABILITY_NAME_6 = new Fireball();
    public static final Ability ABILITY_NAME_7 = new Fireball();
    public static final Ability ABILITY_NAME_8 = new Fireball();
    public static final Ability ABILITY_NAME_9 = new Fireball();
    public static final Ability ABILITY_NAME_10 = new Fireball();

    public static final Ability[][] CLASS_ABILITIES = {
        {//warrior

        }, {//rogue

        }, {//incarnate

        }, {//summoner

        }, {//sorcerer
            ABILITY_FIREBALL
        }, {//priest

        }, {//warlock

        }
    };

    public static Color[] getAbilityClassColors(Ability ability) {
        for (int i = 0; i < CLASS_ABILITIES.length; i++) {
            for (int a = 0; a < CLASS_ABILITIES[i].length; a++) {
                Ability curAbility = CLASS_ABILITIES[i][a];

                if (curAbility.equals(ability))
                    return ClassManager.CLASS_COLORS[i];
            }
        }

        return null;
    }

    public static void resizeAbilities() {
        for (int i = 0; i < CLASS_ABILITIES.length; i++) {
            for (int a = 0; a < CLASS_ABILITIES[i].length; a++) {
                Ability curAbility = CLASS_ABILITIES[i][a];
                //TODO dis gonna freakin break
                ((Renderer) curAbility).resize();
            }
        }
    }
}
