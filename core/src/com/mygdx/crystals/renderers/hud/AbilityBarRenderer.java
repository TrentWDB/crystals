package com.mygdx.crystals.renderers.hud;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by Trent on 7/31/2014.
 */
public class AbilityBarRenderer implements Renderer {
    public FrameBuffer[] abilityIcons;
    public int width;
    public int height;
    public int iconSize;

    //divide the screen height by this to get icon size because I like integers
    public static final int ICON_HEIGHT_DIVIDE = 20;

    public AbilityBarRenderer() {
    }

    public void init() {
        abilityIcons  = new FrameBuffer[AbilityBarManager.TOTAL_ABILITY_COUNT];
        generateNewDimensions();
        generateAbilityIcons();
    }

    public void resize() {
        generateNewDimensions();
        generateAbilityIcons();
    }

    public void generateNewDimensions() {
        int[] resolution = SettingsManager.getResolution();

        iconSize = resolution[1] / ICON_HEIGHT_DIVIDE;
        width = iconSize * 12 + 11;
        height = iconSize;
    }

    public void generateAbilityIcons() {
        for (int i = 0; i < abilityIcons.length; i++) {
            generateIcon(i);
        }
    }

    public void generateIcon(int abilityPos) {
        if (abilityIcons[abilityPos] != null)
            abilityIcons[abilityPos].dispose();
        FrameBuffer curIconFBO = new FrameBuffer(Pixmap.Format.RGBA8888, iconSize, iconSize, false);
        abilityIcons[abilityPos] = curIconFBO;

        Ability currentAbility = AbilityBarManager.currentAbilities[abilityPos];
        if (currentAbility != null)
            currentAbility.generateIcon(curIconFBO);
        else
            generateNullIcon(curIconFBO);
    }

    private void generateNullIcon(FrameBuffer frameBuffer) {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;

        Color backgroundColor = ClassManager.CLASS_COLOR_NULL;
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

            batch.end();
        frameBuffer.end();
    }

    public void render() {
        //this method is wonky because everything has to be scaled properly, but 1 pixel lines have to show up
        //this was fixed by setting the view to the window size and then manually scaling everything but the lines
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;
        int[] resolution = SettingsManager.getResolution();

        //get the scale and scaled value variables which are left as doubles for precisions sake
        double scaleToWindowX = (double) Gdx.graphics.getWidth() / resolution[0];
        double scaleToWindowY = (double) Gdx.graphics.getHeight() / resolution[1];
        double startX = (resolution[0] * scaleToWindowX / 2.0 - width * scaleToWindowX / 2);
        double scaledIconSizeX = iconSize * scaleToWindowX;
        double scaledIconSizeY = iconSize * scaleToWindowY;

        //create the divider line with math.max so that it can be stretched but not shrunk
        int dividerLineWidth = Math.max(1, (int) (1 * scaleToWindowX));
        Pixmap iconDividerLinePixmap = new Pixmap(dividerLineWidth, (int) scaledIconSizeY, Pixmap.Format.RGBA8888);
        iconDividerLinePixmap.setColor(FinalManager.COLOR_BLACK);
        iconDividerLinePixmap.fill();
        Texture iconDividerLineTexture = new Texture(iconDividerLinePixmap);
        iconDividerLinePixmap.dispose();

        //set to window dimensions so that the 1 pixel lines get drawn
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

            for (int i = 0; i < abilityIcons.length; i++) {
                TextureRegion curIconTextureRegion = new TextureRegion(abilityIcons[i].getColorBufferTexture());
                curIconTextureRegion.flip(false, true);

                //gets the start of the current ability and start of next ability to avoid gaps due to casting errors
                //draw the ability texture
                int curIconStartX = (int) (startX + scaledIconSizeX * i) + i * dividerLineWidth;
                int nextIconStartX = (int) (startX + scaledIconSizeX * (i + 1)) + (i + 1) * dividerLineWidth;
                batch.draw(curIconTextureRegion.getTexture(), curIconStartX, 0,
                        nextIconStartX - curIconStartX - dividerLineWidth, (int) (scaledIconSizeY));

                //draw the divider line
                if (i > 0)
                    batch.draw(iconDividerLineTexture, curIconStartX - dividerLineWidth, 0);
            }

        batch.end();

        iconDividerLineTexture.dispose();
    }
}
