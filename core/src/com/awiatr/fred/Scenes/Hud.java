package com.awiatr.fred.Scenes;

import com.awiatr.fred.FredGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer time;
    public static Integer score;
    private Integer lives;
    private float timeCount;

    Label timeWordLabel;
    Label livesWordLabel;
    Label scoreWordLabel;
    static Label  scoreLabel;
    Label livesLabel;
    Label timeLabel;


    public Hud(SpriteBatch sb) {
        time = 300;
        score = 0;

        timeCount = 0;

        viewport = new FitViewport(FredGame.V_WIDTH, FredGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeWordLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreWordLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(String.format("%06d", time), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(scoreWordLabel).expandX().padTop(3);
        table.add(timeWordLabel).expandX().padTop(3);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(timeLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            time--;
            timeLabel.setText(String.format("%06d", time));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
}
