package com.mygdx.pacojuegos.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class Fondo extends Actor {

    private Stage stage;
    private Texture fondoTexture;

    public Fondo(Stage stage,String imagen){
        super();
        this.stage=stage;
        this.setTexture(imagen);
    }

    public void setTexture(String imagen) {
        if (fondoTexture != null) {
            fondoTexture.dispose();
        }
        fondoTexture = new Texture(imagen);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(fondoTexture, this.getX(), this.getY(), SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void dispose() {
        if (fondoTexture != null) {
            fondoTexture.dispose();
            fondoTexture = null;
        }
    }

}
