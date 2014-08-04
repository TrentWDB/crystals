package com.mygdx.crystals.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import java.awt.geom.Point2D;

/**
 * Created by Trent on 8/3/2014.
 */
public abstract interface Ability {
    public static final int TYPE_CAST = 0;
    public static final int TYPE_INSTANT = 1;
    public static final int TYPE_CHANNEL = 2;
    public int getDefenseRating();
    public int getTotalCooldown();
    public int getCurrentCooldown();
    public int getTotalCharges();
    public int getCurrentCharges();
    //if the ability is a channel ability its the mana cost per millisecond
    public int getManaCost();

    public void generateIcon(FrameBuffer frameBuffer);

    public void update(Point2D.Double destination);
}
