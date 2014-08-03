package com.mygdx.crystals.managers;

import com.mygdx.crystals.interfaces.Renderer;
import com.mygdx.crystals.renderers.hud.ActionBarRenderer;
import com.mygdx.crystals.renderers.mainmenu.TitleScreenRenderer;

/**
 * Created by Trent on 8/2/2014.
 */
public class RenderManager {
    public static TitleScreenRenderer titleScreenRenderer;
    public static ActionBarRenderer actionBarRenderer;

    public static final int STATE_TITLE_SCREEN = 0;
    public static final int STATE_CHARACTER_SCREEN = 1;
    public static final int STATE_DESIGN_SCREEN = 2;
    public static final int STATE_SETTINGS_SCREEN = 3;

    public static int currentState = STATE_TITLE_SCREEN;

    public static void initialize() {
        titleScreenRenderer = new TitleScreenRenderer();
        actionBarRenderer = new ActionBarRenderer();
    }

    public static Renderer getRenderObject() {
        switch (RenderManager.currentState) {
            case RenderManager.STATE_TITLE_SCREEN:
                return titleScreenRenderer;
        }

        return null;
    }
}
