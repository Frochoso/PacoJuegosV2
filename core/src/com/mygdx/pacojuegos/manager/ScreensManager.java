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
    private Integer[] pantallas=new Integer[6];
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

    public Screen getPantallas(Game game, ScreensManager.PANTALLAS screenToGet) {
        byte random;
        for (int i = 0; i < pantallas.length; i++) {
            pantallas[i] = 0;
        }
        Screen newScreen = null;
        switch (screenToGet) {
            case N1:
                int repeticiones = 0;
                boolean repetido = false;
                int ultimaPantalla = -1;

                do {
                    random = (byte) (Math.random() * 6);
                    switch (random) {
                        case 0:
                            newScreen = new N1Pantalla1(game, false);
                            pantallas[0] = repeticiones++;
                            break;
                        case 1:
                            newScreen = new N1Pantalla2(game, false);
                            pantallas[1] = repeticiones++;
                            break;
                        case 2:
                            newScreen = new N1Pantalla3(game, false);
                            pantallas[2] = repeticiones++;
                            break;
                        case 3:
                            newScreen = new N1Pantalla1(game, true);
                            pantallas[3] = repeticiones++;
                            break;
                        case 4:
                            newScreen = new N1Pantalla2(game, true);
                            pantallas[4] = repeticiones++;
                            break;
                        case 5:
                            newScreen = new N1Pantalla3(game, true);
                            pantallas[5] = repeticiones++;
                            break;
                    }

                    for (int i = 0; i < pantallas.length; i++) {
                        if (pantallas[i] != null && pantallas[i] > 1) {
                            repetido = true;
                            break;
                        } else {
                            repetido = false;
                            ultimaPantalla = random;
                        }
                    }
                } while (repetido);

                if (ultimaPantalla != -1) {
                    switch (ultimaPantalla) {
                        case 0:
                            newScreen = new N1Pantalla1(game, false);
                            break;
                        case 1:
                            newScreen = new N1Pantalla2(game, false);
                            break;
                        case 2:
                            newScreen = new N1Pantalla3(game, false);
                            break;
                        case 3:
                            newScreen = new N1Pantalla1(game, true);
                            break;
                        case 4:
                            newScreen = new N1Pantalla2(game, true);
                            break;
                        case 5:
                            newScreen = new N1Pantalla3(game, true);
                            break;
                    }
                } else {
                    newScreen = new Inicio(game);
                }
        }

        return newScreen;
    }



}
