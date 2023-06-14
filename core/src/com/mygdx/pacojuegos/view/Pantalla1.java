package com.mygdx.pacojuegos.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.PantallaBase;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.model.APedra;
import com.mygdx.pacojuegos.model.Fiberto;
import com.mygdx.pacojuegos.model.Fondo;
import com.mygdx.pacojuegos.repository.DataUploader;
import com.mygdx.pacojuegos.view.panel.CartelInfo;

import java.util.Random;

public class Pantalla1 extends PantallaBase implements Screen {

    private Stage stage;
    private Game game;
    private Fondo fondo;
    private APedra aPedra3;
    private APedra aPedra1;
    private APedra aPedra2;
    private Fiberto fiberto;
    private CartelInfo cartelInfo;
    private Music cancionFiberto;
    private boolean dificil;
    public static boolean muerto = false;

    public Pantalla1(Game aGame, boolean dificil) {

        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.dificil = dificil;

        fondo = new Fondo(stage, AssetsManager.FONDO_N1P1_JUGAR);
        stage.addActor(fondo);

        selectorDificultad();

        fiberto = new Fiberto(stage);
        stage.addActor(fiberto);

        cartelInfo = new CartelInfo(stage, SettingsManager.SCREEN_WIDTH / 2, SettingsManager.SCREEN_HEIGHT / 1.5f, AssetsManager.INFO_SALTA_ATLAS, AssetsManager.INFO_SALTA_FILE);
        stage.addActor(cartelInfo);

        cancionFiberto = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CANCION_FIBERTO));
    }

    @Override
    public void show() {
        Gdx.app.log("Nivel 1 Pantalla1", "show");
        Gdx.input.setInputProcessor(stage);
    }

    public String getFondo() {
        return AssetsManager.FONDO_N1P1_JUGAR;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();


        if (fiberto.getX() <= 0) {
            if (dificil) {
                DataUploader.sumaPuntuacion(20);
            } else {
                DataUploader.sumaPuntuacion(10);
            }
            game.setScreen(ScreensManager.getSingleton().transiciones(game, AssetsManager.FONDO_N1P1_JUGAR));
            this.dispose();

        } else {
            if (!dificil) {
                if (aPedra1.colisiona(fiberto) || aPedra2.colisiona(fiberto)) {
                    game.setScreen(ScreensManager.getSingleton().transicionesMuerte(game, AssetsManager.FONDO_N1P1_JUGAR));
                    cancionFiberto.stop();
                    muerto = true;
                    this.dispose();
                }
            } else if (dificil) {
                if (aPedra1.colisiona(fiberto) || aPedra2.colisiona(fiberto) || aPedra3.colisiona(fiberto)) {
                    game.setScreen(ScreensManager.getSingleton().transicionesMuerte(game, AssetsManager.FONDO_N1P1_JUGAR));
                    cancionFiberto.stop();
                    muerto = true;
                    this.dispose();
                }
            }


        }


        cancionFiberto.play();
        cancionFiberto.setLooping(true);
    }

    public void selectorDificultad() {
        if (!dificil) {

            Random random = new Random();
            int max = SettingsManager.SCREEN_WIDTH - SettingsManager.FIBERTO_WIDTH * 2;

            int num1 = random.nextInt((max) - (SettingsManager.SCREEN_WIDTH / 2) + 1) + (SettingsManager.SCREEN_WIDTH / 2);

            int num2 = num1 - (SettingsManager.FRUITS_WIDTH + 500);

            aPedra1 = new APedra(stage, num1, 100);
            aPedra2 = new APedra(stage, num2, 100);

            stage.addActor(aPedra1);
            stage.addActor(aPedra2);

        } else if (dificil) {

            Random random = new Random();
            int max = SettingsManager.SCREEN_WIDTH - SettingsManager.FIBERTO_WIDTH * 2;

            int num1 = random.nextInt((max) - (SettingsManager.SCREEN_WIDTH / 2) + 1) + (SettingsManager.SCREEN_WIDTH / 2);
            int num2 = 0;
            int num3 = 0;
            int diferencia;
            byte posicion = (byte) (Math.random() * 2 + 1);

            if (posicion == 1) {
                num2 = num1 - (SettingsManager.APEDRA_WIDTH + 300) - SettingsManager.FIBERTO_WIDTH / 6;
                num3 = num2 - (SettingsManager.APEDRA_WIDTH + 300) - SettingsManager.FIBERTO_WIDTH / 6;
            } else if (posicion == 2) {
                num2 = num1 - (SettingsManager.APEDRA_WIDTH + 300) - SettingsManager.FIBERTO_WIDTH / 6;
                num3 = num2 - (SettingsManager.APEDRA_WIDTH + 300) - SettingsManager.FIBERTO_WIDTH / 6;
            }
            if (num3 < 400) {
                diferencia = 400 - num3;
                num3 = num3 + diferencia;
                num2 = num2 + diferencia;
                num1 = num1 + diferencia;
            }

            aPedra1 = new APedra(stage, num1, 100);
            aPedra2 = new APedra(stage, num2, 100);
            aPedra3 = new APedra(stage, num3, 100);

            stage.addActor(aPedra1);
            stage.addActor(aPedra2);
            stage.addActor(aPedra3);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        cancionFiberto.dispose();
        stage.dispose();
    }

}
