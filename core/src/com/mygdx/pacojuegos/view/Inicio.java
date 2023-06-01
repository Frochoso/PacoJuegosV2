package com.mygdx.pacojuegos.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.LanguageManager;
import com.mygdx.pacojuegos.manager.ScreensManager;

public class Inicio implements Screen {

    private Stage stage;
    private Game game;

    private TextureRegion textura;

    private TextureRegion paco;

    public Inicio(Game aGame) {

        game = aGame;
        stage = new Stage(new ScreenViewport());

        textura=new TextureRegion(new Texture(AssetsManager.FONDO_INICIO));
        Image imagen=new Image(textura);
        imagen.setWidth(Gdx.graphics.getWidth());
        imagen.setHeight(Gdx.graphics.getHeight());
        stage.addActor(imagen);

        paco=new TextureRegion(new Texture(AssetsManager.PACO));
        Image imagenPaco=new Image(paco);
        imagenPaco.setWidth(284);
        imagenPaco.setHeight(300);
        imagenPaco.setPosition(112,225);
        stage.addActor(imagenPaco);

        Skin skin = new Skin(Gdx.files.internal(AssetsManager.SKIN_BOTON));
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.SKIN_ATLAS));
        skin.addRegions(atlas);


        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("button-down");
        style.font = skin.getFont("commodore-64");

        TextButton botonInicio = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.START), style);
        botonInicio.setWidth(Gdx.graphics.getWidth() / 4);
        botonInicio.setHeight(Gdx.graphics.getWidth() / 16);
        botonInicio.setPosition(Gdx.graphics.getWidth() / 2 - botonInicio.getWidth() / 2, Gdx.graphics.getHeight() - botonInicio.getHeight() * 7);
        botonInicio.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(ScreensManager.getSingleton().getPantallas(game,ScreensManager.PANTALLAS.N1));

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(botonInicio);


    }

    @Override
    public void show() {
        Gdx.app.log("StartScreen", "show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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

    //Método para liberar recursos
    public void dispose() {
        stage.dispose();
    }

}
