package com.awiatr.fred.Screens;

import com.awiatr.fred.Fred;
import com.awiatr.fred.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;

public class PlayScreen implements Screen {
    private Fred game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;


    public PlayScreen(Fred game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Fred.V_WIDTH,Fred.V_HEIGHT,gamecam);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }
    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
