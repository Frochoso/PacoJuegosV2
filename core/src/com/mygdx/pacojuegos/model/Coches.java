package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

import java.util.Timer;
import java.util.TimerTask;

public class Coches extends Actor {

    private Game game;
    private Stage stage;
    private Animation<TextureRegion> skin;
    private String[] animaciones = {AssetsManager.COCHE1_ATLAS, AssetsManager.COCHE2_ATLAS, AssetsManager.COCHE3_ATLAS};
    private String[] fichero = {AssetsManager.COCHE1, AssetsManager.COCHE2, AssetsManager.COCHE3};
    private float speed;
    private float speedTarget;
    private float acelX;
    private final byte random;
    private boolean dificil;
    private boolean pantallaAparecida;
    private boolean touch = false;

    public Coches(Stage stage, Integer indice, float posX, float posY, byte random) {
        this.stage = stage;
        this.random = random;
        this.dificil = dificil;

        acelX = 0.225f;
        setBounds(posX, posY, SettingsManager.COCHE_WIDTH, SettingsManager.COCHE_WIDTH);
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(animaciones[indice]));
        skin = new Animation<TextureRegion>(0.02f, atlas.findRegions(fichero[indice]), Animation.PlayMode.LOOP);
    }

    public void defineCoche(final float delta, final boolean dificil) {

            touch = true;
            if (!dificil) {
                if (random == 1) {
                    speedTarget = 200f;
                } else if (random == 2) {
                    speedTarget = 220f;
                } else if (random == 3) {
                    speedTarget = 230f;
                }
            } else {
                if (random == 1) {
                    speedTarget = 230f;
                } else if (random == 2) {
                    speedTarget = 270f;
                } else if (random == 3) {
                    speedTarget = 300f;
                }
            }

            speedTarget += acelX;

            float aceleracion = 100f;
            speed = MathUtils.clamp(speed + aceleracion * delta, 0, speedTarget);
            setX(getX() + speed * delta);

    }

    public boolean isTouchable() {
        return touch;
    }

    public void reset() {
        speed = 0;
        speedTarget = 0;
        acelX = 0;
        this.setX(100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.COCHE_WIDTH, SettingsManager.COCHE_HEIGHT);
    }

    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }
}
