package com.mygdx.pacojuegos.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.PantallaBase;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.model.Fondo;
import com.mygdx.pacojuegos.model.Fruits;
import com.mygdx.pacojuegos.model.Jovani;
import com.mygdx.pacojuegos.view.panel.Contador;
import com.mygdx.pacojuegos.view.panel.PanelNumerico;

import java.util.ArrayList;

public class Pantalla3 extends PantallaBase implements Screen {

    private Stage stage;
    private Game game;
    private byte numeroMango = 1;
    private byte numeroManzana = 1;
    private boolean reproduciendoMotora = false;
    private byte numeroNaranja = 1;
    private byte numeroLagarto = 1;
    private Jovani jovani;
    private Fruits fruits;
    private Music frutaSound;
    private Music mango1;
    private Music mango2;
    private Music manzana1;
    private Music manzana2;
    private Music huevo;
    private Music naranja1;
    private Music naranja2;
    private Music naranja3;
    private Music lagarto1;
    private Music motora;
    private Music lagarto2;
    private SpriteBatch batch;
    private Fondo fondo;
    private boolean sonidoEnReproduccion;
    private PanelNumerico panel;
    public static final ArrayList<Fruits> fallingFruits = new ArrayList<>();
    public static final ArrayList<Fruits> reserveFruits = new ArrayList<>();
    private ArrayList<Fruits> fruitsToRemove = new ArrayList<>();
    private Contador contador;
    private Dialog dialog;
    private Label tiempoLabel;
    private Label puntosLabel;
    private boolean dificil;
    private Skin skin;
    private float tiempoX = SettingsManager.JOVANI_WIDTH;
    private float tiempoY = SettingsManager.SCREEN_HEIGHT - SettingsManager.JOVANI_HEIGHT;
    private Label.LabelStyle labelStyle;
    private ImageButton flechaDcha;
    private ImageButton flechaIzqda;
    private Music jovaniMusic;

