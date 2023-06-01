package com.mygdx.pacojuegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pacojuegos.manager.GameManager;
import com.mygdx.pacojuegos.manager.ScreensManager;
import com.mygdx.pacojuegos.manager.SettingsManager;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	OrthographicCamera camera;
	Screen myScreen;
	int PPM =100;
	ScreensManager myScreenManager;
	GameManager myGame;
	static float fGameTime;


	@Override
	public void create () {

		batch = new SpriteBatch();
		myScreenManager = ScreensManager.getSingleton();
		myGame = GameManager.getSingleton();
		myScreen = myScreenManager.getScreen(this,ScreensManager.SCREENS.INICIO);
		//myScreen = myScreenManager.getPantallas(this,ScreensManager.PANTALLAS.N1,1);
		this.setScreen(myScreen);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);

	}

	@Override
	public void render () {
		Screen currentScreen = this.getScreen();
		if (currentScreen != null) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
			currentScreen.render(Gdx.graphics.getDeltaTime());
			batch.end();
			MyGdxGame.fGameTime += Gdx.graphics.getDeltaTime();
		}

	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}

	public static float getGameTime() {
		return fGameTime;
	}

}
