package com.awiatr.fred.Screens;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Scenes.Hud;
import com.awiatr.fred.Sprites.Fred;
import com.awiatr.fred.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
    private FredGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Fred player;


    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;




    public PlayScreen(FredGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(FredGame.V_WIDTH / FredGame.PPM, FredGame.V_HEIGHT / FredGame.PPM, gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("FRED-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / FredGame.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2,gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10 ), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);


        player = new Fred(world);
    }

    @Override
    public void show() {
    }

    public void handleInput(float dt) {

    if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        player.b2body.applyLinearImpulse(new Vector2(0,3f), player.b2body.getWorldCenter(),true);
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
        player.b2body.applyLinearImpulse(new Vector2(0.05f,0), player.b2body.getWorldCenter(),true);
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x <= 2)
        player.b2body.applyLinearImpulse(new Vector2(-0.05f,0), player.b2body.getWorldCenter(),true);




    }

    public void update(float dt){

        handleInput(dt);

        world.step(1/60f,3,2);
        gamecam.position.x = player.b2body.getPosition().x;


        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {
        // separate our update logic from render
        update(delta);

        //clear the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        b2dr.render(world,gamecam.combined);



        // set our batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);


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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
