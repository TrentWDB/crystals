package com.mygdx.crystals.managers;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.JsonSyntaxException;
import com.mygdx.crystals.models.Settings;

import java.awt.*;
import java.lang.reflect.Field;
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
    }

    private static int[] getMonitorResolution() {
        return settings.getMonitorResolution();
    }

    private static void setMonitorResolution(int[] monitorResolution) {
        settings.setMonitorResolution(monitorResolution);
    }

    public static void loadSettings() {
        //this weirdness is needed because its null if settings exists but is empty
        if (SETTINGS_FILE_HANDLE.exists()) {
            Type settingsType = Settings.class;
            try {
                settings = FinalManager.gson.fromJson(SETTINGS_FILE_HANDLE.reader(), settingsType);
            } catch (JsonSyntaxException e) {
                //if the settings file was corrupted by an idiot
                System.err.println("Warning! The settings file was unreadable due to a syntax exception.");
                settings = null;
            }
        } else {
            settings = null;
        }

        if (settings == null) {
            setDefaults();
            writeSettings();
        } else {
            checkFields();
        }
    }

    private static void checkFields() {
        if (settings.getResolution() == null || settings.getMonitorResolution() == null) {
            setDefaultMonitorResolution();
            setDefaultResolution();
            writeSettings();
        }
    }

    private static boolean monitorResolutionMatch() {
        Dimension monitorResolutionDimension = FinalManager.toolkit.getScreenSize();
        int[] oldMonitorResolution = getMonitorResolution();

        return monitorResolutionDimension.getWidth() == oldMonitorResolution[0]
                && monitorResolutionDimension.getHeight() == oldMonitorResolution[1];
    }

    public static void writeSettings() {
        String jsonString = FinalManager.gson.toJson(settings);
        SETTINGS_FILE_HANDLE.writeString(jsonString, false);
    }

    public static void setDefaults() {
        settings = new Settings();
        setDefaultMonitorResolution();
        setDefaultResolution();
    }

    private static void setDefaultMonitorResolution() {
        Dimension monitorResolutionDimension = FinalManager.toolkit.getScreenSize();

        int[] monitorResolution
                = {(int) monitorResolutionDimension.getWidth(), (int) monitorResolutionDimension.getHeight()};
        setMonitorResolution(monitorResolution);
    }

    private static void setDefaultResolution() {
        Dimension resolutionDimension = FinalManager.toolkit.getScreenSize();
        //to ensure that its not wider than 16:9
        double aspectRatio = resolutionDimension.getWidth() / resolutionDimension.getHeight();
        int resolutionWidth = (int) resolutionDimension.getWidth();
        int resolutionHeight = (int) resolutionDimension.getHeight();
        if (aspectRatio > MAX_ASPECT_RATIO)
            resolutionWidth = (int) (resolutionHeight * MAX_ASPECT_RATIO);

        int[] resolution = {resolutionWidth, resolutionHeight};
        setResolution(resolution);
    }
}
