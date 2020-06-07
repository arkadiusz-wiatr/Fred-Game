package com.awiatr.fred.Sprites.TileObjects;

import com.awiatr.fred.Sprites.TileObjects.InteractiveTileObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Platform extends InteractiveTileObject {

    public Platform(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);


    }

    @Override
    public void onHit() {

    }
}
