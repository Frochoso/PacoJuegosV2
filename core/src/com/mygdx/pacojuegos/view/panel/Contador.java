package com.mygdx.pacojuegos.view.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class Contador {
    private int tiempoRestante;
    private Timer.Task timerTask;
    private boolean isRunning;
    private BitmapFont fuenteContador;
    public Label contadorLabel;
    private Stage stageMio;

    public Contador(Stage stage, int tiempoInicial) {
        this.stageMio = stage;
        tiempoRestante = tiempoInicial;
        isRunning = false;
        fuenteContador = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        fuenteContador.getData().setScale(3);
    }

    public void setLabel(String valor) {
        this.contadorLabel.setText(valor);
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            timerTask = new Timer.Task() {
                @Override
                public void run() {
                    tiempoRestante--;
                    if (tiempoRestante < 0) {
                        stop();
                        tiempoRestante = 0;
                    }
                }
            };
            Timer.schedule(timerTask, 1f, 1f);
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            timerTask.cancel();
        }
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void draw(Batch batch, float x, float y) {
        String tiempoTexto = "Tiempo restante: " + tiempoRestante;
        GlyphLayout layout = new GlyphLayout(fuenteContador, tiempoTexto);
        fuenteContador.draw(batch, layout, x, y);
    }
}

