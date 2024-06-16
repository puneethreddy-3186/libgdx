package in.ac.nimhans;

/**
 * Created by Puneeth Reddy on 10/16/2017.
 Initial version 
 */

public enum Level {

	LEVEL_1(1.25f, 50.0f, true, 0), LEVEL_2(1.0f, 50.0f, false, 0), LEVEL_3(0.85f, 40.0f, false, 1);


	Level(float scaleFactor, float windowGap_px, boolean onlyFrontFace, int reduceSideFaceRows) {
		this.scaleFactor = scaleFactor;
		this.windowGap_px = windowGap_px;
		this.onlyFrontFace = onlyFrontFace;
		this.reduceSideFaceRows = reduceSideFaceRows;
	}


	private float scaleFactor;
	private float windowGap_px;
	private boolean onlyFrontFace;
	private int reduceSideFaceRows;


	public float getScaleFactor() {
		return scaleFactor;
	}


	public float getWindowGap_px() {
		return windowGap_px;
	}


	public int getReduceSideFaceRows() {
		return reduceSideFaceRows;
	}


	public boolean isOnlyFrontFace() {
		return onlyFrontFace;
	}


	public static Level loadCurrentLevel(AppPreferences preferences) {
		int level = preferences.getLevel();
		switch (level) {
			case 1:
				return LEVEL_1;
			case 2:
				return LEVEL_2;
			case 3:
				return LEVEL_3;
		}
		return LEVEL_1;
	}
}
