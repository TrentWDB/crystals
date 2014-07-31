package com.mygdx.crystals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import com.mygdx.crystals.managers.FontManager;
import com.mygdx.crystals.managers.SettingsManager;
import com.mygdx.crystals.models.Settings;
import com.mygdx.crystals.renderers.TitleScreen;
import com.mygdx.crystals.utilities.FixedPoint;

public class Crystals extends ApplicationAdapter implements ApplicationListener {
	public static SpriteBatch batch;
    public static final OrthographicCamera cam = new OrthographicCamera();

    TitleScreen titleScreen;
	
	@Override
	public void create() {
        batch = new SpriteBatch();//TODO might also be a bad idea?
        int[] resolution = SettingsManager.getResolution();
        cam.setToOrtho(false, resolution[0], resolution[1]);

        FontManager.generateFonts();

        long time1 = System.currentTimeMillis();

        titleScreen.generateFrames();

        System.out.println("generating title screen took " + (System.currentTimeMillis() - time1));
    }

	@Override
	public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        titleScreen.render();
	}

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, width, height);
        batch.setProjectionMatrix(cam.combined);
    }

    public void dispose() {
        SettingsManager.writeSettings();
    }
}
