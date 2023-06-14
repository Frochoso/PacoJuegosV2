package com.mygdx.pacojuegos.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.mygdx.pacojuegos.manager.AssetsManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

import java.io.FileNotFoundException;

public class TransitionScreen extends ScreenAdapter implements Disposable {
    private ScreensManager screensManager;
    private SpriteBatch batch;
    private VideoPlayer videoPlayer;
    private ShaderProgram chromaKeyShader;
    private Game game;
    private boolean videoFinished;
    private Texture frame;
    private Texture backgroundTexture;
    private Texture secondTexture;

    private float elapsedTime;
    private boolean isBackgroundTextureRendered;

    public TransitionScreen(Game game, String pantallaPrevia, String pantallaContinua) {

        batch = new SpriteBatch();
        this.game = game;

        screensManager = new ScreensManager();

        backgroundTexture = new Texture(Gdx.files.internal(pantallaPrevia));
        secondTexture = new Texture(Gdx.files.internal(pantallaContinua));


        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                videoFinished = true;
            }
        });
        Thread videoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    videoPlayer.play(Gdx.files.internal(AssetsManager.TRANSICION_VIDEO));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        videoThread.start();
        videoFinished = false;

        chromaKeyShader = new ShaderProgram(
                Gdx.files.internal("shaders/chroma_key_shader.vert"),
                Gdx.files.internal("shaders/chroma_key_shader.frag")
        );

        if (!chromaKeyShader.isCompiled()) {
            Gdx.app.error("ChromaKeyShader", chromaKeyShader.getLog());
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        videoPlayer.update();
        batch.begin();

        elapsedTime += delta;

        if (!isBackgroundTextureRendered) {
            batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (elapsedTime >= 1.5f) {
                isBackgroundTextureRendered = true;
                elapsedTime = 0f;
            }
        } else {
            batch.draw(secondTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        frame = videoPlayer.getTexture();
        if (frame != null) {

            batch.setShader(chromaKeyShader);
            chromaKeyShader.setUniformf("chromaKeyColor", 0f, 1f, 0f);
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            batch.draw(frame, 0, 0, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);

        }
        batch.end();

        if (videoFinished) {
            game.setScreen(ScreensManager.getSingleton().getPantallas(game));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        videoPlayer.dispose();
        secondTexture.dispose();
        frame.dispose();
        backgroundTexture.dispose();
    }
}