package com.mygdx.pacojuegos.view.panel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

import java.util.ArrayList;

public class PanelNumerico {

    protected ArrayList<BitmapFont> listaDigitos;
    protected ArrayList<BitmapFont> listaMostrada;
    protected float fPosX;
    protected float fPosY;
    protected float fAncho;
    private final Game game;
    private int iValorAlmacenado;
    private int tiempoRestante;
    private BitmapFont fuenteContador;

    public PanelNumerico(float pX, float pY, float nAncho, Game game) {
        fPosX = pX;
        fPosY = pY;
        fAncho = nAncho;
        this.game = game;
        //iniciarCuentaAtras(25);

        listaDigitos = new ArrayList<BitmapFont>();
        for (int i = 0; i < 10; i++) {
            BitmapFont nuevoDigito = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
            nuevoDigito.getData().setScale(2);
            listaDigitos.add(nuevoDigito);
        }
        fuenteContador = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        fuenteContador.getData().setScale(3);
        listaMostrada = new ArrayList<BitmapFont>();
        listaMostrada.add(listaDigitos.get(0));
        iValorAlmacenado = 0;
    }

    public void pintarse(SpriteBatch batch) {
        batch.begin();
        float pX = fPosX;
        float pY = fPosY;
        for (BitmapFont digito : listaMostrada) {
            digito.draw(batch, String.valueOf(iValorAlmacenado), pX, pY);
            pX += fAncho;
        }
        String tiempoTexto = "Tiempo restante: " + tiempoRestante;
        GlyphLayout layout = new GlyphLayout(fuenteContador, tiempoTexto);

        float tiempoX = SettingsManager.JOVANI_WIDTH;
        float tiempoY = pY + (fAncho + layout.height) / 2;
        fuenteContador.draw(batch, layout, tiempoX, tiempoY);
        batch.end();
    }

    public void setData(int iValor) {
        if (iValor >= 0) {
            iValorAlmacenado = iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValor);
            for (int i = 0; i < sNumero.length(); i++) {
                int digito = Integer.parseInt(String.valueOf(sNumero.charAt(i)));
                listaMostrada.add(listaDigitos.get(digito));
            }
        }
    }

    public void incrementa(int iValor) {
        iValorAlmacenado += iValor;
        listaMostrada.clear();
        String sNumero = String.valueOf(iValorAlmacenado);
        for (int i = 0; i < sNumero.length(); i++) {
            char c = sNumero.charAt(i);
            if (c != '-') {
                int digito = Integer.parseInt(String.valueOf(c));
                listaMostrada.add(listaDigitos.get(digito));
            }
        }
    }

    public void resta(int iValor) {
        if (iValorAlmacenado - iValor >= 0||iValorAlmacenado - iValor <= 0) {
            iValorAlmacenado -= iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValorAlmacenado);
            for (int i = 0; i < sNumero.length(); i++) {
                char c = sNumero.charAt(i);
                if (c != '-') {
                    int digito = Integer.parseInt(String.valueOf(c));
                    listaMostrada.add(listaDigitos.get(digito));
                }
            }
        }
    }

    public void iniciarCuentaAtras(int tiempoInicial) {
        tiempoRestante = tiempoInicial;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                tiempoRestante--;
                if (tiempoRestante < 0) {
                    tiempoRestante = 0;
                    game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.INICIO));
                }
            }
        }, 1f, 1f);
    }

    public void dispose() {
        // Libera los recursos de las fuentes
        for (BitmapFont font : listaDigitos) {
            font.dispose();
        }
    }
}