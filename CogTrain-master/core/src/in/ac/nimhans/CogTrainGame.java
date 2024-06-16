package in.ac.nimhans;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import in.ac.nimhans.screens.AbstractTableScreen;
import in.ac.nimhans.screens.GameScreen;
import in.ac.nimhans.screens.LoadingScreen;
import in.ac.nimhans.screens.Screens;
import in.ac.nimhans.screens.SettingsScreen;

/**
 * Created by Puneeth Reddy on 9/18/2017.
 Initial version 
 */

public class CogTrainGame extends Game implements AbstractTableScreen.GameInteractionListener {

	private Skin skin;
	private SoundPlayer soundPlayer;
	private AppPreferences appPreferences;


	@Override
	public void create() {
		// Android enables dithering by default on some phones. Disable it for higher quality.
		Gdx.gl.glDisable(GL20.GL_DITHER);
		skin = new Skin(Gdx.files.internal("glassy/skin/glassy-mod-ui.json"));
		soundPlayer = new SoundPlayer();
		appPreferences = new AppPreferences();
		LoadingScreen loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	public AppPreferences getAppPreferences() {
		return appPreferences;
	}


	public Skin getSkin() {
		return skin;
	}


	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}


	@Override public void onGameScreenChange(int screenId) {
		switch (screenId) {
			case Screens.GAME:
				GameScreen gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;
			case Screens.SETTINGS:
				SettingsScreen settingsScreen = new SettingsScreen(this);
				setScreen(settingsScreen);
				break;
		}
	}


	public void updateLastScreen() {
		if (getScreen() instanceof LoadingScreen) {
			Gdx.app.exit();
		}
		else {
			LoadingScreen loadingScreen = new LoadingScreen(this);
			setScreen(loadingScreen);
		}
	}
}
