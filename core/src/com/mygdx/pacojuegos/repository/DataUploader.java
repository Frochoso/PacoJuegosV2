package com.mygdx.pacojuegos.repository;


public class DataUploader implements Conexiones {
    public static int puntuacion;

    public static void sumaPuntuacion(int recibida) {
        puntuacion = puntuacion + recibida;
    }

    public static void sumaPuntuacionN2(double puntosN2) {
        puntuacion = (int) puntosN2;
    }

    public static int devuelvePuntuacion() {
        return puntuacion;
    }

    @Override
    public void insertarDatos() {
    }

    @Override
    public void muestraNombreJugador() {
    }

}
