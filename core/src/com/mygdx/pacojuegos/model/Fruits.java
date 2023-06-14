package com.mygdx.pacojuegos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.view.Pantalla3;

public class Fruits {

    protected float posX;
    protected float posY;
    protected float velX;
    protected float velY;
    protected float anchoDiv4;
    protected float altoDiv4;
    protected Texture img;
    protected final String[] type = AssetsManager.IMGS_FRUITS;
    private String nombreTextura;

    public Fruits() {
        this.posX = (float) (Math.random() * (Gdx.graphics.getWidth() - SettingsManager.FRUITS_WIDTH)) + SettingsManager.FRUITS_WIDTH;
        this.posY = SettingsManager.SCREEN_HEIGHT - SettingsManager.JOVANI_HEIGHT;
        this.velX = 0;
        this.velY = -4;
        byte numero = (byte) (Math.random() * 5);
        switch (numero) {
            case 0:
                this.nombreTextura = "huevo";
                break;
            case 1:
                this.nombreTextura = "mango";
                break;
            case 2:
                this.nombreTextura = "manzana";
                break;
            case 3:
                this.nombreTextura = "naranja";
                break;
            case 4:
                this.nombreTextura = "lagarto";
                break;
        }
        this.img = new Texture(type[numero]);
        this.anchoDiv4 = SettingsManager.FRUITS_WIDTH / 4.0f;
        this.altoDiv4 = SettingsManager.FRUITS_HEIGHT / 4.0f;
    }

    public void setVelY(float velY){
        this.velY=velY;
    }

    public void moverse() {
        posX += velX;
        posY += velY;
    }

    public void pintarse(SpriteBatch batch, float width, float height) {
        batch.begin();
        batch.draw(img, posX, posY, width, height);
        batch.end();
    }

    public String getNombreTextura() {
        return this.nombreTextura;
    }

    public void dispose() {
        if (img != null) {
            img.dispose();
        }
    }

    public void falling() {
        if (Pantalla3.reserveFruits.isEmpty()) {
            Pantalla3.fallingFruits.add(new Fruits());
        } else {
            Fruits aux = Pantalla3.reserveFruits.get(0);
            aux.setPosX();
            aux.setPosY();
            Pantalla3.fallingFruits.add(aux);
            Pantalla3.reserveFruits.remove(0);
        }
        if (Pantalla3.fallingFruits.get(0).getPosY() <= 0) {
            Pantalla3.reserveFruits.add(Pantalla3.fallingFruits.get(0));
            Pantalla3.fallingFruits.remove(0);
        }
    }

    public void fMove() {
        for (Fruits ff : Pantalla3.fallingFruits) {
            ff.moverse();
        }
    }

    public void fDraw(SpriteBatch batch) {
        for (Fruits ff : Pantalla3.fallingFruits) {
            ff.pintarse(batch, SettingsManager.FRUITS_WIDTH, SettingsManager.FRUITS_HEIGHT);
        }
    }

    public void fDispose() {
        for (Fruits ff : Pantalla3.fallingFruits) {
            ff.dispose();
        }
        for (Fruits rf : Pantalla3.reserveFruits) {
            rf.dispose();
        }
    }

    public void setPosX() {
        this.posX = (float) (Math.random() * (Gdx.graphics.getWidth() - SettingsManager.FRUITS_WIDTH)) + SettingsManager.FRUITS_WIDTH;
    }

    public void setPosY() {
        this.posY = SettingsManager.SCREEN_HEIGHT - SettingsManager.JOVANI_HEIGHT;
    }

    public boolean colisiona(Jovani otro) {

        boolean resultado=false, colisionX, colisionY;

        if (posY >= otro.getY() + otro.getHeight()) {
            colisionX = (Math.abs(posX - otro.getX()) <= (anchoDiv4 + SettingsManager.JOVANI_WIDTH / 1.5));
            colisionY = (Math.abs(posY - otro.getY()) <= (altoDiv4 + SettingsManager.JOVANI_HEIGHT / 1.2));
            resultado = colisionX && colisionY;
        }
        return resultado;
    }

    public float getPosY() {
        return posY;
    }

}