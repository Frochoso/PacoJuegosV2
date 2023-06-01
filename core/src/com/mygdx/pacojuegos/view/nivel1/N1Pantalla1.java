package com.mygdx.pacojuegos.view.nivel1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.model.APedra;
import com.mygdx.pacojuegos.model.Fiberto;
import com.mygdx.pacojuegos.model.Fondo;

public class N1Pantalla1 implements Screen {

    private Stage stage;
    private Game game;
    private Fondo fondo;
    private APedra aPedra;
    private Fiberto fiberto;
    private Integer repetido;

    public N1Pantalla1(Game aGame, boolean dificil) {

        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        fondo = new Fondo(stage, AssetsManager.FONDO_N1P1_JUGAR);
        stage.addActor(fondo);

        aPedra = new APedra(stage);
        stage.addActor(aPedra);

        fiberto = new Fiberto(stage);
        stage.addActor(fiberto);
    }

    @Override
    public void show() {
        Gdx.app.log("Nivel 1 Pantalla1", "show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        if (fiberto.getX() <= 112) {
            fiberto.setPosition(112, 100);
            game.setScreen(ScreensManager.getSingleton().getPantallas(game, ScreensManager.PANTALLAS.N1));
            dispose();

        } else {
            fiberto.calculateCollisions(aPedra);
            if (fiberto.isMuerto()) {
                //game.setScreen(ScreensManager.getSingleton().getScreen(game,ScreensManager.SCREENS.INICIO));
                game.setScreen(ScreensManager.getSingleton().getPantallas(game, ScreensManager.PANTALLAS.N1));
                dispose();
            }
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
