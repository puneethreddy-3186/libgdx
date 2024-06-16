package in.ac.nimhans.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import in.ac.nimhans.CogTrainGame;
import in.ac.nimhans.Level;
import in.ac.nimhans.LevelStatisticsManager;
import in.ac.nimhans.ResultIdentifier;
import in.ac.nimhans.SoundPlayer;
import in.ac.nimhans.WindowSequencer;
import in.ac.nimhans.manager.HouseAssetManager;
import in.ac.nimhans.model.House;
import in.ac.nimhans.scene2d.BackgroundActor;
import in.ac.nimhans.scene2d.HouseActor;
import in.ac.nimhans.scene2d.WindowActor;

/**
 * Created by Puneeth Reddy on 9/7/2017.
 Initial version 
 */

public class GameScreen extends AbstractTableScreen implements WindowActor.WindowSelectionListener {

	private HouseActor houseActor;
	private ResultIdentifier resultIdentifier = new ResultIdentifier();
	private LevelStatisticsManager levelStatisticsManager = new LevelStatisticsManager();
	private Label lbScore;
	private TextButton lbTimer;
	private WindowSequencer windowSequencer;
	private Group dynamicActorsGroup;
	private Integer points = 0;
	private static final Integer MAX_POINTS_SUCCESS = 5;
	private Integer timerCount;
	private float glowDurationSec;

	private boolean useSameSeq = false;


	public GameScreen(CogTrainGame game) {
		super(game);
	}


	@Override void setUpInterface(Table table) {
		glowDurationSec = getGame().getAppPreferences().getGlowDuration();
		Table headerTable = new Table();
		Label lbScoreText = new Label("Score", getGame().getSkin(), "big");
		lbScoreText.setColor(Color.GOLDENROD);
		lbScore = new Label("", getGame().getSkin(), "big");
		lbScore.setColor(Color.GOLDENROD);
		lbTimer = new TextButton("", getGame().getSkin());
		headerTable.add(lbScoreText).padRight(1200).size(300, 50).align(Align.left).row();
		headerTable.add(lbScore).size(300, 100).align(Align.left);
		headerTable.add(lbTimer).size(200, 100).align(Align.left);
		table.add(headerTable).fillX().expandY().align(Align.top).row();

		Group staticActorsGroup = new Group();
		staticActorsGroup.addActor(new BackgroundActor());
		getStage().addActor(staticActorsGroup);
		HouseAssetManager houseAssetManager = new HouseAssetManager();
		House house = new House((Gdx.graphics.getWidth() - houseAssetManager.getHouseWidth()), -20);
		houseActor = new HouseActor(house, houseAssetManager);
		staticActorsGroup.addActor(houseActor);

		dynamicActorsGroup = new Group();
		windowSequencer = new WindowSequencer(this);
		levelStatisticsManager.startTimer();
		generateNewSequence((int) Math.floor(0.4 * windowSequencer.getTotalWindows()), glowDurationSec, useSameSeq); // default/level can be changed based on level.
		getStage().addActor(dynamicActorsGroup);
	}


	private void generateNewSequence(int windowGlowCount, float animationDelay, boolean useSameSeq) {
		dynamicActorsGroup.clearChildren();
		resultIdentifier.resetSelectedSequence();
		Map<Integer, WindowActor> idToWindowActorsMap = windowSequencer.getIdToWindowActorsMap();
		Set<Map.Entry<Integer, WindowActor>> entries = idToWindowActorsMap.entrySet();
		for (Map.Entry<Integer, WindowActor> entry : entries) {
			WindowActor windowActor = entry.getValue();
			windowActor.getWindow().setLightOn(false);// set the lights off
			dynamicActorsGroup.addActor(windowActor);
		}
		List<Integer> windowGlowSequence = windowSequencer.generateWindowSequence(windowGlowCount, animationDelay, useSameSeq);
		resultIdentifier.setGeneratedSequence(windowGlowSequence);
		float actionDelay = (resultIdentifier.getGeneratedSequence().size() * animationDelay) + windowGlowCount + 2; // time to select the windows is twice the glow count.
		startTimer(Math.round(actionDelay));
	}


	private void startTimer(int delay) {
		timerCount = delay;
		final Timer timer = new Timer();
		Timer.Task task = new Timer.Task() {
			@Override public void run() {
				lbTimer.setText(timerCount.toString());
				timerCount -= 1;
				if (timerCount < 0) {
					timer.stop();
					showResult();
				}
			}
		};
		timer.scheduleTask(task, 0, 1);
		timer.start();
	}


	private void showResult() {
		boolean result = resultIdentifier.calculateResult();
		Gdx.app.log("Result", Boolean.toString(result));
		getGame().getSoundPlayer().playSound((result) ? SoundPlayer.SoundCode.SUCCESS : SoundPlayer.SoundCode.FAILURE);
		String[] dialogInfo = handleResult(result);
		showDialog(dialogInfo[0], dialogInfo[1]);
	}


