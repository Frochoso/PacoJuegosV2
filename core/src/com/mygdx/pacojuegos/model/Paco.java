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
import com.mygdx.pacojuegos.repository.DataUploader;

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
    private float aceleracion;
    private float deceleracion;
    private int puntos;
    private int contadorToques;
    private DataUploader puntuaciones;


    public Paco(Stage stage) {
        super();
        this.stage = stage;
        speed = 200f;
        this.puntos = 0;
        acelX = 0.225f;
        hitbox = null;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.PACO_RUNNING_ATLAS_FILE));
        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PACO_RUNNING), Animation.PlayMode.LOOP);
        setBounds(100, 200, SettingsManager.PACO_WIDTH, SettingsManager.PACO_HEIGHT);
    }

    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.justTouched() && !meta) {
            contadorToques++;
            puntos = puntos + 5;
            touchingScreen = true;
            speedTarget = 1000f;
            speedTarget += acelX;
        }
        if (touchingScreen) {
            aceleracion = 700f;
            speed = MathUtils.clamp(speed + aceleracion * delta, 0, speedTarget);
        } else {
            deceleracion = 20f;
            speed = MathUtils.clamp(speed - deceleracion * delta, 0, speedTarget);
        }
        this.setX(this.getX() + speed * delta);

        touchingScreen = false;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getContadorToques() {
        return contadorToques;
    }

    public void pasarPuntos(int puntos, int toques) {

        double mediaPuntos = puntos / toques;

        puntuaciones.sumaPuntuacionN2(mediaPuntos);
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

    public void reset() {
        puntos = 0;
        aceleracion = 0f;
        speed = 0;
        speedTarget = 0;
        deceleracion = 0f;
        this.setX(100);
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
