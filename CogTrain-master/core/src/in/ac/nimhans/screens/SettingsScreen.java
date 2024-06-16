package in.ac.nimhans.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import in.ac.nimhans.CogTrainGame;
import in.ac.nimhans.scene2d.BackgroundActor;

/**
 * Created by Puneeth Reddy on 10/8/2017.
 Initial version 
 */

public class SettingsScreen extends AbstractTableScreen {

	public SettingsScreen(CogTrainGame game) {
		super(game);
	}


	@Override void setUpInterface(Table table) {

		//		final Label lbTimer = new Label("Timer On", getGame().getSkin(), "big");
		//
		//		final CheckBox cbTimer = new CheckBox("", getGame().getSkin());
		//		cbTimer.setChecked(getGame().getAppPreferences().isTimerOn());
		//		cbTimer.addListener(new EventListener() {
		//			@Override
		//			public boolean handle(Event event) {
		//				boolean enabled = cbTimer.isChecked();
		//				lbTimer.setText((enabled) ? "Timer On" : "Timer Off");
		//				getGame().getAppPreferences().setTimer(enabled);
		//				return false;
		//			}
		//		});
		//		table.add(cbTimer).padBottom(100);
		//		table.add(lbTimer).padBottom(100).padRight(800).row();
		Group staticActorsGroup = new Group();
		staticActorsGroup.addActor(new BackgroundActor().setBackgroundSImage("loading_background.jpg"));
		getStage().addActor(staticActorsGroup);

		Label lbGlowDuration = new Label("Glow Duration", getGame().getSkin(), "big");
		final Slider slGlowDuration = new Slider(0.5f, 1.5f, 0.5f, false, getGame().getSkin());
		slGlowDuration.setValue(getGame().getAppPreferences().getGlowDuration());
		slGlowDuration.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				getGame().getAppPreferences().setGlowDuration(slGlowDuration.getValue());
				return false;
			}
		});

		Label lbGDSlow = new Label("Fast", getGame().getSkin(), "big");
		lbGDSlow.setColor(Color.GREEN);
		Label lbGDMedium = new Label("Medium", getGame().getSkin(), "big");
		lbGDMedium.setColor(Color.ORANGE);
		Label lbGDFast = new Label("Slow", getGame().getSkin(), "big");
		lbGDFast.setColor(Color.FIREBRICK);
		table.add(lbGDSlow).padLeft(lbGlowDuration.getPrefWidth());
		table.add(lbGDMedium);
		table.add(lbGDFast).padBottom(10).row();
		table.add(lbGlowDuration).padBottom(150).padRight(25);
		table.add(slGlowDuration).width(1000).height(150).padBottom(150);
		table.row();

		Label lbLevel = new Label("Level", getGame().getSkin(), "big");
		// window size
		final Slider slLevel = new Slider(1.0f, 3f, 1.0f, false, getGame().getSkin());
		slLevel.setValue(getGame().getAppPreferences().getLevel());
		slLevel.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				getGame().getAppPreferences().setLevel((int) slLevel.getValue());
				return false;
			}
		});

		Label lbLevelEasy = new Label("Easy", getGame().getSkin(), "big");
		lbLevelEasy.setColor(Color.GREEN);
		Label lbLevelMedium = new Label("Medium", getGame().getSkin(), "big");
		lbLevelMedium.setColor(Color.ORANGE);
		Label lbLevelDifficult = new Label("Difficult", getGame().getSkin(), "big");
		lbLevelDifficult.setColor(Color.FIREBRICK);
		table.add(lbLevelEasy).padBottom(50).padLeft(lbGlowDuration.getPrefWidth());
		table.add(lbLevelMedium).padBottom(50);
		table.add(lbLevelDifficult).padBottom(50).row();
		table.add(lbLevel).padRight(25);
		table.add(slLevel).width(1000);
		table.row();

	}


	@Override public int getID() {
		return Screens.SETTINGS;
	}
}