	private String[] handleResult(boolean result) {
		String[] dialogInfo = new String[2];
		points += (result) ? MAX_POINTS_SUCCESS : 0;  // no negative points
		if (points < 0)
			points = 0;
		lbScore.setText(points.toString());
		useSameSeq = false; // reset same seq flag after every result.
		levelStatisticsManager.incTotalAttempts();
		if (points >= 100) {
			// win condition
			levelStatisticsManager.stopTimer();
			Level level = Level.loadCurrentLevel(getGame().getAppPreferences());
			switch (level.ordinal() + 1) {
				// In future it is possible to go to next level based on the requirement.
				case 1:
				case 2:
					dialogInfo[0] = "Hurray you finished Level " + (level.ordinal() + 1) + " !! in " + levelStatisticsManager.getLvlCompletionTimeMsg()
							+ "\n\n " + levelStatisticsManager.getSuccessfulRateMsg()
							+ "\n\n" + levelStatisticsManager.getUnsuccessfulSeqMsg();
					// uncomment when to want to progress to next level from here.
					//dialogInfo[1] = "Next Level";
					dialogInfo[1] = "Quit Game";
					break;
				case 3:
					dialogInfo[0] = "Hurray you finished Level " + (level.ordinal() + 1) + " !! in " + levelStatisticsManager.getLvlCompletionTimeMsg()
							+ "\n\n " + levelStatisticsManager.getSuccessfulRateMsg()
							+ "\n\n" + levelStatisticsManager.getUnsuccessfulSeqMsg();
					dialogInfo[1] = "Quit Game";
			}

		}
		else {
			if (result) {
				// Go to next sequence!!
				levelStatisticsManager.resetSameSeqAttempt();
				dialogInfo[0] = "Correct!";
				dialogInfo[1] = "Next";
			}
			else {
				levelStatisticsManager.incUnsuccessfulAttempts();
				levelStatisticsManager.incSameSeqAttempt();
				switch (levelStatisticsManager.getSameSeqAttemptCount()) {
					case 1:
						useSameSeq = true;
						dialogInfo[0] = "Incorrect! Please try the same sequence again";
						dialogInfo[1] = "Retry";
						break;
					case 2:
						useSameSeq = true;
						dialogInfo[0] = "Incorrect Again! You need to pay close attention";
						dialogInfo[1] = "Retry";
						break;
					case 3:
						levelStatisticsManager.resetSameSeqAttempt();
						levelStatisticsManager.incUnsuccessfulSeq();
						dialogInfo[0] = "Incorrect Again! Let's try a new sequence";
						dialogInfo[1] = "Next";
						break;
				}
			}
		}
		return dialogInfo;
	}


	private void showDialog(String lbText, final String btnText) {
		Label label = new Label(lbText, getGame().getSkin(), "big");
		label.setColor(Color.BLACK);
		label.setWrap(true);
		label.setAlignment(Align.center);
		Dialog dialog = new Dialog("", getGame().getSkin());
		dialog.padTop(50).padBottom(50);
		dialog.getContentTable().add(label).width(850).row();
		dialog.getButtonTable().padTop(50);

		TextButton btRetry = new TextButton(btnText, getGame().getSkin());
		dialog.button(btRetry);
		btRetry.addListener(new ChangeListener() {
			@Override public void changed(ChangeEvent event, Actor actor) {
				if (btnText.equals("Quit Game")) {
					Gdx.app.exit();
					return;
				}
				// uncomment when to want to progress to next level from here.
				//				if (btnText.equals("Next Level")) {
				//					Level level = Level.loadCurrentLevel(getGame().getAppPreferences());
				//					getGame().getAppPreferences().setLevel(level.ordinal() + 2);
				//					setScreenId(Screens.GAME);
				//					return;
				//				}
				if (points < 20) {
					generateNewSequence((int) Math.floor(0.4 * windowSequencer.getTotalWindows()), glowDurationSec, useSameSeq);
				}
				if (points >= 20 && points < 60) {
					generateNewSequence((int) Math.floor(0.5 * windowSequencer.getTotalWindows()), glowDurationSec, useSameSeq);
				}
				else if (points >= 60 && points < 100) {
					generateNewSequence((int) Math.floor(0.7 * windowSequencer.getTotalWindows()), glowDurationSec, useSameSeq);
				}

			}
		});
		dialog.key(Input.Keys.ENTER, true).key(Input.Keys.ESCAPE, false);
		dialog.invalidateHierarchy();
		dialog.invalidate();
		dialog.layout();
		dialog.show(getStage());
	}


	@Override public void onWindowTouched(int windowId, boolean isWindowLit) {
		if (isWindowLit)
			resultIdentifier.addSelectedWindow(windowId);
		else
			resultIdentifier.removeSelectedWindow(windowId);

	}


	@Override public void onWindowLit(int windowId, boolean isWindowLit) {
		getGame().getSoundPlayer().playSound((isWindowLit) ? SoundPlayer.SoundCode.LIGHT_ON : SoundPlayer.SoundCode.LIGHT_OFF);
	}


	public HouseActor getHouseActor() {
		return houseActor;
	}


	@Override public int getID() {
		return Screens.GAME;
	}


	@Override protected void handleBackPress() {
		showDialog("Do you want to quit?", "Quit Game");
	}
}
