package com.awiatr.fred.Sprites;

import com.awiatr.fred.FredGame;
import com.awiatr.fred.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Points extends InteractiveTileObject {

    public Points(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(FredGame.POINTS_BIT);


    }

    @Override
    public void onHit() {
        Gdx.app.log("Points", "Collison");
        setCategoryFilter(FredGame.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(100);
    }
}
