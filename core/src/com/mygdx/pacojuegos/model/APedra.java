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
    protected float anchoDiv4;
    protected float anchoDiv2;
    protected float altoDiv4;
    protected float posX;
    protected float posY;

    public APedra(Stage stage, float nPosX, float nPosY) {
        super();
        skin = new TextureRegion(new Texture(AssetsManager.PEDRA));
        this.posX = nPosX;
        this.posY = nPosY;
        this.anchoDiv4 = SettingsManager.APEDRA_WIDTH / 4.0f;
        this.anchoDiv2 = SettingsManager.APEDRA_WIDTH / 2.0f;
        this.altoDiv4 = SettingsManager.APEDRA_HEIGHT / 4.0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(skin, posX, posY, SettingsManager.APEDRA_WIDTH, SettingsManager.APEDRA_HEIGHT);
    }

    public boolean colisiona(Fiberto otro) {
        boolean resultado = false;
        boolean colisionX = (posX + anchoDiv2 <= otro.getX() + otro.getWidth()) && (posX + anchoDiv2 >= otro.getX());
        boolean colisionY = (posY <= (otro.getY() + otro.getHeight())) && ((posY + altoDiv4) >= otro.getY());
        resultado = colisionX && colisionY;
        return resultado;
    }

    public void dispose() {
        if (this != null) {
            this.dispose();
        }
    }
}
