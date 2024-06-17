package in.ac.nimhans.connect4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import in.ac.nimhans.connect4.Assets;
import in.ac.nimhans.connect4.Connect4Game;

/**
 * This is where you screen code will go, any UI should be in here
 * 
 * @author Richard Taylor, Kevin Chen
 */
public class MainScreen implements Screen {
	public static Skin skin;
	public static Screen screen;

	private final Stage stage;
	private final SpriteBatch spriteBatch;
	private final Viewport screenViewPort;

	public MainScreen() {
		MainScreen.screen = this;
		spriteBatch = new SpriteBatch();
		screenViewPort=new ScreenViewport();
		screenViewPort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		stage = new Stage(screenViewPort, spriteBatch);

		// initialize skin with styles
		initSkin();

		// Add 'pennyPop' label
		Label pennyLabel = new Label("PennyPop", skin);
		pennyLabel.setPosition(Gdx.graphics.getWidth() / 4 - pennyLabel.getWidth() / 2,
				(float) (Gdx.graphics.getHeight() * 0.6));
		stage.addActor(pennyLabel);

		// Add 'SFX' button and listener
		ImageButton sfxButton = new ImageButton(skin, "sfxButton");
		sfxButton.setPosition(Gdx.graphics.getWidth() / 4 - sfxButton.getWidth(),
				Gdx.graphics.getHeight() / 2 - sfxButton.getWidth());
		sfxButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal(Assets.CLICK_SOUND));
				mp3Music.play();
				return true;
			}
		});
		stage.addActor(sfxButton);

		// Add 'API' button and define listener
		ImageButton apiButton = new ImageButton(skin, "apiButton");
		apiButton.setPosition(sfxButton.getX() + sfxButton.getWidth(), sfxButton.getY());
		apiButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//displayWeather();
				return true;
			}
		});
		stage.addActor(apiButton);

		// Add Player vs Player 'Game' button and listener
		ImageButton gameButton = new ImageButton(skin, "gameButton");
		gameButton.setPosition(Gdx.graphics.getWidth() / 4 - sfxButton.getWidth(),
				Gdx.graphics.getHeight() / 2 - sfxButton.getWidth() * 2);
		gameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Screen gameScreen = new GameScreen(false);
				Connect4Game.app.setScreen(gameScreen);
				return true;
			}
		});
		stage.addActor(gameButton);

		// Add Player vs AI 'Game' button and listener
		ImageButton AIgameButton = new ImageButton(skin, "AIButton");
		AIgameButton.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - sfxButton.getWidth() * 2);
		AIgameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Screen gameScreen = new GameScreen(true);
				Connect4Game.app.setScreen(gameScreen);
				return true;
			}
		});
		stage.addActor(AIgameButton);
	}

//	/**
//	 * Creates WeatherAPI object Pulls weather data from OpenWeatherMap, then
//	 * displays labels for weather on MainScreen
//	 */
//	private void displayWeather() {
//		WeatherAPI weather = new WeatherAPI(
//				"http://api.openweathermap.org/data/2.5/weather?q=San%20Francisco,US&appid=2e32d2b4b825464ec8c677a49531e9ae");
//
//		// ------------ Create Labels for weather conditions -----------//
//		// Add label for "Current Weather"
//		Label currentWeather = new Label("Current Weather", skin, "colored");
//		currentWeather.setPosition((float) (Gdx.graphics.getWidth() * 0.7 - currentWeather.getWidth() / 2),
//				(float) (Gdx.graphics.getHeight() * 0.6));
//		currentWeather.setColor(Color.BLACK);
//		stage.addActor(currentWeather);
//
//		// Add label for city
//		Label city = new Label(weather.getName(), skin, "colored");
//		city.setPosition((float) (Gdx.graphics.getWidth() * 0.7 - city.getWidth() / 2),
//				currentWeather.getY() - currentWeather.getHeight());
//		city.setColor(Color.BLUE);
//		stage.addActor(city);
//
//		// Add label for sky conditions
//		Label description = new Label("Sky: " + weather.getDescription(), skin);
//		description.setPosition((float) (Gdx.graphics.getWidth() * 0.7 - description.getWidth() / 2),
//				city.getY() - currentWeather.getHeight() * 2);
//		stage.addActor(description);
//
//		// Add label for temperature and wind
//		Label tempAndWind = new Label(
//				Math.round(weather.getTemp()) + " degrees, " + weather.getWindspeed() + "mph wind", skin);
//		tempAndWind.setPosition((float) (Gdx.graphics.getWidth() * 0.7 - tempAndWind.getWidth() / 2),
//				description.getY() - currentWeather.getHeight());
//		stage.addActor(tempAndWind);
//	}

	/**
	 * Defines the styles for various UI Widgets and stores them in the Skin
	 * variable
	 */
	private void initSkin() {
		// Create a font
		Texture t=new Texture(Gdx.files.internal(Assets.SFX_TXT));
		BitmapFont font = new BitmapFont(Gdx.files.internal(Assets.FONT));
		skin = new Skin();
		skin.add("default", font);

		// Create label style with default red color
		Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.RED);
		skin.add("default", labelStyle);

		// Create label with color to be chosen later
		Label.LabelStyle coloredLabel = new Label.LabelStyle(font, Color.WHITE);
		skin.add("colored", coloredLabel);

		// Create sfx button style
		ImageButton.ImageButtonStyle sfxButtonStyle = new ImageButton.ImageButtonStyle();
		sfxButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.SFX_TXT))).getDrawable();
		sfxButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("sfxButton", sfxButtonStyle);

		// Create api button style
		ImageButton.ImageButtonStyle apiButtonStyle = new ImageButton.ImageButtonStyle();
		apiButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.API_TXT))).getDrawable();
		apiButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("apiButton", apiButtonStyle);

		// Create game button style
		ImageButton.ImageButtonStyle gameButtonStyle = new ImageButton.ImageButtonStyle();
		gameButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.GAME_TXT))).getDrawable();
		gameButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("gameButton", gameButtonStyle);

		// Create AI button style
		ImageButton.ImageButtonStyle AIButtonStyle = new ImageButton.ImageButtonStyle();
		AIButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.AI_TXT))).getDrawable();
		AIButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("AIButton", AIButtonStyle);

		// Create Menu button style
		ImageButton.ImageButtonStyle menuButtonStyle = new ImageButton.ImageButtonStyle();
		menuButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.MENU_TXT))).getDrawable();
		menuButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("menuButton", menuButtonStyle);

		// Create Reset button style
		ImageButton.ImageButtonStyle resetButtonStyle = new ImageButton.ImageButtonStyle();
		resetButtonStyle.imageUp = new Image(new Texture(Gdx.files.internal(Assets.RESET_TXT))).getDrawable();
		resetButtonStyle.imageDown = new Image(new Texture(Gdx.files.internal(Assets.BTN_DOWN_TXT))).getDrawable();
		skin.add("resetButton", resetButtonStyle);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		stage.dispose();
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		screenViewPort.update(width, height, false);
		stage.setViewport(screenViewPort);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void pause() {
		// Irrelevant on desktop, ignore this
	}

	@Override
	public void resume() {
		// Irrelevant on desktop, ignore this
	}

}
