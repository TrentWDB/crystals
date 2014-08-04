package com.mygdx.crystals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.crystals.interfaces.Renderer;
import com.mygdx.crystals.managers.AbilityBarManager;
import com.mygdx.crystals.managers.FontManager;
import com.mygdx.crystals.managers.RenderManager;
import com.mygdx.crystals.managers.SettingsManager;

import java.util.List;

public class Crystals extends ApplicationAdapter implements ApplicationListener {
	public static SpriteBatch batch;//TODO static might be a bad idea idk
    public static final OrthographicCamera cam = new OrthographicCamera();//TODO same here idk
	
	@Override
	public void create() {
        batch = new SpriteBatch();

        initialize();
    }

    public void initialize() {
        SettingsManager.loadSettings();
        FontManager.initialize();
        AbilityBarManager.initialize();
        RenderManager.initialize();
    }

	@Override
	public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        List<Renderer> rendererList = RenderManager.getRenderObjects();
        for (int i = 0; i < rendererList.size(); i++) {
            rendererList.get(i).render();
        }
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
