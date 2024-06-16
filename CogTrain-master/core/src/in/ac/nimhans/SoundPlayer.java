/*
 * This file is part of Rectball.
 * Copyright (C) 2015-2017 Dani Rodríguez.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package in.ac.nimhans;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

public class SoundPlayer {

	SoundPlayer() {

	}


	public void playSound(SoundCode soundCode) {
		Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal(soundCode.getSoundFilePath()));
		mp3Music.play();
	}


	public enum SoundCode {
		LIGHT_OFF("sound/light_off.ogg"),
		LIGHT_ON("sound/light_on.ogg"),
		SUCCESS("sound/success.ogg"),
		FAILURE("sound/failure.ogg"),
		LOADING_GAME("sound/loading_game.ogg");
		String internalPath;


		SoundCode(String internalPath) {
			this.internalPath = internalPath;
		}


		public String getSoundFilePath() {
			return internalPath;
		}
	}
}