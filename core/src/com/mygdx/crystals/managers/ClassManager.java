package com.mygdx.crystals.managers;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.crystals.interfaces.Ability;

/**
 * Created by Trent on 8/3/2014.
 */
public class ClassManager {
    public static final int CLASS_WARRIOR = 0;
    public static final int CLASS_ROGUE = 1;
    public static final int CLASS_INCARNATE = 2;
    public static final int CLASS_SUMMONER = 3;
    public static final int CLASS_SORCERER = 4;
    public static final int CLASS_PRIEST = 5;
    public static final int CLASS_WARLOCK = 6;

    public static final int CLASS_COLOR = 0;
    public static final int CLASS_COLOR_DARKER = 1;

    public static final Color CLASS_COLOR_NULL = new Color(0x111111ee);
    public static final Color CLASS_COLOR_RED = new Color(0x7f2517ff);
    public static final Color CLASS_COLOR_RED_DARKER = new Color(0x56190fff);
    public static final Color CLASS_COLOR_GRAY = new Color(0x3b3b3bff);
    public static final Color CLASS_COLOR_GRAY_DARKER = new Color(0x272727ff);
    public static final Color CLASS_COLOR_GREEN = new Color(0x5c833aff);
    public static final Color CLASS_COLOR_GREEN_DARKER = new Color(0x48662dff);
    public static final Color CLASS_COLOR_ORANGE = new Color(0xe1793dff);
    public static final Color CLASS_COLOR_ORANGE_DARKER = new Color(0xbf6733ff);
    public static final Color CLASS_COLOR_BLUE = new Color(0x056380ff);
    public static final Color CLASS_COLOR_BLUE_DARKER = new Color(0x03495eff);
    public static final Color CLASS_COLOR_WHITE = new Color(0xd8d8d7ff);
    public static final Color CLASS_COLOR_WHITE_DARKER = new Color(0xc3c3c3ff);
    public static final Color CLASS_COLOR_PURPLE = new Color(0x7e2064ff);
    public static final Color CLASS_COLOR_PURPLE_DARKER = new Color(0x60184cff);

    public static final Color[][] CLASS_COLORS = {
        {//warrior
            CLASS_COLOR_RED,
            CLASS_COLOR_RED_DARKER
        }, {//rogue
            CLASS_COLOR_GRAY,
            CLASS_COLOR_GRAY_DARKER
        }, {//incarnate
            CLASS_COLOR_GREEN,
            CLASS_COLOR_GREEN_DARKER
        }, {//summoner
            CLASS_COLOR_ORANGE,
            CLASS_COLOR_ORANGE_DARKER
        }, {//sorcerer
            CLASS_COLOR_BLUE,
            CLASS_COLOR_BLUE_DARKER
        }, {//priest
            CLASS_COLOR_WHITE,
            CLASS_COLOR_WHITE_DARKER
        }, {//warlock
            CLASS_COLOR_PURPLE,
            CLASS_COLOR_PURPLE_DARKER
        }
    };
}
