package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class Jovani extends Actor {

    protected float posX;
    private Animation<TextureRegion> skin;
    private Animation<TextureRegion> animacionIzqda;
    private Animation<TextureRegion> animacionDcha;
    private TextureAtlas atlasDcha;
    private TextureAtlas atlasIzqda;
    private float velX;
    protected float anchoDiv2;
    protected float altoDiv2;
    private Rectangle hitbox;
    private boolean isMoving = false;
    private Stage stage;
    private boolean direccion;

    public Jovani(Stage stage) {

        this.stage = stage;
        this.anchoDiv2 = SettingsManager.JOVANI_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.JOVANI_HEIGHT / 2.0f;
        this.posX = (Gdx.graphics.getWidth() / 2f) - anchoDiv2;
        hitbox = null;
        this.velX = 0f;
        this.setPosition(SettingsManager.SCREEN_WIDTH / 2f, 170);
        atlasDcha = new TextureAtlas(Gdx.files.internal(AssetsManager.JOVANI_DCHA_ATLAS));
        atlasIzqda = new TextureAtlas(Gdx.files.internal(AssetsManager.JOVANI_IZQDA_ATLAS));
        animacionDcha = new Animation<TextureRegion>(0.25f, atlasDcha.findRegions(AssetsManager.JOVANI_DCHA), Animation.PlayMode.LOOP);
        animacionIzqda = new Animation<TextureRegion>(0.25f, atlasIzqda.findRegions(AssetsManager.JOVANI_IZQDA), Animation.PlayMode.LOOP);
        skin = animacionDcha;
        this.anchoDiv2 = SettingsManager.JOVANI_WIDTH / 2.0f;
        this.altoDiv2 = SettingsManager.JOVANI_HEIGHT / 2.0f;
    }

    public void calculateBodyRectangle() {
        hitbox = new Rectangle(this.getX(), this.getY(), SettingsManager.JOVANI_WIDTH, SettingsManager.JOVANI_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.JOVANI_WIDTH, SettingsManager.JOVANI_HEIGHT);
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public void act(float delta) {
        calculateBodyRectangle();

        if (!isMoving) {
            isMoving = true;
        }

        if (isMoving) {
            float targetVelX = 0;

            if (direccion) {
                setAnimacion(animacionDcha);
                targetVelX = 300f;
            } else if (!direccion) {
                setAnimacion(animacionIzqda);
                targetVelX = -300f;
            }

            float maxVelX = 300f;
            float aceleracion = 2f;
            velX = MathUtils.clamp(velX + (targetVelX - velX) * aceleracion * delta, -maxVelX, maxVelX);

            this.setX(this.getX() + velX * delta);

            if (this.getX() <= 0) {
                this.setX(0);
            } else if (this.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.JOVANI_WIDTH) {
                this.setX(SettingsManager.SCREEN_WIDTH - SettingsManager.JOVANI_WIDTH);
            }
        }
    }

    public void reset() {
        setX(SettingsManager.SCREEN_WIDTH / 2);
        setVelX(0);
    }

    public float getVelX() {
        return velX;
    }

    public void setAnimacion(Animation<TextureRegion> current) {
        this.skin = current;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }
}
