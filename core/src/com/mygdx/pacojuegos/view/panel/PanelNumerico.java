package com.mygdx.pacojuegos.view.panel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.repository.DataUploader;

import java.util.ArrayList;

public class PanelNumerico {

    protected ArrayList<BitmapFont> listaDigitos;
    protected ArrayList<BitmapFont> listaMostrada;
    protected float fPosX;
    protected float fPosY;
    protected float fAncho;
    private int iValorAlmacenado;
    private static DataUploader puntuaciones;

    public PanelNumerico() {

        listaDigitos = new ArrayList<BitmapFont>();
        for (int i = 0; i < 10; i++) {
            BitmapFont nuevoDigito = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
            nuevoDigito.getData().setScale(3);
            listaDigitos.add(nuevoDigito);
        }

        listaMostrada = new ArrayList<BitmapFont>();
        listaMostrada.add(listaDigitos.get(0));
        iValorAlmacenado = 0;
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
        int nuevoValor = iValorAlmacenado - iValor;
        if (nuevoValor >= 0) {
            iValorAlmacenado = nuevoValor;
        } else {
            iValorAlmacenado = 0;
        }
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

    public int getiValorAlmacenado() {
        return iValorAlmacenado;
    }

    public static void pasarPuntuacion(int puntuacion) {
        puntuaciones.sumaPuntuacion(puntuacion);
    }

    public void dispose() {
        for (BitmapFont font : listaDigitos) {
            font.dispose();
        }
    }
}