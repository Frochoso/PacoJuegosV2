package com.mygdx.pacojuegos.view.panel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pacojuegos.manager.AssetsManager;

import java.util.ArrayList;

public class PanelNumerico {

    protected ArrayList<BitmapFont> listaDigitos;
    protected ArrayList<BitmapFont> listaMostrada;
    protected float fPosX;
    protected float fPosY;
    protected float fAncho;
    private final Game game;
    private int iValorAlmacenado;

    private Stage stage;

    public PanelNumerico(float pX, float pY, float nAncho, Game game) {
        this.fPosX = pX;
        this.fPosY = pY;
        this.fAncho = nAncho;
        this.game = game;
        this.stage=stage;

        listaDigitos = new ArrayList<BitmapFont>();
        for (int i = 0; i < 10; i++) {
            BitmapFont nuevoDigito = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
            nuevoDigito.getData().setScale(2);
            listaDigitos.add(nuevoDigito);
        }
        listaMostrada = new ArrayList<BitmapFont>();
        listaMostrada.add(listaDigitos.get(0));
        iValorAlmacenado = 0;
    }

    public float getfPosY(){
        return fPosY;
    }
    public float getfPosX(){
        return fPosX;
    }
    public float getfAncho(){
        return fAncho;
    }

    public void pintarse(SpriteBatch batch) {
        batch.begin();
        float pX = fPosX;
        float pY = fPosY;
        for (BitmapFont digito : listaMostrada) {
            String digitoTexto = String.valueOf(listaDigitos.indexOf(digito));
            digito.draw(batch, digitoTexto, pX, pY);
            pX += fAncho;
        }

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



    public void dispose() {
        // Libera los recursos de las fuentes
        for (BitmapFont font : listaDigitos) {
            font.dispose();
        }
    }
}