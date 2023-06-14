package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.pacojuegos.view.HistoriaFin;
import com.mygdx.pacojuegos.view.HistoriaInicio;
import com.mygdx.pacojuegos.view.Inicio;
import com.mygdx.pacojuegos.view.Pantalla1;
import com.mygdx.pacojuegos.view.Pantalla2;
import com.mygdx.pacojuegos.view.Pantalla3;
import com.mygdx.pacojuegos.view.TransicionMuerte;
import com.mygdx.pacojuegos.view.TransitionScreen;

import java.util.ArrayList;

public class ScreensManager extends ScreenAdapter {

    private static ScreensManager singleton;
    public static ArrayList<Screen> pantallas = new ArrayList<>();
    private Game aGame;
    static byte random;
    private TransitionScreen transicion;

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public void initialize(Game game) {
        this.aGame = game;
    }

    public void initializeScreens() {
        Screen p1Facil = new Pantalla1(aGame, false);
        Screen p2Facil = new Pantalla2(aGame, false);
        Screen p3Facil = new Pantalla3(aGame, false);
        Screen p1Dificil = new Pantalla1(aGame, true);
        Screen p2Dificil = new Pantalla2(aGame, true);
        Screen p3Dificil = new Pantalla3(aGame, true);

        pantallas.add(p1Facil);
        pantallas.add(p2Facil);
        pantallas.add(p3Facil);
        pantallas.add(p1Dificil);
        pantallas.add(p2Dificil);
        pantallas.add(p3Dificil);
    }

    public void vaciaPantallas(){
        pantallas.clear();
    }

    public enum SCREENS {
        INICIO, HISTORIA, FIN;
    }

    public Screen getScreen(Game game, SCREENS screenToGet) {
        Screen newScreen = null;
        switch (screenToGet) {
            case INICIO:
                newScreen = new Inicio(game);
                break;
            case HISTORIA:
                    newScreen=new HistoriaInicio(game);
                    break;
            case FIN:
                newScreen=new HistoriaFin(game);
                break;
        }
        return newScreen;
    }

    public Screen transiciones(Game game, String previa) {
        Screen pantallaSiguiente = null;
        random = (byte) (Math.random() * pantallas.size());
        String fondo = AssetsManager.FONDO_INICIO;
        if (!pantallas.isEmpty()) {
            pantallaSiguiente = pantallas.get(random);
            if (pantallaSiguiente instanceof PantallaBase) {
                fondo = ((PantallaBase) pantallaSiguiente).getFondo();
            }
        } else {
            fondo = AssetsManager.FONDO_NEGRO;
        }

        return new TransitionScreen(game, previa, fondo);
    }

    public Screen transicionesMuerte(Game game, String previa) {
        String fondo = AssetsManager.FONDO_INICIO;

        return new TransicionMuerte(game, previa, fondo);
    }

    public Screen getPantallas(Game game) {

        Screen newScreen = null;
        if (pantallas.isEmpty()) {
            newScreen = new HistoriaFin(game);

        } else {
            newScreen = pantallas.get(random);
            pantallas.remove(random);
        }

        return newScreen;
    }
}
