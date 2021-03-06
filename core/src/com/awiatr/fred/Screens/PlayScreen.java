package com.awiatr.fred.Screens;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Scenes.Hud;
import com.awiatr.fred.Sprites.Fred;
import com.awiatr.fred.Tools.B2WorldCreator;
import com.awiatr.fred.Tools.WorldContactListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    private int livesCounter = 0;
    private FredGame game;
    private TextureAtlas atlas;


    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;


    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private Fred player;

    private Music music;



    public PlayScreen(FredGame game) {
        atlas = new TextureAtlas("Fred_and_Enemies.pack");


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


        player = new Fred(world,this);

        world.setContactListener(new WorldContactListener());

        music = FredGame.manager.get("audio/fred.ogg", Music.class);
        music.setLooping(true);
        music.play();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {
    }

    public void handleInput(float dt) {

    if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        player.jump();
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
        player.b2body.applyLinearImpulse(new Vector2(0.05f,0), player.b2body.getWorldCenter(),true);
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x <= 2)
        player.b2body.applyLinearImpulse(new Vector2(-0.05f,0), player.b2body.getWorldCenter(),true);




    }

    public void update(float dt){

        handleInput(dt);

        world.step(1/60f,3,2);
        gamecam.position.x = player.b2body.getPosition().x;

        player.update(dt);
        hud.update(dt);


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

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();




        // set our batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(player.b2body.getPosition().y < -1.5) {
            gamecam.position.x = player.b2body.getPosition().x;
            game.setScreen(new GameOverScreen(game));
            dispose();
        } else if(player.b2body.getPosition().x > 20.5){
            gamecam.position.x = player.b2body.getPosition().x;
            game.setScreen(new GameWinScreen(game));
            Hud.addScore(500);
            dispose();
        }


    }

    public World getWorld(){
        return world;
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
