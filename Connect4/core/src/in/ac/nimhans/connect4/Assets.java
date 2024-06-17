package in.ac.nimhans.connect4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
	public static final AssetManager manager = new AssetManager();

	public static final String SFX_TXT = "sfxButton.png";
	public static final String API_TXT = "apiButton.png";
	public static final String GAME_TXT = "gameButton.png";
	public static final String BTN_DOWN_TXT = "buttonDown.png";
	public static final String MENU_TXT = "menu.png";
	public static final String RESET_TXT = "reset.png";
	public static final String AI_TXT = "AIGame.png";
	public static final String SQUARE_TXT = "square.png";
	public static final String RED_TXT = "red.png";
	public static final String YELLOW_TXT = "yellow.png";
	public static final String CLICK_SOUND = "button_click.wav";
	public static final String FONT = "font.fnt";

	public static void load() {
		manager.load(SFX_TXT, Texture.class);
		manager.load(API_TXT, Texture.class);
		manager.load(GAME_TXT, Texture.class);
		manager.load(AI_TXT, Texture.class);
		manager.load(BTN_DOWN_TXT, Texture.class);
		manager.load(MENU_TXT, Texture.class);
		manager.load(RESET_TXT, Texture.class);
		manager.load(SQUARE_TXT, Texture.class);
		manager.load(RED_TXT, Texture.class);
		manager.load(YELLOW_TXT, Texture.class);
		manager.load(CLICK_SOUND, Sound.class);
		manager.load(FONT, BitmapFont.class);
	}

	public static void dispose() {
		manager.dispose();
	}

}
