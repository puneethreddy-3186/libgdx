package in.ac.nimhans.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import in.ac.nimhans.CogTrainGame;

/**
 * Created by Puneeth Reddy on 9/18/2017.
 Initial version 
 */

abstract class AbstractScreen implements Screen {

	/**
	 * The padding for the table in every stage.
	 */
	private final CogTrainGame game;

	/**
	 * Whether the default BACK/ESCAPE button handler should be used or not.
	 */
	private final boolean handleBack;

	/**
	 * Common stage.
	 */
	private Stage stage = null;


	AbstractScreen(CogTrainGame game) {
		this(game, true);
	}


	private AbstractScreen(CogTrainGame game, boolean handleBack) {
		this.game = game;
		this.handleBack = handleBack;
	}


	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}


	public void load() {

	}


	/**
	 * This method sets up the visual layout for this screen. Child classes
	 * have to override this method and add to the provided table the widgets
	 * they want to show in the screen.
	 */
	abstract void setUpInterface();


	@Override
	public void pause() {

	}


	@Override
	public void resume() {
	}


	public CogTrainGame getGame() {
		return game;
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.3f, 0.4f, 0.4f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}


	@Override
	public void show() {
		// Dispatch an analytic event.
		if (stage == null) {
			stage = new Stage(new ScreenViewport());
		}
		setUpInterface();
		Gdx.input.setCatchBackKey(true);
		if (handleBack) {
			InputMultiplexer multiplexer = new InputMultiplexer();
			multiplexer.addProcessor(new BackButtonInputProcessor(game));
			multiplexer.addProcessor(stage);
			Gdx.input.setInputProcessor(multiplexer);
		}
		else {
			Gdx.input.setInputProcessor(stage);
		}
	}


	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}


	@Override
	public void dispose() {
		stage.dispose();
		stage = null;
	}


	public abstract int getID();


	Stage getStage() {
		return stage;
	}


	private class BackButtonInputProcessor extends InputAdapter {

		private final CogTrainGame game;


		BackButtonInputProcessor(CogTrainGame game) {
			this.game = game;
		}


		@Override
		public boolean keyDown(int keycode) {
			return keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK;
		}


		@Override
		public boolean keyUp(int keycode) {
			if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
				handleBackPress();
				return true;
			}
			return false;
		}
	}


	protected void handleBackPress() {
		game.updateLastScreen();
	}
}
