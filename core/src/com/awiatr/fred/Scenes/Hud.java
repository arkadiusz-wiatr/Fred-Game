package com.awiatr.fred.Scenes;

import com.awiatr.fred.Fred;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer time;
    private Integer score;
    private Integer lives;

    Label timeWordLabel;
    Label livesWordLabel;
    Label scoreWordLabel;
    Label scoreLabel;
    Label livesLabel;
    Label timeLabel;


    public Hud(SpriteBatch sb) {
        time = 0;
        score = 0;
        lives = 3;

        viewport = new FitViewport(Fred.V_WIDTH, Fred.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeWordLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesWordLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreWordLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label(String.format("%03d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(String.format("%06d", time), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(scoreWordLabel).expandX().padTop(3);
        table.add(timeWordLabel).expandX().padTop(3);
        table.add(livesWordLabel).expandX().padTop(3);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(timeLabel).expandX();
        table.add(livesLabel).expandX();

        stage.addActor(table);
    }

}
