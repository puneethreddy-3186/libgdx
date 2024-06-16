package in.ac.nimhans.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import in.ac.nimhans.CogTrainGame;
import in.ac.nimhans.SoundPlayer;
import in.ac.nimhans.scene2d.BackgroundActor;

/**
 * Created by Puneeth Reddy on 9/20/2017.
 Initial version 
 */

public class LoadingScreen extends AbstractTableScreen {

	public LoadingScreen(CogTrainGame game) {
		super(game);
	}


	@Override void setUpInterface(Table table) {
		getStage().addActor(new BackgroundActor().setBackgroundSImage("loading_background.jpg"));
		TextButton btStart = new TextButton("New Game", getGame().getSkin());
		btStart.addListener(new ChangeListener() {
			@Override public void changed(ChangeEvent event, Actor actor) {
				setScreenId(Screens.GAME);
			}
		});
		TextButton btSettings = new TextButton("Settings", getGame().getSkin());
		btSettings.addListener(new ChangeListener() {
			@Override public void changed(ChangeEvent event, Actor actor) {
				setScreenId(Screens.SETTINGS);
			}
		});
		table.add(btStart).width(700).height(150).pad(30).row();
		table.add(btSettings).width(700).height(150).pad(30);
		getGame().getSoundPlayer().playSound(SoundPlayer.SoundCode.LOADING_GAME);
	}


	@Override public int getID() {
		return Screens.LOADING;
	}
}
