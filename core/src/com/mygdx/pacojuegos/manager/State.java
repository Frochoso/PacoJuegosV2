package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected Vector3 mouse;
    protected GameManager gm;

    protected State(GameManager gm){
        this.gm=gm;
        mouse=new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);


}
