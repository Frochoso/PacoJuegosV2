package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class APedra extends Actor {

    private final TextureRegion skin;
    private Rectangle hitBox;


    public APedra(Stage stage) {
        super();
        skin = new TextureRegion(new Texture(AssetsManager.PEDRA));
        this.setPosition(800, 100);
        hitBox= new Rectangle(this.getX()+SettingsManager.APEDRA_WIDTH/4,this.getY(),SettingsManager.APEDRA_WIDTH/4,SettingsManager.APEDRA_HEIGHT);
    }


    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(skin, getX(), getY(), SettingsManager.APEDRA_WIDTH, SettingsManager.APEDRA_HEIGHT);
    }

    public void dispose() {
        if (this!=null) {
            this.dispose();
        }
    }
}
