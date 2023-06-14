package com.mygdx.pacojuegos.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;

import java.util.ArrayList;
import java.util.List;

public class HistoriaFin implements Screen {

    private Stage stage;
    private BitmapFont fuenteContador;
    private SpriteBatch batch;
    private List<String> parrafos = new ArrayList<>();
    private int indiceParrafoActual = 0;
    private float tiempoEntreLetras = 0.1f;
    private Table table;
    private Label label;
    private int indiceLetraActual = 0;
    private StringBuilder stringBuilder;
    private Game game;

    public HistoriaFin(Game game) {
        this.game = game;
        table = new Table();
        stage = new Stage();
        batch = new SpriteBatch();

        Skin skin = new Skin(Gdx.files.internal(AssetsManager.SKIN_BOTON));

        fuenteContador = new BitmapFont(Gdx.files.internal(AssetsManager.SKIN_TEXTO));
        fuenteContador.getData().setScale(3);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fuenteContador;
        labelStyle.font.getData().setScale(3);
        labelStyle.fontColor = skin.getColor("white");

        label = new Label("", labelStyle);
        label.setAlignment(Align.center);
        label.setWrap(true);
        table.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        table.add(label).expand().center().width(Gdx.graphics.getWidth());

        parrafos.add(AssetsManager.FINAL1);
        parrafos.add(AssetsManager.FINAL2);

        stage.addActor(table);

        stringBuilder = new StringBuilder();
    }

    private void mostrarSiguienteLetra() {
        if (indiceParrafoActual < parrafos.size()) {
            String parrafoActual = parrafos.get(indiceParrafoActual);
            if (indiceLetraActual < parrafoActual.length()) {
                stringBuilder.append(parrafoActual.charAt(indiceLetraActual));
                label.setText(stringBuilder.toString());
                indiceLetraActual++;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mostrarSiguienteLetra();
                    }
                }, tiempoEntreLetras);
            } else {
                if (Gdx.input.justTouched()) {
                    indiceParrafoActual++;
                    if (indiceParrafoActual >= parrafos.size()) {
                        game.setScreen(ScreensManager.getSingleton().transicionesMuerte(game, AssetsManager.FONDO_NEGRO));
                    } else {
                        stringBuilder.setLength(0);
                        indiceLetraActual = 0;
                        mostrarSiguienteLetra();
                    }
                }
            }
        }
    }

    @Override
    public void show() {
        Gdx.app.log("Historia Inicio", "show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        batch.begin();

        stage.draw();
        mostrarSiguienteLetra();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    @Override
    public void dispose() {
        stage.dispose();
        fuenteContador.dispose();
        batch.dispose();
    }


}
