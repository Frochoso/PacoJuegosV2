package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {

    public static final String FONDO_N1P1_JUGAR = "fondos\\fondo1.png";
    public static final String FONDO3 = "fondos\\fondo3.png";
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
    public static final String FIBERTO_MUERTO = "fiberto\\fiberto_dead.png";
    public static final String COCHE1_ATLAS = "coches/coche1/coche1.atlas";
    public static final String COCHE1 = "coche1";
    public static final String COCHE2_ATLAS = "coches/coche2/coche2.atlas";
    public static final String COCHE2 = "coche2";
    public static final String COCHE3_ATLAS = "coches/coche3/coche3.atlas";
    public static final String COCHE3 = "coche3";
    public static final String JOVANI_DCHA_ATLAS = "jovani/derecha/jovaniDcha.atlas";
    public static final String JOVANI_DCHA = "jovani_dcha";
    public static final String JOVANI_IZQDA_ATLAS = "jovani/izquierda/jovaniIzqda.atlas";
    public static final String JOVANI_IZQDA = "jovani_izqda";
    public static final String RUTA_FRUTAS = "sprites/frutas/";
    public static final String FLECHA_DCHA = "jovani/derecha/flechaDcha.png";
    public static final String FLECHA_IZQDA = "jovani/izquierda/flechaIzqda.png";
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
    public static final String CANCION_INICIO = "music/music/cancionInicio.wav";
    public static final String CANCION_FIBERTO = "music/music/fibertoMusic.wav";
    public static final String CANCION_PACO = "music/music/pacoMusic.wav";
    public static final String CANCION_JOVANI = "music/music/jovaniMusic.wav";
    public static final String TRANSICION_VIDEO = "transicion/transicionPantallas.mp4";
    public static final String INFO_SALTA_ATLAS = "sprites/info/infoSalta.atlas";
    public static final String INFO_TOCA_ATLAS = "sprites/info/infoToca.atlas";
    public static final String INFO_SALTA_FILE = "cartelSalta";
    public static final String INFO_TOCA_FILE = "cartelToca";
    public static final String FONDO_NEGRO = "fondos/fondoNegro.png";
    public static final String[] IMGS_FRUITS = {RUTA_FRUTAS + "huevo.png", RUTA_FRUTAS + "mango.png", RUTA_FRUTAS + "manzana.png", RUTA_FRUTAS + "naranja.png", RUTA_FRUTAS + "lagarto.png"};
    public static final String HISTORIA1 = "Jovani, Paco y Fiberto eran amigos inseparables. Siempre estaban juntos, ya fuera jugando videojuegos, explorando el vecindario o inventando aventuras divertidas. Pero un dia, mientras exploraban un viejo almacen abandonado, encontraron un misterioso movil con una pantalla brillante y tentadora.";
    public static final String HISTORIA2 = "Sin pensarlo dos veces, los amigos tocaron la pantalla y, de repente, fueron absorbidos dentro del movil. Descubrieron que estaban atrapados en un mundo digital controlado por un movil malvado llamado Michael Jordan. Este movil tenia un poder siniestro y habia capturado a Jovani, Paco y Fiberto para su propio entretenimiento retorcido.";
    public static final String HISTORIA3 = "El mundo digital era un laberinto lleno de desafios peligrosos y trampas mortales. Michael Jordan les lanzaba obstaculos cada vez más dificiles de superar, poniendo a prueba su valentia y habilidad en el juego. Parecia que no habia escapatoria, a menos que alguien pudiera completar todos los niveles y desafios que el malvado movil les presentaba.";
    public static final String HISTORIA4 = "Entonces, aparecio un jugador valiente y decidido. Era la unica esperanza de salvar a Jovani, Paco y Fiberto de las garras de Michael Jordan. Este jugador asumio el desafio de adentrarse en el mundo digital y enfrentar todos los obstaculos que se interponian en su camino.";

    public static final String FINAL1 = "Finalmente, despues de superar el ultimo desafio con movimientos rapidos y precisos, el jugador logro vencer al movil malvado y liberar a Jovani, Paco y Fiberto.";
    public static final String FINAL2 = "La alegria y el alivio llenaron el corazon de los amigos cuando finalmente regresaron al mundo real. Agradecieron al jugador por su valentia y determinacion para rescatarlos. Juntos, juraron nunca volver a caer en las garras de un movil malvado y apreciaron aún más la importancia de su amistad y de disfrutar del mundo real.";

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
