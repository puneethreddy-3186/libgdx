package in.ac.nimhans.model;

/**
 * Created by Puneeth Reddy on 9/7/2017.
 Initial version 
 */

public class Window extends Entity {

	private boolean lightOn = false;
	private int id;


	public Window(int id, float xPos, float yPos) {
		super(xPos, yPos);
		this.id = id;
	}

	public Window setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
		return this;
	}


	public boolean isLightOn() {
		return lightOn;
	}


	public int getId() {
		return id;
	}
}
