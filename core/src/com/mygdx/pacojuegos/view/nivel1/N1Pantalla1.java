package com.mygdx.pacojuegos.view.nivel1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private TextureRegion manolo;
    private APedra aPedra;
    private ScreensManager myScreenManager;
    private Fiberto fiberto;
    private AssetsManager assetsManager;

    public N1Pantalla1(Game aGame) {

        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        assetsManager = new AssetsManager();
        assetsManager.loadFondo(AssetsManager.FONDO_N1P1_GANADO);
        assetsManager.finishLoading();


        fondo = new Fondo(stage, AssetsManager.FONDO_N1P1_JUGAR);
        stage.addActor(fondo);


        manolo = new TextureRegion(new Texture(AssetsManager.MANOLO));
        Image imagenPaco = new Image(manolo);
        imagenPaco.setWidth(284);
        imagenPaco.setHeight(300);
        imagenPaco.setPosition(112, 100);
        stage.addActor(imagenPaco);

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
            game.setScreen(ScreensManager.getSingleton().getPantallas(game,ScreensManager.PANTALLAS.N1,2));

        } else {
            fiberto.calculateCollisions(aPedra);
            if (fiberto.isMuerto()) {
                //game.setScreen(ScreensManager.getSingleton().getScreen(game,ScreensManager.SCREENS.INICIO));
                game.setScreen(ScreensManager.getSingleton().getPantallas(game,ScreensManager.PANTALLAS.N1,1));
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
