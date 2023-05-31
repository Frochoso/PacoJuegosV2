package com.mygdx.pacojuegos.manager;

public class LanguageManager {

    private String[] enUso;
    private final String[] aSPANISH = {"Empezar", "TIEMPO: ", "PUNTUACION"};

    private static LanguageManager singleton;
    public static final int START = 0;
    public static final int TIME = 1;
    public static final int SCORE = 2;

    public enum lang {SPANISH, ENGLISH}

    private lang myLangs;

    public LanguageManager() {
        this.enUso = aSPANISH;
    }

    public static LanguageManager getSingleton() {
        if (singleton == null) {
            singleton = new LanguageManager();
        }
        return singleton;
    }

    public String getString(int n) {
        return enUso[n];
    }

    public void setString(lang myLangs) {
        enUso = aSPANISH;
    }
}
