package com.mygdx.pacojuegos.repository;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static String nombre;

    private List<Player> jugadores = new ArrayList<>();
    public String nombreJugador;
    public String campoPlayer;
    private static Player instance;
    public int puntuacion;

    public Player(String campoPlayer, String nombreJugador, int puntuacion) {
        this.campoPlayer = campoPlayer;
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
    }

    private Player() {
        jugadores = new ArrayList<>();
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public List<Player> getJugadores() {
        return jugadores;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

}