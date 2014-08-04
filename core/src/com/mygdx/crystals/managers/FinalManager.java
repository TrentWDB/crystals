package com.mygdx.crystals.managers;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.gson.Gson;

import java.awt.Toolkit;

/**
 * Created by Trent on 7/27/2014.
 */
public class FinalManager {
    public static final Gson gson = new Gson();
    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static final Color COLOR_TITLE_SCREEN_TITLE_FONT = new Color(0xde00ff96);
    public static final Color COLOR_TITLE_SCREEN_MENU_FONT = new Color(0xffffff96);

    public static final Color COLOR_TRUE_WHITE = new Color(0xffffffff);
    public static final Color COLOR_WHITE = new Color(0xffffffff);
    public static final Color COLOR_BLACK = new Color(0x000000ff);
}
