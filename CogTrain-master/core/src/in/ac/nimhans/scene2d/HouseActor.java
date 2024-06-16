package in.ac.nimhans.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import in.ac.nimhans.manager.HouseAssetManager;
import in.ac.nimhans.model.House;

/**
 * Created by Puneeth Reddy on 9/7/2017.
 Initial version 
 */

public class HouseActor extends Image {
	private House house;
	private HouseAssetManager houseAssetManager;


	public HouseActor(House house, HouseAssetManager houseAssetManager) {
		this.houseAssetManager = houseAssetManager;
		this.house = house;
	}


	@Override public void draw(Batch batch, float parentAlpha) {
		batch.draw(this.houseAssetManager.getHouseSprite(), this.house.getXPos(), this.house.getYPos());
	}


	public House getHouse() {
		return house;
	}


	public HouseAssetManager getHouseAssetManager() {
		return houseAssetManager;
	}
}
