package com.awiatr.fred.Sprites;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Fred extends Sprite {

    public enum State {FALLING,JUMPING,STANDING,RUNNING,DEAD};
    public State currentState;
    public State previousState;


    public World world;
    public Body b2body;
    private TextureRegion fredStand;
    private Animation<TextureRegion> fredRun;
    private Animation<TextureRegion> fredJump;

    private float stateTimer;
    private boolean runningRight;
    private boolean fredIsDead;

    protected Fixture fixture;



    public Fred(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("A-FRED-WALK-RIGHT"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(),i * 16,0,16,16));
        fredRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 6; i <8; i++)
            frames.add(new TextureRegion(getTexture(),i *16,0,16,16));
        fredJump = new Animation<TextureRegion>(0.1f,frames);

        fredStand = new TextureRegion(getTexture(),0,0,16,16);

        defineFred();
        setBounds(0,0,16 / FredGame.PPM, 16 / FredGame.PPM );
        setRegion(fredStand);
    }


    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 );
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;

        switch(currentState){
            case JUMPING:
                region = (TextureRegion) fredJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) fredRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = fredStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public boolean isDead(){return fredIsDead;}

    public State getState(){
        if(fredIsDead)
            return State.DEAD;
        else if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }



    public void jump() {
        if (currentState != State.JUMPING) {
            b2body.applyLinearImpulse(new Vector2(0, 3f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }


            public void defineFred(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / FredGame.PPM ,32 / FredGame.PPM );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / FredGame.PPM );
        fdef.filter.categoryBits = FredGame.FRED_BIT;
        fdef.filter.maskBits = FredGame.GROUND_BIT | FredGame.POINTS_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape fredShape = new EdgeShape();
        fredShape.set(new Vector2(-6/ FredGame.PPM, 0 / FredGame.PPM),new Vector2(8/ FredGame.PPM, 0 / FredGame.PPM));
        fdef.shape = fredShape;
        fdef.isSensor = true;


        b2body.createFixture(fdef).setUserData("fred");







    }

}
