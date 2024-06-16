package in.ac.nimhans;

import com.badlogic.gdx.utils.Timer;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Puneeth Reddy on 3/29/2018.
 Initial version 
 */

public class LevelStatisticsManager {

	private int lvlCompletionTime_s;
	private int totalAttempts;
	private int unsuccessfulAttempts;
	private int unsuccessfulSequences;
	private int sameSeqAttemptCount;

	private Timer timer;


	public void startTimer() {
		timer = new Timer();
		Timer.Task task = new Timer.Task() {
			@Override public void run() {
				lvlCompletionTime_s += 5;
			}
		};
		timer.scheduleTask(task, 0, 5);
		timer.start();
	}


	public void stopTimer() {
		if (null != timer) {
			timer.stop();
			timer = null;
		}
	}


	public String getSuccessfulRateMsg() {
		return "You were successful in (" + (totalAttempts - unsuccessfulAttempts) + "/" + totalAttempts + ") attempts";
	}


	public String getLvlCompletionTimeMsg() {
		return String.format(Locale.getDefault(), "%02d min, %02d sec",
				TimeUnit.SECONDS.toMinutes(lvlCompletionTime_s),
				lvlCompletionTime_s - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(lvlCompletionTime_s))
		);
	}


	public String getUnsuccessfulSeqMsg() {
		return (unsuccessfulSequences == 0) ? "Perfect!! You followed all the sequences" : "You couldn't follow " + unsuccessfulSequences + " sequence(s)";
	}


	public void incTotalAttempts() {
		totalAttempts++;
	}


	public void incUnsuccessfulAttempts() {
		unsuccessfulAttempts++;
	}


	public void incUnsuccessfulSeq() {
		unsuccessfulSequences++;
	}


	public void incSameSeqAttempt() {
		sameSeqAttemptCount++;
	}


	public void resetSameSeqAttempt() {
		sameSeqAttemptCount = 0;
	}


	public int getSameSeqAttemptCount() {
		return sameSeqAttemptCount;
	}

}
