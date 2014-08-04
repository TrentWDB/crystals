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
    public static BitmapFont abilityBarNumberFont;

    private static final FileHandle titleScreenTitleFileHandle
            = Gdx.files.internal("res/fonts/NeutraDisplay-BoldAlt.ttf");
    private static final FileHandle titleScreenMenuFileHandle
            = Gdx.files.internal("res/fonts/NeutraDisplay-MediumAlt.ttf");
    private static final FileHandle abilityBarNumberFileHandle
            = Gdx.files.internal("res/fonts/NeutraDisplay-BoldAlt.ttf");

    public static void initialize() {
        generateFonts();
    }

    public static void generateFonts() {
        int[] resolution = SettingsManager.getResolution();
        double multRatio = resolution[1] / 1080.0 * Gdx.graphics.getDensity();

        FreeTypeFontGenerator titleScreenTitleFontGenerator
                = new FreeTypeFontGenerator(titleScreenTitleFileHandle);
        titleScreenTitleFont = titleScreenTitleFontGenerator.generateFont((int) (670 * multRatio));
        titleScreenTitleFontGenerator.dispose();
        titleScreenTitleFont.setColor(FinalManager.COLOR_TITLE_SCREEN_TITLE_FONT);

        FreeTypeFontGenerator titleScreenMenuFontGenerator
                = new FreeTypeFontGenerator(titleScreenMenuFileHandle);
        titleScreenMenuFont = titleScreenMenuFontGenerator.generateFont((int) (140 * multRatio));
        titleScreenMenuFontGenerator.dispose();
        titleScreenMenuFont.setColor(FinalManager.COLOR_TITLE_SCREEN_MENU_FONT);

        FreeTypeFontGenerator abilityBarNumberFontGenerator
                = new FreeTypeFontGenerator(abilityBarNumberFileHandle);
        abilityBarNumberFont = abilityBarNumberFontGenerator.generateFont((int) (40 * multRatio));
        abilityBarNumberFontGenerator.dispose();
        abilityBarNumberFont.setColor(FinalManager.COLOR_BLACK);
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
