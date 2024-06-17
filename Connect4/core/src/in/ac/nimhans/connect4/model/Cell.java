package in.ac.nimhans.connect4.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import in.ac.nimhans.connect4.Assets;
import in.ac.nimhans.connect4.screens.GameScreen;

public class Cell extends Image {
	public static int SIZE = 100;
	public static int MARGIN = 5;
	public static int OFFSET = 17;

	public int indX;
	public int indY;

	private Stage stage;
	private Image img;
	private Image piece;
	private int occupied = 0; // 0 = none, -1 = red, 1 = yellow

	/**
	 * Constructor for Cell. Cell holds an image to represent itself on a grid,
	 * and some game information
	 * 
	 * @param indX
	 *            X index
	 * @param indY
	 *            Y index
	 * @param x
	 *            image X position
	 * @param y
	 *            image Y position
	 */
	public Cell(final int indX, final int indY, int x, int y) {
		this.stage = GameScreen.stage;
		this.indX = indX;
		this.indY = indY;
		this.img = new Image(new Texture(Gdx.files.internal(Assets.SQUARE_TXT)));
		img.setX(x);
		img.setY(y);
		stage.addActor(img);
		img.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// Call gameLogic to find where piece belongs and check game state
				GameScreen.game.placePiece(indX, indY);
				return true;
			}
		});
	}

	/**
	 * Sets a game piece in the Cell
	 * 
	 * @param color:
	 *            the color of the piece ( -1 = red, 1 = yellow )
	 */
	public void setCell(int color) {
		Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal(Assets.CLICK_SOUND));
		mp3Music.play();
		occupied = color;
		if (color == -1) {
			piece = new Image(new Texture(Gdx.files.internal(Assets.RED_TXT)));
		} else {
			piece = new Image(new Texture(Gdx.files.internal(Assets.YELLOW_TXT)));
		}
		piece.setPosition(img.getX() + OFFSET, img.getY() + OFFSET);
		stage.addActor(piece);
	}

	/**
	 * Checks which player occupies this Cell
	 * 
	 * @return integer between -1 and 1. -1 = red, 0 = empty, 1 = yellow
	 */
	public int isOccupied() {
		return occupied;
	}
}
