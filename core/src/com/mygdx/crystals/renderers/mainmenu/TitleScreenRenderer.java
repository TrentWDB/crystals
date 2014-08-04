package com.mygdx.crystals.renderers.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mygdx.crystals.Crystals;
import com.mygdx.crystals.interfaces.Renderer;
import com.mygdx.crystals.managers.FontManager;
import com.mygdx.crystals.managers.SettingsManager;
import com.mygdx.crystals.managers.mainmenu.TitleScreenManager;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Trent on 7/21/2014.
 */
public class TitleScreenRenderer implements Renderer {
    private static Texture[] bgImageTextureArray;
    private static FrameBuffer titleImageFBO;//TODO maybe change to a texture eventually
    //index 0 is the solid text, index 1 is the slightly transparent text for a glow
    private static FrameBuffer[] menuImageFBOArray;

    public TitleScreenRenderer() {
    }

    public void init() {
        generateFrames();
    }

    public void resize() {
        generateFrames();
    }

    public void generateFrames() {
        bgImageTextureArray = new Texture[1];
        menuImageFBOArray = new FrameBuffer[TitleScreenManager.titleScreenMenuOptions.length];

        try {
            renderBgImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        renderTitleTextImage();

        renderMenuImageFBO();
    }

    private static void renderBgImages() throws IOException {
        int[] resolution = SettingsManager.getResolution();

        //load full background image
        File fullBgImageFile = Gdx.files.internal("res/images/background01.png").file();
        BufferedImage fullBgImage = ImageIO.read(fullBgImageFile);

        //scale full background image and get new image
        double scaleX = (double) resolution[0] / fullBgImage.getWidth();
        double scaleY = (double) resolution[1] / fullBgImage.getHeight();
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage scaledBgImage = bilinearScaleOp.filter(
                fullBgImage,
                new BufferedImage(resolution[0], resolution[1], fullBgImage.getType()));

        //write new image to pixmap and then to texture
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledBgImage, "jpg", baos);
        baos.flush();
        byte[] scaledBgImageByteArray = baos.toByteArray();
        baos.close();

        Pixmap finalBgPixmap = new Pixmap(scaledBgImageByteArray, 0, scaledBgImageByteArray.length);
        Texture finalBgTexture = new Texture(finalBgPixmap);
        finalBgPixmap.dispose();

        for (int i = 0; i < bgImageTextureArray.length; i++) {
            bgImageTextureArray[i] = finalBgTexture;
        }
    }

    private void renderTitleTextImage() {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;

        int[] textBounds = FontManager.getRealBounds(FontManager.titleScreenTitleFont, "crystals");

        if (titleImageFBO != null)
            titleImageFBO.dispose();
        titleImageFBO = new FrameBuffer(Pixmap.Format.RGBA8888, textBounds[0], textBounds[1], false);

        //zoom in on the frame buffer to keep perspective
        cam.setToOrtho(false, textBounds[0], textBounds[1]);
        batch.setProjectionMatrix(cam.combined);
        titleImageFBO.begin();
            batch.begin();

                FontManager.titleScreenTitleFont.draw(batch, "crystals", 0, textBounds[1]);

            batch.end();
        titleImageFBO.end();
    }

    public void renderMenuImageFBO() {
        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;

        for (int i = 0; i < menuImageFBOArray.length; i++) {
            String curText = TitleScreenManager.titleScreenMenuOptions[i];
            int[] menuTextBounds = FontManager.getRealBounds(FontManager.titleScreenMenuFont, curText);

            //zoom in on the frame buffer to keep perspective
            cam.setToOrtho(false, menuTextBounds[0], menuTextBounds[1]);
            batch.setProjectionMatrix(cam.combined);

            //generate the plain text
            if (menuImageFBOArray[i] != null)
                menuImageFBOArray[i].dispose();
            FrameBuffer menuTextFBO = new FrameBuffer(Pixmap.Format.RGBA8888, menuTextBounds[0], menuTextBounds[1], false);
            menuImageFBOArray[i] = menuTextFBO;

            //zoom in on the frame buffer to keep perspective
            cam.setToOrtho(false, menuTextBounds[0], menuTextBounds[1]);
            batch.setProjectionMatrix(cam.combined);
            menuTextFBO.begin();
                batch.begin();

                    FontManager.titleScreenMenuFont.draw(batch, curText, 0, menuTextBounds[1]);

                batch.end();
            menuTextFBO.end();
        }
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = Crystals.batch;
        OrthographicCamera cam = Crystals.cam;
        int[] resolution = SettingsManager.getResolution();

        Texture curBgTexture = bgImageTextureArray[0];

        TextureRegion curTitleTextureRegion = new TextureRegion(titleImageFBO.getColorBufferTexture());
        curTitleTextureRegion.flip(false, true);
        BitmapFont.TextBounds titleTextBounds = FontManager.titleScreenTitleFont.getBounds("crystals");
        int titleTextX = (int) (resolution[0] - titleTextBounds.width - resolution[0] / 20);
        int titleTextY = (int) (resolution[1] / 2 + FontManager.titleScreenTitleFont.getDescent());

        int fontHeight = FontManager.getRealHeight(FontManager.titleScreenMenuFont);
        int menuTextX = resolution[0] / 40;
        int menuTextStartY = resolution[1] / 2 - fontHeight;
        int menuTextLineHeight = fontHeight;

        cam.setToOrtho(false, resolution[0], resolution[1]);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

            batch.draw(curBgTexture, 0, 0);

            batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ONE_MINUS_SRC_ALPHA);
            batch.draw(curTitleTextureRegion, titleTextX, titleTextY);
            batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);

            for (int i = 0; i < menuImageFBOArray.length; i++) {
                FrameBuffer curMenuFrameBuffer = menuImageFBOArray[i];
                TextureRegion curMenuTextureRegion = new TextureRegion(curMenuFrameBuffer.getColorBufferTexture());
                curMenuTextureRegion.flip(false, true);
                batch.draw(curMenuTextureRegion, menuTextX, menuTextStartY - menuTextLineHeight * i);
            }

        batch.end();
    }
}