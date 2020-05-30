package com.awiatr.fred.Sprites;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Fred extends Sprite {

    public World world;
    public Body b2body;
    private TextureRegion fredStand;


    public Fred(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("A-FRED-WALK-RIGHT"));
        this.world = world;
        defineFred();
        fredStand = new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16 / FredGame.PPM, 16 / FredGame.PPM );
        setRegion(fredStand);
    }


    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 );


    }

    public void defineFred(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / FredGame.PPM ,32 / FredGame.PPM );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / FredGame.PPM );

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }

}
