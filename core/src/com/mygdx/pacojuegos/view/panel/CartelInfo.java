package com.mygdx.pacojuegos.view.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class CartelInfo extends Actor {
    private Animation<TextureRegion> skin;
    protected float posX;
    protected float posY;
    private Stage stage;

    public CartelInfo(Stage stage, float nPosX, float nPosY, String textura, String objeto) {
        this.stage = stage;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(textura));
        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(objeto), Animation.PlayMode.LOOP);
        this.posX = nPosX;
        this.posY = nPosY;
        this.setWidth(400f);
        this.setHeight(250f);
        this.setBounds(this.posX, this.posY, this.getWidth(), this.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }

}
