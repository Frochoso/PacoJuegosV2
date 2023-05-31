package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.pacojuegos.MyGdxGame;

public class GameManager {
    private float gameTime;
    private static GameManager singleton;

    private World world;

    public static GameManager getSingleton() {
        if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }

    public float getGameTime() {
        return MyGdxGame.getGameTime();
    }

    private GameManager() {
        // Set up the Box2D world with gravity and collision detection.
        Vector2 gravity = new Vector2(0, -9.81f); // set the gravity to simulate.
        boolean doSleep = true; // set whether to allow inactive bodies to sleep.
        world = new World(gravity, doSleep);
    }

    public World getWorld() {
        return world;
    }
}
