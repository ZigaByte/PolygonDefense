package com.zigabyte.polygondefense.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.zigabyte.polygondefense.Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
        	float scale = 0.4f;
            return new GwtApplicationConfiguration((int)(1600 * scale), (int)(900 * scale));
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new Game();
        }
}