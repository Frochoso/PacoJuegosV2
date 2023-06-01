package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SettingsManager {
    private static Texture pedra = new Texture(Gdx.files.internal(AssetsManager.PEDRA));
    private static Texture fiberto = new Texture(Gdx.files.internal(AssetsManager.FIBERTO2));
    private static Texture jovani = new Texture(Gdx.files.internal(AssetsManager.JOVANITAMANO));
    private static Texture paco = new Texture(Gdx.files.internal(AssetsManager.PACO));
    private static Texture coche = new Texture(Gdx.files.internal(AssetsManager.COCHETAMANO));
    private static Texture fruta = new Texture(Gdx.files.internal(AssetsManager.HUEVO));
    public static final short SCREEN_WIDTH = (short) Gdx.graphics.getWidth();
    public static final short SCREEN_HEIGHT = (short) Gdx.graphics.getHeight();
    public static final int APEDRA_WIDTH = 120 * pedra.getWidth() / 500;
    public static final int APEDRA_HEIGHT = 60 * pedra.getHeight() / 300;
    public static final int FIBERTO_WIDTH = 300 * fiberto.getWidth() / 400;
    public static final int FIBERTO_HEIGHT = 250 * fiberto.getHeight() / 300;
    public static final int PACO_WIDTH = 200 * paco.getWidth() / 500;
    public static final int PACO_HEIGHT = 200 * paco.getHeight() / 500;
    public static final int COCHE_WIDTH = 200 * coche.getWidth() / 100;
    public static final int COCHE_HEIGHT = 200 * coche.getHeight() / 100;
    public static final int JOVANI_WIDTH =157 * jovani.getWidth() / 250;
    public static final int JOVANI_HEIGHT = 211 * jovani.getHeight() / 300;
    public static final int FRUITS_WIDTH =  72 * fruta.getWidth() / 200;
    public static final int FRUITS_HEIGHT = 78 * fruta.getHeight() / 200;


}
