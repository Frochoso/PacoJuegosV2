package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class Fiberto extends Actor{

    private Animation<TextureRegion> skin;
    private Texture texturaMuerto;
    private Stage stage;
    private Rectangle hitbox;
    private boolean muerto;
    private float velY;
    private final float acelY;
    private float velocidadSalto = 9.0f;
    private boolean saltando = false;


    public Fiberto(Stage stage) {
        super();
        this.stage = stage;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.FIBERTO_ATLAS_FILE));
        this.setX(SettingsManager.SCREEN_WIDTH-this.getWidth());
        this.velY=0.0f;
        this.acelY = 0.225f;
        this.setY(100);

        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.FIBERTO), Animation.PlayMode.LOOP);
        texturaMuerto = new Texture(Gdx.files.internal(AssetsManager.FIBERTO_MUERTO));
        hitbox = null;
        muerto = false;
        setBounds(getX(), getY(), SettingsManager.FIBERTO_WIDTH, SettingsManager.FIBERTO_HEIGHT);
    }

    public void saltar() {
        saltando = true;
        velY = velocidadSalto;
    }

    public void calculateBodyRectangle() {
        hitbox = new Rectangle(this.getX(), this.getY(), SettingsManager.FIBERTO_WIDTH,SettingsManager.FIBERTO_HEIGHT);
    }

    public boolean calculateCollisions(APedra pedra) {
        boolean result = false;
        Rectangle enBody = pedra.getHitBox();
        if (enBody != null && hitbox != null) {
            result = hitbox.overlaps(enBody);
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
        batch.draw(currentFrame, this.getX(), this.getY(),SettingsManager.FIBERTO_WIDTH,SettingsManager.FIBERTO_HEIGHT);
    }

    @Override
    public void act(float delta) {
        calculateBodyRectangle();
        super.act(delta);
        if (muerto) {
            setY(getY() - 5);
            if (getY() < 0) {
                this.dispose();
            }
        }
        this.setX(this.getX()-6);

        if(Gdx.input.justTouched() && !saltando) {
            saltar();
        }

        if (saltando) {
            velY -= acelY;
            this.setY(this.getY() + velY);
            if (this.getY() > 1000) {
                velY = -velocidadSalto;
            }
            if (this.getY() < 100) {
                this.setY(100f);
                saltando = false;
            }
        }


    }
    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }
}