    public Pantalla3(Game aGame, boolean dificil) {

        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.dificil = dificil;

        fondo = new Fondo(stage, AssetsManager.FONDO3);
        stage.addActor(fondo);

        panel = new PanelNumerico();
        panel.setData(0);

        inicializarContador(dificil);

        fruits = new Fruits();
        fallingFruits.add(fruits);

        jovani = new Jovani(stage);
        stage.addActor(jovani);

        batch = new SpriteBatch();

        sonidoEnReproduccion = false;

        mango1 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_MANGO1));
        mango2 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_MANGO2));
        huevo = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_HUEVO));
        manzana1 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_MANZANA1));
        manzana2 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_MANZANA2));
        naranja1 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_NARANJA1));
        naranja2 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_NARANJA2));
        naranja3 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_NARANJA3));
        lagarto1 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_LAGARTO1));
        lagarto2 = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CHOCA_LAGARTO2));
        motora = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.JOVANI_FINAL));

        skin = new Skin(Gdx.files.internal(AssetsManager.SKIN_BOTON));
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.SKIN_ATLAS));
        skin.addRegions(atlas);

        BitmapFont font = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        font.getData().setScale(2f);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        dialog = new Dialog("", skin);
        tiempoLabel = new Label("", labelStyle);
        puntosLabel = new Label("", labelStyle);
        dialog.setModal(false);
        dialog.setTouchable(Touchable.disabled);
        dialog.getContentTable().add(tiempoLabel).colspan(1).padRight(SettingsManager.SCREEN_WIDTH / 8);
        dialog.getContentTable().add(puntosLabel).colspan(2).padLeft(SettingsManager.SCREEN_WIDTH / 8);
        dialog.show(stage);
        stage.addActor(dialog);

        Texture texturaFlechaDerecha = new Texture(Gdx.files.internal(AssetsManager.FLECHA_DCHA));
        Texture texturaFlechaIzqda = new Texture(Gdx.files.internal(AssetsManager.FLECHA_IZQDA));

        ImageButton.ImageButtonStyle estiloDcha = new ImageButton.ImageButtonStyle();
        estiloDcha.imageUp = new TextureRegionDrawable(new TextureRegion(texturaFlechaDerecha));

        ImageButton.ImageButtonStyle estiloIzda = new ImageButton.ImageButtonStyle();
        estiloIzda.imageUp = new TextureRegionDrawable(new TextureRegion(texturaFlechaIzqda));


        flechaDcha = new ImageButton(estiloDcha);
        flechaDcha.setPosition(SettingsManager.SCREEN_WIDTH - SettingsManager.JOVANI_WIDTH * 4, SettingsManager.SCREEN_HEIGHT - SettingsManager.FLECHA_HEIGHT);
        flechaDcha.setWidth(Gdx.graphics.getWidth() / 4f);
        flechaDcha.setHeight(Gdx.graphics.getWidth() / 4f);
        flechaDcha.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jovani.setDireccion(true);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        flechaIzqda = new ImageButton(estiloIzda);
        flechaIzqda.setPosition(SettingsManager.JOVANI_WIDTH, SettingsManager.SCREEN_HEIGHT - SettingsManager.FLECHA_HEIGHT);
        flechaIzqda.setWidth(Gdx.graphics.getWidth() / 4f);
        flechaIzqda.setHeight(Gdx.graphics.getWidth() / 4f);
        flechaIzqda.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jovani.setDireccion(false);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(flechaIzqda);
        stage.addActor(flechaDcha);

        jovaniMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.CANCION_JOVANI));
    }

    private void inicializarContador(boolean dificil) {
        if (!dificil) {
            contador = new Contador(stage, 30);
        } else {
            contador = new Contador(stage, 15);
        }
        contador.start();
    }

    @Override
    public void show() {
        inicializarContador(dificil);
        Gdx.app.log("Pantalla3", "show");
        Gdx.input.setInputProcessor(stage);

        dialog.setPosition(tiempoX, tiempoY);
        dialog.setSize(SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT / 6);

        dialog.toFront();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        jovani.act(delta);

        for (Fruits fruit : fallingFruits) {
            if (fruit.colisiona(jovani)) {
                fruitsToRemove.add(fruit);
                colisonSonidos(fruit);
                colisionPuntos(fruit);

            }
        }

        fallingFruits.removeAll(fruitsToRemove);

        fruits.fDraw(batch);
        fruits.fMove();

        panel.pintarse(batch);
        reproducirMotora();

        if (Math.random() > 0.97f) {
            fruits.falling();
        }

        if (dificil) {
            for (Fruits frutas : fallingFruits) {
                frutas.setVelY(-8f);
            }
        }

        tiempoLabel.setText("Tiempo: " + contador.getTiempoRestante());

        puntosLabel.setText("Puntos: " + panel.getiValorAlmacenado());

        if (contador.getTiempoRestante() == 0) {
            panel.pasarPuntuacion(panel.getiValorAlmacenado());
            reset();
            jovaniMusic.stop();
             game.setScreen(ScreensManager.getSingleton().transiciones(game, AssetsManager.FONDO3));
        } else {
            jovaniMusic.play();
            jovaniMusic.setLooping(true);
        }
    }

    public String getFondo(){
        return AssetsManager.FONDO3;
    }

    public void colisonSonidos(Fruits fruit) {
        if (!sonidoEnReproduccion) {
            if (fruit.getNombreTextura().equals("huevo")) {
                frutaSound = huevo;
            } else if (fruit.getNombreTextura().equals("naranja")) {
                if (numeroNaranja == 1) {
                    numeroNaranja++;
                    frutaSound = naranja1;
                } else if (numeroNaranja == 2) {
                    numeroNaranja++;
                    frutaSound = naranja2;
                } else if (numeroNaranja == 3) {
                    numeroNaranja = 1;
                    frutaSound = naranja3;
                }
            } else if (fruit.getNombreTextura().equals("mango")) {
                if (numeroMango == 1) {
                    numeroMango++;
                    frutaSound = mango1;
                } else if (numeroMango == 2) {
                    numeroMango = 1;
                    frutaSound = mango2;
                }
            } else if (fruit.getNombreTextura().equals("manzana")) {
                if (numeroManzana == 1) {
                    numeroManzana++;
                    frutaSound = manzana1;
                } else if (numeroManzana == 2) {
                    numeroManzana = 1;
                    frutaSound = manzana2;
                }
            } else if (fruit.getNombreTextura().equals("lagarto")) {
                if (numeroLagarto == 1) {
                    numeroLagarto++;
                    frutaSound = lagarto1;
                } else if (numeroLagarto == 2) {
                    numeroLagarto = 1;
                    frutaSound = lagarto2;
                }
            }
            if (frutaSound != null) {
                frutaSound.setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {
                        sonidoEnReproduccion = false;
                    }
                });
                frutaSound.play();
                sonidoEnReproduccion = true;
            }
        }
    }

    public void colisionPuntos(Fruits fruit) {

        if (fruit.getNombreTextura().equals("huevo") ||
                fruit.getNombreTextura().equals("naranja") ||
                fruit.getNombreTextura().equals("mango") ||
                fruit.getNombreTextura().equals("manzana")) {
            panel.incrementa(5);
        } else if (fruit.getNombreTextura().equals("lagarto")) {
            panel.resta(10);
        }

    }

    public void reproducirMotora() {
        if (!sonidoEnReproduccion && !reproduciendoMotora && Math.random() < 0.0001) {
            frutaSound = motora;
            reproduciendoMotora = true;

            if (frutaSound != null) {
                frutaSound.setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {
                        sonidoEnReproduccion = false;
                        if (frutaSound == motora) {
                            reproduciendoMotora = false;
                        }
                    }
                });
                frutaSound.play();
                sonidoEnReproduccion = true;
            }

        }
    }

    public void reset() {
        reproduciendoMotora = false;
        panel.setData(0);
        fallingFruits.clear();
        reserveFruits.clear();
        fruitsToRemove.clear();
        jovani.reset();
        sonidoEnReproduccion = false;
        frutaSound = null;
        mango1.stop();
        mango2.stop();
        manzana1.stop();
        manzana2.stop();
        huevo.stop();
        naranja1.stop();
        naranja2.stop();
        naranja3.stop();
        lagarto1.stop();
        lagarto2.stop();
        motora.stop();
        jovaniMusic.stop();
        contador.stop();
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
        batch.dispose();

        if (frutaSound != null) {
            frutaSound.dispose();
        }
        fruits.fDispose();
        stage.dispose();

        jovaniMusic.dispose();
    }

}
