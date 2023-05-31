package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class Paco extends Actor {

    private Animation<TextureRegion> skin;
    private Stage stage;
    private float speed;
    private float acelX;
    private float speedTarget;
    private Rectangle hitbox;
    private boolean touchingScreen = false;
    private boolean muerto;
    private boolean meta;


    public Paco(Stage stage) {
        super();
        this.stage = stage;
        speed = 200f;
        acelX = 0.225f;
        hitbox = null;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.PACO_RUNNING_ATLAS_FILE));
        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PACO_RUNNING), Animation.PlayMode.LOOP);
        setBounds(100, 200, SettingsManager.PACO_WIDTH, SettingsManager.PACO_HEIGHT);

    }

    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.justTouched()&&!meta) {
            touchingScreen = true;
            speedTarget = 1000f;
            speedTarget += acelX;
        }
        if (touchingScreen) {
            float aceleracion = 700f;
            speed = MathUtils.clamp(speed + aceleracion * delta, 0, speedTarget);
        } else {
            float deceleracion = 20f;
            speed = MathUtils.clamp(speed - deceleracion * delta, 0, speedTarget);
        }
        this.setX(this.getX() + speed * delta);


        touchingScreen = false;
    }

    public boolean calculateCollisions(Rectangle meta) {
        boolean result = false;
        Rectangle enBody = meta;
        if (enBody != null && meta != null) {
            result = meta.overlaps(enBody);
            if (result) {
                this.muerto = true;
            }
        }
        return result;
    }

    public boolean isMuerto() {
        return muerto;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.PACO_WIDTH, SettingsManager.PACO_HEIGHT);
    }

    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }
}
