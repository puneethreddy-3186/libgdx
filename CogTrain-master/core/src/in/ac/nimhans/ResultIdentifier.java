package in.ac.nimhans;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Puneeth Reddy on 9/19/2017.
 Initial version 
 */

public class ResultIdentifier {

	private List<Integer> selectedWindowIds = new ArrayList<Integer>();
	private List<Integer> generatedSequence = new ArrayList<Integer>();


	public void addSelectedWindow(Integer windowId) {
		selectedWindowIds.add(windowId);
	}


	public void removeSelectedWindow(Integer windowId) {
		selectedWindowIds.remove(windowId);
	}


	public void resetSelectedSequence() {
		selectedWindowIds.clear();
	}


	public boolean calculateResult() {
		Gdx.app.log("CogTrain", "selectedSequence " + selectedWindowIds.toString());
		return generatedSequence.equals(selectedWindowIds);
	}


	public ResultIdentifier setGeneratedSequence(List<Integer> generatedSequence) {
		this.generatedSequence = generatedSequence;
		Gdx.app.log("CogTrain", "generatedSequence " + generatedSequence.toString());
		return this;
	}


	public List<Integer> getGeneratedSequence() {
		return generatedSequence;
	}
}
