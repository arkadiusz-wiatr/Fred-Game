package com.awiatr.fred.Sprites;

import com.awiatr.fred.FredGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public Fred(World world){
        this.world = world;
        defineFred();
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
