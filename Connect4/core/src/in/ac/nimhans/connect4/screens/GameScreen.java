package in.ac.nimhans.connect4.screens;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import in.ac.nimhans.connect4.Connect4Game;
import in.ac.nimhans.connect4.model.Cell;
import in.ac.nimhans.connect4.model.GameLogic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
	public static Stage stage;
	public static GameLogic game;
	public static Screen screen;

	private final SpriteBatch spriteBatch;
	private Cell[][] board;
	private final Viewport screenViewPort;

	public GameScreen(boolean AI) {
		GameScreen.screen = this;
		spriteBatch = new SpriteBatch();
		screenViewPort=new ScreenViewport();
		stage = new Stage(screenViewPort, spriteBatch);
		//stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, spriteBatch);

		// Create game board and attach game logic
		createGrid(7, 6);
		game = new GameLogic(board, AI);

		// Create return to menu button
		ImageButton returnButton = new ImageButton(MainScreen.skin, "menuButton");
		returnButton.setPosition(50, Gdx.graphics.getHeight() / 2 - returnButton.getWidth() * 2);
		returnButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Connect4Game.app.setScreen(MainScreen.screen);
				return true;
			}
		});
		stage.addActor(returnButton);
	}

	/**
	 * Generates a game grid with given dimensions The game board is a 2D array
	 * of Cells
	 * 
	 * @param width
	 *            X dimension of Game Board
	 * @param height
	 *            Y dimension of Game Board
	 */
	public void createGrid(int width, int height) {
		int x = (Gdx.graphics.getWidth() / 2) - ((width * Cell.SIZE + (width - 1) * Cell.MARGIN) / 2);
		int y = (Gdx.graphics.getHeight() / 2) - ((height * Cell.SIZE) / 2);
		board = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = new Cell(i, j, x, y);
				y += Cell.SIZE + Cell.MARGIN;
			}
			y = (Gdx.graphics.getHeight() / 2) - ((height * Cell.SIZE) / 2);
			x += Cell.SIZE + Cell.MARGIN;
		}
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
