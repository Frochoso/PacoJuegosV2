package com.mygdx.pacojuegos.view.nivel1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;
import com.mygdx.pacojuegos.model.Fondo;
import com.mygdx.pacojuegos.model.Fruits;
import com.mygdx.pacojuegos.model.Jovani;
import com.mygdx.pacojuegos.view.panel.PanelNumerico;

import java.util.ArrayList;

public class N1Pantalla3 implements Screen {

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
    private int tiempoRestante;
    public static final ArrayList<Fruits> fallingFruits = new ArrayList<>();
    public static final ArrayList<Fruits> reserveFruits = new ArrayList<>();
    private ArrayList<Fruits> fruitsToRemove = new ArrayList<>();
    private Timer.Task cuentaAtrasTask;
    private BitmapFont fuenteContador;
    private Integer repetido;
    private boolean dificil;


    public N1Pantalla3(Game aGame, boolean dificil) {

        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.dificil = dificil;

        fondo = new Fondo(stage, AssetsManager.FONDO_INICIO);
        stage.addActor(fondo);

        panel = new PanelNumerico(SettingsManager.SCREEN_WIDTH - SettingsManager.JOVANI_WIDTH * 2, SettingsManager.SCREEN_HEIGHT - SettingsManager.JOVANI_HEIGHT, 40, game);
        panel.setData(0);

        fuenteContador = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        fuenteContador.getData().setScale(3);

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

        if (dificil) {
            iniciarCuentaAtras(15);
        } else {
            iniciarCuentaAtras(30);
        }
    }


    @Override
    public void show() {
        Gdx.app.log("Pantalla3", "show");
        Gdx.input.setInputProcessor(stage);
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

        batch.begin();
        String tiempoTexto = "Tiempo restante: " + tiempoRestante;
        GlyphLayout layout = new GlyphLayout(fuenteContador, tiempoTexto);

        float tiempoX = SettingsManager.JOVANI_WIDTH;
        float tiempoY = panel.getfPosY() + (panel.getfAncho() + layout.height) / 2;
        fuenteContador.draw(batch, layout, tiempoX, tiempoY);
        batch.end();
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

        if (fruit.getNombreTextura().equals("huevo")) {
            panel.incrementa(5);
        } else if (fruit.getNombreTextura().equals("naranja")) {
            panel.incrementa(5);
        } else if (fruit.getNombreTextura().equals("mango")) {
            panel.incrementa(5);
        } else if (fruit.getNombreTextura().equals("manzana")) {
            panel.incrementa(5);
        } else if (fruit.getNombreTextura().equals("lagarto")) {
            panel.resta(10);
        }

    }

    public void reproducirMotora() {
        if (!sonidoEnReproduccion && !reproduciendoMotora && Math.random() < 0.001) {
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

    public void iniciarCuentaAtras(int tiempoInicial) {
        tiempoRestante = tiempoInicial;
        cuentaAtrasTask = Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                tiempoRestante--;
                if (tiempoRestante < 0) {
                    tiempoRestante = 0;
                    game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.INICIO));
                    reset();
                }
            }
        }, 1f, 1f);
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
        if (cuentaAtrasTask != null) {
            cuentaAtrasTask.cancel();
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
        batch.dispose();
        if (frutaSound != null) {
            frutaSound.dispose();
        }
        fruits.fDispose();
        stage.dispose();
    }

}
