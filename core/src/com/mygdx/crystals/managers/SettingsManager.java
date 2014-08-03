package com.mygdx.crystals.managers;

import com.badlogic.gdx.files.FileHandle;
import com.mygdx.crystals.models.Settings;

import java.awt.*;
import java.lang.reflect.Type;

/**
 * Created by Trent on 7/27/2014.
 */
public class SettingsManager {
    private static Settings settings;

    private static final FileHandle SETTINGS_FILE_HANDLE = new FileHandle("res/config/settings.json");
    private static final double MAX_ASPECT_RATIO = 1920.0 / 1080;

    public static int[] getResolution() {
        return settings.getResolution();
    }

    public static void setResolution(int[] resolution) {
        settings.setResolution(resolution);

        FontManager.generateFonts();
    }

    public static void loadSettings() {
        if (SETTINGS_FILE_HANDLE.exists()) {
            Type settingsType = Settings.class;
            settings = FinalManager.gson.fromJson(SETTINGS_FILE_HANDLE.reader(), settingsType);
        } else {
            setDefaults();
            writeSettings();
        }
    }

    public static void writeSettings() {
        String jsonString = FinalManager.gson.toJson(settings);
        SETTINGS_FILE_HANDLE.writeString(jsonString, false);
    }

    public static void setDefaults() {
        settings = new Settings();
        setDefaultResolution();
    }

    private static void setDefaultResolution() {
        Dimension resolutionDimension = FinalManager.toolkit.getScreenSize();
        double aspectRatio = resolutionDimension.getWidth() / resolutionDimension.getHeight();
        int resolutionWidth = (int) resolutionDimension.getWidth();
        int resolutionHeight = (int) resolutionDimension.getHeight();
        if (aspectRatio > MAX_ASPECT_RATIO)
            resolutionWidth = (int) (resolutionHeight * MAX_ASPECT_RATIO);

        int[] resolution = {resolutionWidth, resolutionHeight};
        setResolution(resolution);
    }
}
