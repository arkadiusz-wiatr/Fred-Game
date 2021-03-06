package com.awiatr.fred;

import com.awiatr.fred.Screens.PlayScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FredGame extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 200;
    public static final float PPM = 100;
    public static final short GROUND_BIT = 1;
    public static final short FRED_BIT = 2;
    public static final short PLATFORM_BIT = 4;
    public static final short POINTS_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;

    public SpriteBatch batch;

    public static AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/fred.ogg", Music.class);
        manager.finishLoading();

        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
