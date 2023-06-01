package com.mygdx.pacojuegos.view.nivel1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.model.Coches;
import com.mygdx.pacojuegos.model.Fondo;
import com.mygdx.pacojuegos.model.Paco;

public class N1Pantalla2 implements Screen {

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
    private Integer repetido;

    public N1Pantalla2(Game aGame, boolean dificil) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.PACO_RUNNING_ATLAS_FILE));
        Gdx.input.setInputProcessor(stage);

        fondo = new Fondo(stage, AssetsManager.MAPA_N1P12);
        stage.addActor(fondo);

        paco = new Paco(stage);
        stage.addActor(paco);


        byte random1 = (byte) (Math.random() * 3 + 1);
        byte random2 = (byte) (Math.random() * 3 + 1);
        byte random3 = (byte) (Math.random() * 3 + 1);

        //Se les asigna una velocidad aleatoria dependiendo del random
        coche1 = new Coches(stage, 0, 350, random1);
        coche2 = new Coches(stage, 1, 185, random2);
        coche3 = new Coches(stage, 2, 120, random3);

        stage.addActor(coche1);
        stage.addActor(coche2);
        stage.addActor(coche3);
    }

    @Override
    public void show() {
        Gdx.app.log("Nivel 1 Pantalla2", "show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (coche1.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaPerdida = true;
        }
        if (coche2.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaPerdida = true;
        }
        if (coche3.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaPerdida = true;
        }
        if (paco.getX() >= SettingsManager.SCREEN_WIDTH - SettingsManager.PACO_WIDTH * 1.5) {
            partidaGanada = true;
        }

        if (partidaPerdida) {
            // game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.INICIO));
            game.setScreen(ScreensManager.getSingleton().getPantallas(game, ScreensManager.PANTALLAS.N1));

        }

        if (partidaGanada) {
            game.setScreen(ScreensManager.getSingleton().getPantallas(game, ScreensManager.PANTALLAS.N1));

        }

        paco.act(delta);
        coche1.defineCoche(delta);
        coche2.defineCoche(delta);
        coche3.defineCoche(delta);
        stage.act(delta);
        stage.draw();
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
    }
}
