package in.ac.nimhans;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Puneeth Reddy on 10/8/2017.
 Initial version 
 */

public class AppPreferences {

	private static final String PREF_APP_LEVEL = "app.level";
	private static final String PREF_WINDOW_GLOW_DURATION = "glow.duration";
	private static final String PREF_TIMER = "timer";

	private Preferences preferences;

	private static final String PREFS_NAME = "cog_train";


	private Preferences getPrefs() {
		if (preferences == null) {
			preferences = Gdx.app.getPreferences(PREFS_NAME);
		}
		return preferences;
	}


	public boolean isTimerOn() {
		return getPrefs().getBoolean(PREF_TIMER, true);
	}


	public void setTimer(boolean isTimerOn) {
		getPrefs().putBoolean(PREF_TIMER, isTimerOn);
		getPrefs().flush();
	}


	public void setLevel(int level) {
		getPrefs().putInteger(PREF_APP_LEVEL, level);
		getPrefs().flush();
	}


	public int getLevel() {
		return getPrefs().getInteger(PREF_APP_LEVEL, 1);
	}


	public float getGlowDuration() {
		return getPrefs().getFloat(PREF_WINDOW_GLOW_DURATION, 1.0f);
	}


	public void setGlowDuration(float windowGlowDuration) {
		getPrefs().putFloat(PREF_WINDOW_GLOW_DURATION, windowGlowDuration);
		getPrefs().flush();
	}
}
