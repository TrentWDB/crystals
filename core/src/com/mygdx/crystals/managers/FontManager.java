package com.mygdx.crystals.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Trent on 7/24/2014.
 */
public class FontManager {
    public static BitmapFont titleScreenTitleFont;
    public static BitmapFont titleScreenMenuFont;

    private static final FileHandle titleScreenTitleFileHandle
            = Gdx.files.internal("res/fonts/NeutraDisplay-BoldAlt.ttf");
    private static final FileHandle titleScreenMenuFileHandle
            = Gdx.files.internal("res/fonts/NeutraDisplay-MediumAlt.ttf");


    public static void initialize() {
        generateFonts();
    }

    public static void generateFonts() {
        int[] resolution = SettingsManager.getResolution();
        double multRatio = resolution[1] / 1080.0 * Gdx.graphics.getDensity();

        FreeTypeFontGenerator titleScreenTitleFontGenerator
                = new FreeTypeFontGenerator(titleScreenTitleFileHandle);
        titleScreenTitleFont = titleScreenTitleFontGenerator.generateFont((int) (670 * multRatio));
        titleScreenTitleFont.setColor(222 / 255f, 0 / 255f, 255 / 255f, 150 / 255f);
        titleScreenTitleFontGenerator.dispose();

        FreeTypeFontGenerator titleScreenMenuFontGenerator
                = new FreeTypeFontGenerator(titleScreenMenuFileHandle);
        titleScreenMenuFont = titleScreenMenuFontGenerator.generateFont((int) (140 * multRatio));
        titleScreenMenuFont.setColor(255 / 255f, 255 / 255f, 255 / 255f, 200 / 255f);
        titleScreenMenuFontGenerator.dispose();
    }

    public static int[] getRealBounds(BitmapFont font, String string) {
        BitmapFont.TextBounds textBounds = font.getMultiLineBounds(string);
        int width = (int) textBounds.width;
        int height = (int) (textBounds.height - font.getDescent());

        int[] bounds = {width, height};
        return bounds;
    }

    public static int getRealHeight(BitmapFont font) {
        return (int) (font.getCapHeight() - font.getDescent());
    }
}
