package com.mygdx.pacojuegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.repository.Conexiones;
import com.mygdx.pacojuegos.repository.DataUploader;
import com.mygdx.pacojuegos.view.Pantalla1;
import com.mygdx.pacojuegos.view.Pantalla2;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Screen myScreen;
    ScreensManager myScreenManager;
    GameManager myGame;
    static float fGameTime;
    protected Conexiones conexiones;

    public MyGdxGame(Conexiones conexiones) {
        this.conexiones = conexiones;
    }

    public MyGdxGame() {

    }

    public void create() {
        batch = new SpriteBatch();
        myScreenManager = ScreensManager.getSingleton();
        myScreenManager.initialize(this);
        myGame = GameManager.getSingleton();
        myScreen = myScreenManager.getScreen(this, ScreensManager.SCREENS.HISTORIA);
        this.setScreen(myScreen);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        conexiones.muestraNombreJugador();
    }

    @Override
    public void render() {

        if (Pantalla1.muerto || Pantalla2.muerto) {
            conexiones.insertarDatos();
            DataUploader.puntuacion = 0;
        }

        Screen currentScreen = this.getScreen();
        if (currentScreen != null) {
            ScreenUtils.clear(0, 0, 0, 1);
            batch.begin();
            currentScreen.render(Gdx.graphics.getDeltaTime());
            batch.setProjectionMatrix(camera.combined);
            batch.end();
            MyGdxGame.fGameTime += Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public static float getGameTime() {
        return fGameTime;
    }

}
