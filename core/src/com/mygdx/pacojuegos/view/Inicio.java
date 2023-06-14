package com.mygdx.pacojuegos.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.LanguageManager;
import com.mygdx.pacojuegos.manager.PantallaBase;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.repository.Player;

import java.util.Collections;
import java.util.Comparator;

public class Inicio extends PantallaBase implements Screen {

    private Stage stage;
    private static Stage stagePuntos = new Stage(new ScreenViewport());
    private Game game;
    private TextureRegion textura;
    private Music inicio;
    private ScreensManager myScreenManager;
    private TextureRegion paco;
    private final Skin skinDialog;
    private static boolean nombre = false;


    public Inicio(Game aGame) {

        game = aGame;
        stage = new Stage(new ScreenViewport());

        textura = new TextureRegion(new Texture(AssetsManager.FONDO_INICIO));
        Image imagen = new Image(textura);
        imagen.setWidth(Gdx.graphics.getWidth());
        imagen.setHeight(Gdx.graphics.getHeight());
        stage.addActor(imagen);

        paco = new TextureRegion(new Texture(AssetsManager.PACO));
        Image imagenPaco = new Image(paco);
        imagenPaco.setWidth(284);
        imagenPaco.setHeight(300);
        imagenPaco.setPosition(112, 225);
        stage.addActor(imagenPaco);

        Skin skin = new Skin(Gdx.files.internal(AssetsManager.SKIN_BOTON));
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.SKIN_ATLAS));
        skin.addRegions(atlas);

        skinDialog = new Skin(Gdx.files.internal(AssetsManager.SKIN_BOTON));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("button-down");
        style.font = skin.getFont("commodore-64");

        TextButton botonInicio = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.START), style);
        botonInicio.setWidth(Gdx.graphics.getWidth() / 4f);
        botonInicio.setHeight(Gdx.graphics.getWidth() / 16f);
        botonInicio.setPosition(Gdx.graphics.getWidth() / 2f - botonInicio.getWidth() / 2, Gdx.graphics.getHeight() - botonInicio.getHeight() * 5);
        botonInicio.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                myScreenManager = ScreensManager.getSingleton();
                myScreenManager.initialize(game);
                myScreenManager.vaciaPantallas();
                myScreenManager.initializeScreens();
                game.setScreen(ScreensManager.getSingleton().transiciones(game, AssetsManager.FONDO_INICIO));
                inicio.stop();

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(botonInicio);

        TextButton botonPuntuaciones = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.SCORE), style);
        botonPuntuaciones.setWidth(Gdx.graphics.getWidth() / 4);
        botonPuntuaciones.setHeight(Gdx.graphics.getWidth() / 16);
        botonPuntuaciones.setPosition(Gdx.graphics.getWidth() / 2 - botonInicio.getWidth() / 2, Gdx.graphics.getHeight() - botonInicio.getHeight() * 7);
        botonPuntuaciones.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                verPuntuaciones();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(botonPuntuaciones);

        TextButton botonConfig = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.CONFIGURACION), style);
        botonConfig.setWidth(Gdx.graphics.getWidth() / 4);
        botonConfig.setHeight(Gdx.graphics.getWidth() / 16);
        botonConfig.setPosition(Gdx.graphics.getWidth() / 2 - botonInicio.getWidth() / 2, Gdx.graphics.getHeight() - botonInicio.getHeight() * 3);
        botonConfig.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                BitmapFont font = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
                font.getData().setScale(2f);
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font;

                TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(skinDialog.get("default", TextField.TextFieldStyle.class));
                textFieldStyle.font = font;
                textFieldStyle.fontColor = Color.BLACK;

                TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
                style.up = skinDialog.getDrawable("button");
                style.down = skinDialog.getDrawable("button-down");
                style.font = font;

                final Dialog dialog = new Dialog(LanguageManager.getSingleton().getString(LanguageManager.CONFIGURACION), skinDialog);
                float dialogWidth = Gdx.graphics.getWidth() / 2f;
                float dialogHeight = Gdx.graphics.getHeight() / 2f;
                dialog.setSize(dialogWidth, dialogHeight);
                dialog.setModal(true);
                dialog.setTouchable(Touchable.enabled);

                Label labelNombre = new Label("Nombre: ", labelStyle);
                final TextField campoNombre = new TextField("", textFieldStyle);

                TextButton botonSubmit = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.ACEPTAR), style);
                botonSubmit.setWidth(dialogWidth / 2);
                botonSubmit.setHeight(dialogWidth / 8);
                botonSubmit.setPosition(dialogWidth / 2 - botonSubmit.getWidth() / 2, dialogWidth - botonSubmit.getHeight() * 5);
                botonSubmit.addListener(new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        if (campoNombre.getText().length() >= 3) {
                            Player.nombre = campoNombre.getText();
                            campoNombre.setText("");
                        }
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });

                TextButton botonCerrar = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.CERRAR), style);
                botonCerrar.setWidth(dialogWidth / 2);
                botonCerrar.setHeight(dialogWidth / 8);
                botonCerrar.setPosition(dialogWidth / 2 - botonSubmit.getWidth() / 2, dialogWidth - botonSubmit.getHeight() * 5);
                botonCerrar.addListener(new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        dialog.hide();
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });

                dialog.pad(Gdx.graphics.getWidth() / 12f);

                dialog.getContentTable().add(labelNombre).padRight(10);
                dialog.getContentTable().add(campoNombre).width(dialogWidth / 2).height(100).padBottom(10).row();
                dialog.getContentTable().add(botonSubmit).padTop(10).size(dialogWidth / 4, dialogWidth / 16).colspan(1);
                dialog.getContentTable().add(botonCerrar).padTop(10).size(dialogWidth / 4, dialogWidth / 16).colspan(1);

                dialog.show(stage);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(botonConfig);

        if (!nombre) {
            dialogNombre();
        }

        inicio = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CANCION_INICIO));
    }

    public void dialogNombre() {
        BitmapFont font = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        font.getData().setScale(2f);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(skinDialog.get("default", TextField.TextFieldStyle.class));
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skinDialog.getDrawable("button");
        style.down = skinDialog.getDrawable("button-down");
        style.font = font;

        final Dialog dialog = new Dialog(LanguageManager.getSingleton().getString(LanguageManager.NOMBRE), skinDialog);
        float dialogWidth = Gdx.graphics.getWidth() / 2f;
        float dialogHeight = Gdx.graphics.getHeight() / 2f;
        dialog.setSize(dialogWidth, dialogHeight);
        dialog.setModal(true);
        dialog.setTouchable(Touchable.enabled);

        Label labelNombre = new Label("Nombre: ", labelStyle);
        final TextField campoNombre = new TextField("", textFieldStyle);

        TextButton botonSubmit = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.ACEPTAR), style);
        botonSubmit.setWidth(dialogWidth / 2);
        botonSubmit.setHeight(dialogWidth / 8);
        botonSubmit.setPosition(dialogWidth / 2 - botonSubmit.getWidth() / 2, dialogWidth - botonSubmit.getHeight() * 5);
        botonSubmit.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (campoNombre.getText().length() >= 3) {
                    Player.nombre = campoNombre.getText();
                    dialog.hide();
                    nombre = true;
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        dialog.pad(Gdx.graphics.getWidth() / 12f);

        dialog.getContentTable().add(labelNombre).padRight(10);
        dialog.getContentTable().add(campoNombre).width(dialogWidth / 2).height(100).padBottom(10).row();
        dialog.getContentTable().add(botonSubmit).center().padTop(10).size(dialogWidth / 4, dialogWidth / 16).colspan(1);

        dialog.show(stage);
    }

    public void verPuntuaciones() {
        BitmapFont font = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        font.getData().setScale(2f);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(skinDialog.get("default", TextField.TextFieldStyle.class));
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skinDialog.getDrawable("button");
        style.down = skinDialog.getDrawable("button-down");
        style.font = font;

        final Dialog dialog = new Dialog(LanguageManager.getSingleton().getString(LanguageManager.SCORE), skinDialog);
        float dialogWidth = Gdx.graphics.getWidth() / 1.5f;
        float dialogHeight = Gdx.graphics.getHeight() / 2f;
        dialog.setSize(dialogWidth, dialogHeight);
        dialog.setModal(true);
        dialog.setTouchable(Touchable.enabled);

        Image manolo = new Image(new Texture(Gdx.files.internal(AssetsManager.MANOLO)));
        manolo.setHeight(dialogHeight / 32);
        manolo.setWidth(dialogWidth / 32);

        ScrollPane scrollPane = new ScrollPane(null, skinDialog);
        scrollPane.setScrollingDisabled(true, false);

        Label labelNombre = new Label("TU: ", labelStyle);
        Label labelJugador = new Label(Player.nombre.substring(0, 3).toUpperCase(), labelStyle);

        Table contentTable = new Table();
        scrollPane.setActor(contentTable);

        Player player = Player.getInstance();

        Collections.sort(player.getJugadores(), new Comparator<Player>() {
            @Override
            public int compare(Player jugador1, Player jugador2) {
                return Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion());
            }
        });

        Label label1 = new Label("", labelStyle);
        Label label2 = new Label("", labelStyle);
        Label label3 = new Label("", labelStyle);
        Label label4 = new Label("", labelStyle);
        if (player.getJugadores().isEmpty()) {
            label1.setText("!ENHORABUENA!");
            label2.setText("VAS A ESTRENAR");
            label3.setText("LA TABLA");
            label4.setText("DE PUNTUACIONES");
            contentTable.add(label1).padBottom(10).row();
            contentTable.add(label2).padBottom(10).row();
            contentTable.add(label3).padBottom(10).row();
            contentTable.add(label4).padBottom(10).row();
        } else {
            for (Player jugador : player.getJugadores()) {

                if (Player.nombre.substring(0, 3).toUpperCase().equalsIgnoreCase(jugador.getNombreJugador())) {
                    Label label = new Label("*" + jugador.getNombreJugador() + ": " + jugador.getPuntuacion(), labelStyle);
                    contentTable.add(label).padBottom(10).row();
                } else {
                    Label label = new Label(jugador.getNombreJugador() + ": " + jugador.getPuntuacion(), labelStyle);
                    contentTable.add(label).padBottom(10).row();
                }
            }

        }


        TextButton botonSubmit = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.ACEPTAR), style);
        botonSubmit.setWidth(dialogWidth / 2);
        botonSubmit.setHeight(dialogWidth / 8);
        botonSubmit.setPosition(dialogWidth / 2 - botonSubmit.getWidth() / 2, dialogWidth - botonSubmit.getHeight() * 5);
        botonSubmit.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dialog.hide();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Table tableLabel = new Table();
        tableLabel.add(labelNombre);
        tableLabel.add(labelJugador);

        scrollPane.setSize(dialogWidth, dialogHeight);

        dialog.getContentTable().add(manolo);
        dialog.getContentTable().add(scrollPane).width(dialogWidth / 2).height(dialogHeight).row();
        dialog.getContentTable().add(tableLabel).padTop(50f).padBottom(50f).colspan(1);
        dialog.getContentTable().add(botonSubmit).colspan(2);

        dialog.show(stage);
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
        inicio.play();
        inicio.setLooping(true);
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
        stagePuntos.dispose();
        inicio.dispose();
    }

    @Override
    public String getFondo() {
        return AssetsManager.FONDO_INICIO;
    }
}
