package com.mygdx.crystals.managers;

import com.mygdx.crystals.interfaces.Renderer;
import com.mygdx.crystals.renderers.hud.AbilityBarRenderer;
import com.mygdx.crystals.renderers.mainmenu.TitleScreenRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trent on 8/2/2014.
 */
public class RenderManager {
    public static final Renderer[] RENDERERS = {
        new TitleScreenRenderer(),
        new AbilityBarRenderer()
    };

    public static final int RENDERER_TITLE_SCREEN = 0;
    public static final int RENDERER_ABILITY_BAR = 1;

    private static List<Integer> currentStates;

    public static void initialize() {
        currentStates = new ArrayList<Integer>();
        currentStates.add(RENDERER_TITLE_SCREEN);
        currentStates.add(RENDERER_ABILITY_BAR);

        for (int i = 0; i < RENDERERS.length; i++) {
            RENDERERS[i].init();
        }
    }

    //TODO I think this is good enough but I might want to let them put a renderer in a specific index (z-index)
    public static void addRenderer(int renderer) {
        currentStates.add(renderer);
    }

    public static void removeRenderer(int renderer) {
        //B)
        currentStates.remove(new Integer(renderer));
    }

    public static List<Renderer> getRenderObjects() {
        List<Renderer> rendererList = new ArrayList<Renderer>();

        for (int i = 0; i < currentStates.size(); i++) {
            rendererList.add(RENDERERS[currentStates.get(i)]);
        }

        return rendererList;
    }
}
