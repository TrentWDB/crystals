package com.mygdx.crystals.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.crystals.Crystals;
import com.mygdx.crystals.GpuShadows;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new Crystals(), config);
        new LwjglApplication(new Crystals(), "DONT USE THIS LAUNCHER", (int) (1920 * 0.8), (int) (1080 * 0.8));
	}
}
