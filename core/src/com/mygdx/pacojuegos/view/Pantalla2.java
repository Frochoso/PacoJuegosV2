package com.mygdx.pacojuegos.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.PantallaBase;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.model.Coches;
import com.mygdx.pacojuegos.model.Fondo;
import com.mygdx.pacojuegos.model.Paco;
import com.mygdx.pacojuegos.view.panel.CartelInfo;

public class Pantalla2 extends PantallaBase implements Screen {

    private TextureAtlas atlas;
    private Stage stage;
    private Paco paco;
    private Game game;
    private boolean partidaPerdida = false;
    private boolean partidaGanada = false;
    private Coches coche1;
    private Coches coche2;
    private Coches coche3;
    private Fondo fondo;
    private boolean dificil;
    private Music musicaPaco;
    private boolean pantallaAparecida;
    private CartelInfo cartelInfo;
    public static boolean muerto = false;

    public Pantalla2(Game aGame, boolean dificil) {
        this.dificil = dificil;
        game = aGame;
        stage = new Stage(new ScreenViewport());
        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.PACO_RUNNING_ATLAS_FILE));
        Gdx.input.setInputProcessor(stage);
        pantallaAparecida = false;

        fondo = new Fondo(stage, AssetsManager.MAPA_N1P12);
        stage.addActor(fondo);

        paco = new Paco(stage);
        stage.addActor(paco);

        byte random1 = (byte) (Math.random() * 3 + 1);
        byte random2 = (byte) (Math.random() * 3 + 1);
        byte random3 = (byte) (Math.random() * 3 + 1);

        coche1 = new Coches(stage, 0, 100, 350, random1);
        coche2 = new Coches(stage, 1, 100, 185, random2);
        coche3 = new Coches(stage, 2, 100, 120, random3);

        stage.addActor(coche1);
        stage.addActor(coche2);
        stage.addActor(coche3);

        cartelInfo = new CartelInfo(stage, SettingsManager.SCREEN_WIDTH / 2, SettingsManager.SCREEN_HEIGHT / 1.5f, AssetsManager.INFO_TOCA_ATLAS, AssetsManager.INFO_TOCA_FILE);
        stage.addActor(cartelInfo);

        musicaPaco = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CANCION_PACO));
    }

    @Override
    public void show() {
        Gdx.app.log("Nivel 1 Pantalla2", "show");
        Gdx.input.setInputProcessor(stage);
        pantallaAparecida = true;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (coche1.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5 || coche2.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5 || coche3.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaPerdida = true;
            muerto = true;
        }

        if (paco.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaGanada = true;
        }

        if (partidaPerdida) {
            game.setScreen(ScreensManager.getSingleton().transicionesMuerte(game, AssetsManager.MAPA_N1P12));
            paco.reset();
            coche1.reset();
            coche2.reset();
            coche3.reset();
            musicaPaco.stop();
            this.dispose();
        }

        if (partidaGanada) {
            game.setScreen(ScreensManager.getSingleton().transiciones(game, AssetsManager.MAPA_N1P12));
            paco.reset();
            coche1.reset();
            coche2.reset();
            coche3.reset();
            paco.pasarPuntos(paco.getPuntos(), paco.getContadorToques());
            musicaPaco.stop();
            this.dispose();
        }

        paco.act(delta);
        coche1.defineCoche(delta, dificil);
        coche2.defineCoche(delta, dificil);
        coche3.defineCoche(delta, dificil);
        stage.act(delta);
        stage.draw();

        musicaPaco.play();
        musicaPaco.setLooping(true);
    }

    public String getFondo() {
        return AssetsManager.MAPA_N1P12;
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
        stage.dispose();
        musicaPaco.dispose();
    }
}
