package com.mygdx.crystals.abilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mygdx.crystals.Crystals;
import com.mygdx.crystals.interfaces.Ability;
import com.mygdx.crystals.interfaces.Renderer;
import com.mygdx.crystals.managers.*;
import com.mygdx.crystals.renderers.hud.AbilityBarRenderer;

import java.awt.geom.Point2D;

/**
 * Created by Trent on 8/3/2014.
 */
public class Fireball implements Ability, Renderer {
    private static final FileHandle iconFileHandle = Gdx.files.internal("res/icons/abilities/fireball.png");//TODO make svg

    private static final int DEFENSE_RATING = 10;
    private static final int TOTAL_COOLDOWN = 0;
    private static final int TOTAL_CHARGES = 1;
    private static final int MANA_COST = 200;

    private int cooldown = TOTAL_COOLDOWN;
    private int charges = 0;

    private int iconSize;

    public Fireball() {
        init();
    }

    @Override
    public int getDefenseRating() {
        return DEFENSE_RATING;
    }

    @Override
    public int getTotalCooldown() {
        return TOTAL_COOLDOWN;
    }

    @Override
    public int getCurrentCooldown() {
        return cooldown;
    }

    @Override
    public int getTotalCharges() {
        return TOTAL_CHARGES;
    }

    @Override
    public int getCurrentCharges() {
        return charges;
    }

    @Override
    public int getManaCost() {
        return MANA_COST;
    }

    @Override
    public void generateIcon(FrameBuffer frameBuffer) {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;

        int[] resolution = SettingsManager.getResolution();
        iconSize = resolution[1] / AbilityBarRenderer.ICON_HEIGHT_DIVIDE;

        TextureRegion iconTextureRegion = new TextureRegion(new Texture(iconFileHandle));
        iconTextureRegion.flip(false, true);

        Color[] classColors = AbilityManager.getAbilityClassColors(this);
        Color backgroundColor = classColors[ClassManager.CLASS_COLOR];
        Color iconColor = classColors[ClassManager.CLASS_COLOR_DARKER];
        Pixmap backgroundPixmap = new Pixmap(iconSize, iconSize, Pixmap.Format.RGBA8888);
        backgroundPixmap.setColor(backgroundColor);
        backgroundPixmap.fill();

        Texture backgroundTexture = new Texture(backgroundPixmap);
        backgroundPixmap.dispose();

        //zoom in on the ability icon to keep perspective
        cam.setToOrtho(false, iconSize, iconSize);
        batch.setProjectionMatrix(cam.combined);
        frameBuffer.begin();
            batch.begin();

                batch.draw(backgroundTexture, 0, 0);
                batch.setColor(iconColor);
                batch.draw(iconTextureRegion, iconSize / 10, iconSize / 10, iconSize - iconSize / 5, iconSize - iconSize / 5);//TODO dont actually scale it
                batch.setColor(FinalManager.COLOR_TRUE_WHITE);

            batch.end();
        frameBuffer.end();
    }

    @Override
    public void update(Point2D.Double destination) {

    }

    @Override
    public void render() {

    }

    @Override
    public void init() {
        resize();
    }

    @Override
    public void resize() {
        //TODO reload icon image at new size
    }
}
