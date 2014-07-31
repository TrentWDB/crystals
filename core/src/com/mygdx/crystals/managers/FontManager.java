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

    public static void generateFonts() {
        FreeTypeFontGenerator titleScreenTitleFontGenerator
                = new FreeTypeFontGenerator(titleScreenTitleFileHandle);
        titleScreenTitleFont = titleScreenTitleFontGenerator.generateFont(400);
        titleScreenTitleFont.setColor(222 / 255f, 0 / 255f, 255 / 255f, 150 / 255f);
        titleScreenTitleFontGenerator.dispose();

        FreeTypeFontGenerator titleScreenMenuFontGenerator
                = new FreeTypeFontGenerator(titleScreenMenuFileHandle);
        titleScreenMenuFont = titleScreenMenuFontGenerator.generateFont(400);
        titleScreenMenuFont.setColor(222 / 255f, 0 / 255f, 255 / 255f, 150 / 255f);
        titleScreenMenuFontGenerator.dispose();
    }

    public static int[] getRealBounds(BitmapFont font, String string) {
        BitmapFont.TextBounds textBounds = font.getMultiLineBounds(string);
        int width = (int) textBounds.width;
        int height = (int) (textBounds.height - font.getDescent());

        int[] bounds = {width, height};
        return bounds;
    }
}
