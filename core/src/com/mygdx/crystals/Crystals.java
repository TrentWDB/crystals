package com.mygdx.crystals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.crystals.managers.FontManager;
import com.mygdx.crystals.managers.RenderManager;
import com.mygdx.crystals.managers.SettingsManager;
import com.mygdx.crystals.renderers.mainmenu.TitleScreenRenderer;

public class Crystals extends ApplicationAdapter implements ApplicationListener {
	public static SpriteBatch batch;
    public static final OrthographicCamera cam = new OrthographicCamera();
	
	@Override
	public void create() {
        batch = new SpriteBatch();

        initialize();
    }

    public void initialize() {
        SettingsManager.loadSettings();
        FontManager.initialize();
        RenderManager.initialize();
    }

	@Override
	public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RenderManager.getRenderObject().render();
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
