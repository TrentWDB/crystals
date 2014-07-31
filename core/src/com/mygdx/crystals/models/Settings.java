package com.mygdx.crystals.models;

import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Trent on 7/27/2014.
 */
public class Settings {
    private int[] resolution;

    public Settings() {
    }

    public Settings(int[] resolution) {
        this.resolution = resolution;
    }

    public int[] getResolution() {
        return resolution;
    }

    public void setResolution(int[] resolution) {
        this.resolution = resolution;
    }
}
