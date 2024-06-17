package in.ac.nimhans.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Puneeth Reddy on 9/18/2017.
 Initial version 
 */

public class WindowAssetManager {
		private Sprite windowLightOffSprite = new Sprite(new Texture(Gdx.files.internal("window_light_off.png")));
	private Sprite windowLightOnSprite = new Sprite(new Texture(Gdx.files.internal("window_light_on.png")));


	public Sprite getWindowSprite(boolean lightOn) {
		return (lightOn) ? windowLightOnSprite : windowLightOffSprite;
	}


	public float getWindowWidth() {
		return windowLightOffSprite.getWidth();
	}


	public float getWindowHeight() {
		return windowLightOffSprite.getHeight();
	}
}
