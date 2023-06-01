package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {

    public static final String FONDO_N1P1_JUGAR = "fondos\\fondo1.png";
    public static final String FONDO_N1P1_GANADO = "fondos\\fondo1_ganada.png";
    public static final String MAPA_N1P12 = "fondos/fondo2.jpeg";
    public static final String FONDO_INICIO = "fondos\\fondoInicio.png";
    public static final String PEDRA = "sprites\\aPedra.png";
    public static final String SKIN_BOTON = "sprites/interfaz/uiskin.json";
    public static final String SKIN_ATLAS = "sprites/interfaz/uiskin.atlas";
    public static final String SKIN_TEXTO = "sprites/interfaz/commodore-64.fnt";
    public static final String MANOLO = "sprites\\manolo.png";
    public static final String PACO = "sprites\\paco.png";
    public static final String PACO_RUNNING = "pacoRunning";
    public static final String PACO_RUNNING_ATLAS_FILE = "sprites/pacoRunning/pacoRunning.atlas";
    public static final String FIBERTO_ATLAS_FILE = "fiberto\\fiberto.atlas";
    public static final String FIBERTO = "fiberto";
    public static final String FIBERTO2 = "fiberto/fiberto2.png";
    public static final String FIBERTO_MUERTO = "fiberto\\fiberto_dead.png";
    public static final String COCHE1_ATLAS = "coches/coche1/coche1.atlas";
    public static final String COCHETAMANO = "coches/cocheTamano.png";
    public static final String COCHE1 = "coche1";
    public static final String COCHE2_ATLAS = "coches/coche2/coche2.atlas";
    public static final String COCHE2 = "coche2";
    public static final String COCHE3_ATLAS = "coches/coche3/coche3.atlas";
    public static final String COCHE3 = "coche3";
    public static final String DIGITO_NEGATIVO = "sprites/digitos/digitoNeg.png";
    public static final String JOVANI_DCHA_ATLAS = "jovani/derecha/jovaniDcha.atlas";
    public static final String JOVANI_DCHA = "jovani_dcha";
    public static final String JOVANI_IZQDA_ATLAS = "jovani/izquierda/jovaniIzqda.atlas";
    public static final String JOVANITAMANO = "jovani/jovaniTamano.png";
    public static final String JOVANI_IZQDA = "jovani_izqda";
    public static final String RUTA_FRUTAS = "sprites/frutas/";
    public static final String HUEVO = "sprites/frutas/huevo.png";
    public static final String CHOCA_MANGO1 = "music/sounds/tomaMango.wav";
    public static final String CHOCA_MANGO2 = "music/sounds/everybodyWanMango.wav";
    public static final String CHOCA_MANZANA1 = "music/sounds/dimeloManzana.wav";
    public static final String CHOCA_MANZANA2 = "music/sounds/enSerioManzana.wav";
    public static final String CHOCA_HUEVO = "music/sounds/huevo.wav";
    public static final String CHOCA_NARANJA1 = "music/sounds/naranja.wav";
    public static final String CHOCA_NARANJA2 = "music/sounds/conPulpa.wav";
    public static final String CHOCA_NARANJA3 = "music/sounds/sinPulpa.wav";
    public static final String CHOCA_LAGARTO1 = "music/sounds/iguana.wav";
    public static final String CHOCA_LAGARTO2 = "music/sounds/lagarto.wav";
    public static final String JOVANI_FINAL = "music/sounds/motoraFin.wav";
    public static final String[] IMGS_FRUITS = {RUTA_FRUTAS + "huevo.png", RUTA_FRUTAS + "mango.png", RUTA_FRUTAS + "manzana.png", RUTA_FRUTAS + "naranja.png", RUTA_FRUTAS + "lagarto.png"};

    private static AssetManager assetManager;

    public AssetsManager() {
        assetManager = new AssetManager();
    }

    public void loadFondo(String imagen) {
        assetManager.load(imagen, Texture.class);
    }

    public static String getFondo(String imagen) {
        return assetManager.get(imagen, Texture.class).toString();
    }

    public void dispose() {
        assetManager.dispose();
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

}
