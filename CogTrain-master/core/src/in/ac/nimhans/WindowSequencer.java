package in.ac.nimhans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import in.ac.nimhans.manager.WindowAssetManager;
import in.ac.nimhans.model.Window;
import in.ac.nimhans.scene2d.WindowActor;
import in.ac.nimhans.screens.GameScreen;

/**
 * Created by Puneeth Reddy on 9/21/2017.
 Initial version 
 */

public class WindowSequencer {

	private Map<Integer, WindowActor> idToWindowActorsMap;
	private List<Integer> currentSequence;


	public WindowSequencer(GameScreen gameScreen) {
		calculateWindowsToFit(gameScreen);
	}


	private void calculateWindowsToFit(GameScreen gameScreen) {
		Level level = Level.loadCurrentLevel(gameScreen.getGame().getAppPreferences());
		idToWindowActorsMap = new HashMap<Integer, WindowActor>();
		WindowAssetManager windowAssetManager = new WindowAssetManager();

		int colFrontFace = (int) Math.floor((gameScreen.getHouseActor().getHouseAssetManager().getFrontFaceWidth())  //
				/ ((windowAssetManager.getWindowWidth() * level.getScaleFactor()) + level.getWindowGap_px()));
		int rowFrontFace = (int) Math.floor((gameScreen.getHouseActor().getHouseAssetManager().getLintelHeight())  //
				/ ((windowAssetManager.getWindowHeight() * level.getScaleFactor()) + level.getWindowGap_px()));

		int windowId = 0;
		float newXPos = gameScreen.getHouseActor().getHouse().getXPos() + level.getWindowGap_px();
		float newYPos = gameScreen.getHouseActor().getHouse().getYPos() + level.getWindowGap_px();
		for (int i = 0; i < rowFrontFace; i++) {
			for (int j = 0; j < colFrontFace; j++) {
				Window window = new Window(windowId, newXPos, newYPos);
				WindowActor windowActor = new WindowActor(window, windowAssetManager);
				windowActor.setScaleFactor(level.getScaleFactor()).setWindowSelectionListener(gameScreen);
				idToWindowActorsMap.put(windowId, windowActor);
				newXPos += windowAssetManager.getWindowWidth() * level.getScaleFactor() + level.getWindowGap_px();
				windowId++;
			}
			newYPos += windowAssetManager.getWindowHeight() * level.getScaleFactor() + level.getWindowGap_px();
			newXPos = gameScreen.getHouseActor().getHouse().getXPos() + level.getWindowGap_px(); // reset xPos
		}

		if (!level.isOnlyFrontFace()) {
			int colSideFace = (int) Math.floor((gameScreen.getHouseActor().getHouseAssetManager().getSideFaceWidth())  //
					/ ((windowAssetManager.getWindowWidth() * level.getScaleFactor()) + level.getWindowGap_px()));
			int rowSideFace = (int) Math.floor((gameScreen.getHouseActor().getHouseAssetManager().getLintelHeight())  //
					/ ((windowAssetManager.getWindowHeight() * level.getScaleFactor()) + level.getWindowGap_px())) - level.getReduceSideFaceRows();

			newXPos = gameScreen.getHouseActor().getHouse().getXPos() + gameScreen.getHouseActor().getHouseAssetManager().getFrontFaceWidth() + level.getWindowGap_px();
			newYPos = gameScreen.getHouseActor().getHouse().getYPos() + level.getWindowGap_px();

			for (int i = 0; i < rowSideFace; i++) {
				for (int j = 0; j < colSideFace; j++) {
					Window window = new Window(windowId, newXPos, newYPos);
					WindowActor windowActor = new WindowActor(window, windowAssetManager);
					windowActor.setScaleFactor(level.getScaleFactor()).setWindowSelectionListener(gameScreen);
					idToWindowActorsMap.put(windowId, windowActor);
					newXPos += windowAssetManager.getWindowWidth() * level.getScaleFactor() + level.getWindowGap_px();
					if (i == 0 && j == 0) {
						// after adding the first window to the side face. We need to skip the door and remove one window.
						newXPos += gameScreen.getHouseActor().getHouseAssetManager().getDoorWidth();
						j++;
					}
					windowId++;
				}
				newYPos += windowAssetManager.getWindowHeight() * level.getScaleFactor() + level.getWindowGap_px();
				newXPos = gameScreen.getHouseActor().getHouse().getXPos() + +gameScreen.getHouseActor().getHouseAssetManager().getFrontFaceWidth() + level.getWindowGap_px(); // reset xPos
			}
		}
	}


	public List<Integer> generateWindowSequence(int windowGlowCount, float animationDelay, boolean useSameSeq) {
		currentSequence = (useSameSeq ? currentSequence : generateWindowGlowSequence(idToWindowActorsMap.size(), windowGlowCount));
		Map<Integer, Float> windowIdToDelayMap = calculateAnimationDelay(currentSequence, animationDelay);
		Set<Map.Entry<Integer, Float>> entries = windowIdToDelayMap.entrySet();
		for (Map.Entry<Integer, Float> entry : entries) {
			idToWindowActorsMap.get(entry.getKey()).setWindowAnimation(entry.getValue(), animationDelay);
		}
		return currentSequence;
	}


	public Map<Integer, WindowActor> getIdToWindowActorsMap() {
		return idToWindowActorsMap;
	}


	private List<Integer> generateWindowGlowSequence(int windowCount, int windowGlowCount) {
		List<Integer> patternToGlow = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < windowGlowCount; i++) {
			int windowId = r.nextInt(windowCount);
			if (!patternToGlow.contains(windowId)) {
				patternToGlow.add(windowId);
			}
			else {
				i--; // retry once more to get the a unique sequence
			}
		}
		return patternToGlow;
	}


	private Map<Integer, Float> calculateAnimationDelay(List<Integer> newSequence, float animationDelay) {
		Map<Integer, Float> animationDelayMap = new HashMap<Integer, Float>();
		float currentDelay = 1.0f;
		for (Integer windowId : newSequence) {
			animationDelayMap.put(windowId, currentDelay);
			currentDelay += animationDelay;
		}
		return animationDelayMap;
	}


	public int getTotalWindows() {
		if (null != idToWindowActorsMap)
			return idToWindowActorsMap.size();
		return 0;
	}
}
