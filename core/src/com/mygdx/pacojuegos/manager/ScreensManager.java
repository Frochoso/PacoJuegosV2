package com.mygdx.pacojuegos.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.pacojuegos.view.Inicio;
import com.mygdx.pacojuegos.view.nivel1.N1Pantalla1;
import com.mygdx.pacojuegos.view.nivel1.N1Pantalla2;
import com.mygdx.pacojuegos.view.nivel1.N1Pantalla3;

public class ScreensManager extends ScreenAdapter {

    private static ScreensManager singleton;
    private Game game;

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public enum SCREENS {
        INICIO, NIVELES, CONFIGURACION;
    }

    public enum PANTALLAS {
        N1
    }

    public Screen getScreen(Game game, SCREENS screenToGet) {
        Screen newScreen = null;
        switch (screenToGet) {
            case INICIO:
                newScreen = new Inicio(game);
                break;
        }
        return newScreen;
    }

    public Screen getPantallas(Game game, ScreensManager.PANTALLAS screenToGet, Integer pantalla) {
        Screen newScreen = null;
        switch (screenToGet) {
            case N1:
                switch (pantalla) {
                    case 1:
                        newScreen = new N1Pantalla1(game);
                        break;
                    case 2:
                        newScreen = new N1Pantalla2(game);
                        break;
                    case 3:
                        newScreen = new N1Pantalla3(game);
                        break;
                }
                break;
        }

        return newScreen;
    }

}
