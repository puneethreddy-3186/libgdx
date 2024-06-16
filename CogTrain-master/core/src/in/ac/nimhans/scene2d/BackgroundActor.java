package in.ac.nimhans.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Puneeth Reddy on 9/15/2017.
 Initial version 
 */

public class BackgroundActor extends Image {

	private Sprite backgroundSprite = new Sprite(new Texture(Gdx.files.internal("background.png")));


	public BackgroundActor setBackgroundSImage(String imageFileName) {
		this.backgroundSprite = new Sprite(new Texture(Gdx.files.internal(imageFileName)));
		return this;
	}


	@Override public void draw(Batch batch, float parentAlpha) {
		batch.draw(backgroundSprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

}
