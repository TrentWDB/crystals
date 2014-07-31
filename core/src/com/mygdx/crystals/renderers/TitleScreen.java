package com.mygdx.crystals.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.crystals.Crystals;
import com.mygdx.crystals.managers.FontManager;
import com.mygdx.crystals.managers.SettingsManager;

/**
 * Created by Trent on 7/21/2014.
 */
public class TitleScreen {
    private static FrameBuffer[] bgImageFBOArray;
    private static FrameBuffer titleImageFBO;
    private static FrameBuffer[] menuImageFBOArray;

    public static void generateFrames() {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;
        int[] resolution = SettingsManager.getResolution();

        Texture fullBgImage = new Texture(Gdx.files.internal("res/images/background01.png"));
        renderBgImages(fullBgImage);

        renderTitleImage();
    }

    private static void renderBgImages(Texture fullBgImage) {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;
        int[] resolution = SettingsManager.getResolution();

        cam.setToOrtho(false, resolution[0], resolution[1]);
        batch.setProjectionMatrix(cam.combined);

        //to animate the title screen for 20 seconds
        bgImageFBOArray = new FrameBuffer[1];
        for (int i = 0; i < bgImageFBOArray.length; i++) {
            FrameBuffer curFrameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, resolution[0], resolution[1], false);
            bgImageFBOArray[0] = curFrameBuffer;

            curFrameBuffer.begin();
                batch.begin();

                    batch.draw(fullBgImage, 0, 0, resolution[0], resolution[1]);

                batch.end();
            curFrameBuffer.end();
        }
    }

    private static void renderTitleImage() {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;

        int[] textBounds = FontManager.getRealBounds(FontManager.titleScreenTitleFont, "crystals");

        //zoom in on the frame buffer to keep perspective
        cam.setToOrtho(false, textBounds[0], textBounds[1]);
        batch.setProjectionMatrix(cam.combined);

        titleImageFBO = new FrameBuffer(Pixmap.Format.RGBA8888, textBounds[0], textBounds[1], false);
        titleImageFBO.begin();
            batch.begin();

                FontManager.titleScreenTitleFont.draw(batch, "crystals", 0, textBounds[1]);

            batch.end();
        titleImageFBO.end();
    }

    public static void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;
        int[] resolution = SettingsManager.getResolution();

        batch.setProjectionMatrix(cam.combined);
        cam.setToOrtho(false, resolution[0], resolution[1]);

        FrameBuffer curBgImageFBO = bgImageFBOArray[0];

        TextureRegion curBgTextureRegion = new TextureRegion(curBgImageFBO.getColorBufferTexture());
        curBgTextureRegion.flip(false, true);
        TextureRegion curTitleTextureRegion = new TextureRegion(titleImageFBO.getColorBufferTexture());
        curTitleTextureRegion.flip(false, true);
        BitmapFont.TextBounds textBounds = FontManager.titleScreenTitleFont.getBounds("crystals");
        int textX = (int) (resolution[0] - textBounds.width - resolution[0] / 10);
        int textY = (int) (resolution[1] / 2 + FontManager.titleScreenTitleFont.getDescent());

        batch.begin();

            batch.draw(curBgTextureRegion, 0, 0);

            batch.enableBlending();
            batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ONE_MINUS_SRC_ALPHA);
            batch.draw(curTitleTextureRegion, textX, textY);
            batch.disableBlending();

        batch.end();
    }
}
