package in.ac.nimhans.model;

/**
 * Created by Puneeth Reddy on 9/15/2017.
 Initial version 
 */

public class Entity {

	private float xPos, yPos;


	Entity(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}


	public float getXPos() {
		return xPos;
	}


	public float getYPos() {
		return yPos;
	}
}
