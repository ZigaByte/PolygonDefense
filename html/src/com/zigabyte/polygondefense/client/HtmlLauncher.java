package com.zigabyte.polygondefense.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.zigabyte.polygondefense.Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1600, 900);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Game();
        }
}