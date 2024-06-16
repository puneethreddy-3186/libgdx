package in.ac.nimhans.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Puneeth Reddy on 9/15/2017.
 Initial version 
 */

public class HouseAssetManager {

	private Sprite houseSprite = new Sprite(new Texture(Gdx.files.internal("house.png")));


	public float getHouseWidth() {
		return houseSprite.getWidth();
	}


	public float getHouseHeight() {
		return houseSprite.getHeight();
	}


	public float getFrontFaceCeilingHeight() {
		return getHouseHeight() - getLintelHeight();
	}


	public float getLintelHeight() {
		return getHouseHeight() * .65f;
	}


	public float getFrontFaceWidth() {
		return getHouseWidth() * .45f;
	}


	public float getSideFaceWidth() {
		return getHouseWidth() - getFrontFaceWidth();
	}

	public float getDoorWidth() {
		return 200.0f; // 200 pixels.
	}


	public float getDoorHeight() {
		return 200.0f; // 200 pixels.
	}


	public Sprite getHouseSprite() {
		return houseSprite;
	}
}
