package com.awiatr.fred.Screens;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Scenes.Hud;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameWinScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GameWinScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(FredGame.V_WIDTH, FredGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((FredGame) game).batch );

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("You Won!", font);
        Label playAgainLabel = new Label("Click to Play Again", font);
        Label scoreLabel = new Label("Your score : ", font);
        Label score = new Label(String.format("%06d", Hud.score), font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);
        table.row();
        table.add(scoreLabel).expandX().padTop(5f);
        table.row();
        table.add(score).expandX().padTop(2f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((FredGame) game));
            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();

    }
}
