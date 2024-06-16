package in.ac.nimhans.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import in.ac.nimhans.CogTrainGame;

/**
 * Created by Puneeth Reddy on 9/18/2017.
 Initial version 
 */

public abstract class AbstractTableScreen extends AbstractScreen {

	private static final int STAGE_PADDING = 20;
	/**
	 * Common table.
	 */
	private Table table = null;
	private GameInteractionListener gameInteractionListener;


	AbstractTableScreen(CogTrainGame game) {
		super(game);
		setGameInteractionListener(game);
	}


	/**
	 * This method sets up the visual layout for this screen. Child classes
	 * have to override this method and add to the provided table the widgets
	 * they want to show in the screen.
	 *
	 * @param table table that has been assigned to this screen.
	 */
	abstract void setUpInterface(Table table);


	@Override void setUpInterface() {
		if (table == null) {
			table = new Table(getGame().getSkin());
			table.setFillParent(true);
			//table.pad(STAGE_PADDING);
			setUpInterface(table);
			getStage().addActor(table);
		}
		else {
			table.clear();
		}
	}


	@Override public void dispose() {
		super.dispose();
		table = null;
	}


	public void setScreenId(int screenId) {
		if (this.gameInteractionListener != null)
			this.gameInteractionListener.onGameScreenChange(screenId);
	}


	public AbstractTableScreen setGameInteractionListener(GameInteractionListener gameInteractionListener) {
		this.gameInteractionListener = gameInteractionListener;
		return this;
	}


	public interface GameInteractionListener {
		void onGameScreenChange(int screenId);
	}

}
