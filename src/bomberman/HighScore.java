package bomberman;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HighScore {
	private Set<Integer> instanceScores;
	private Map<Integer, String> instancePlayer;
	private static Set<Integer> scores = new HashSet<Integer>();
	private static Map<Integer, String> player = new HashMap<Integer, String>();

	public HighScore() {
		instanceScores = new HashSet<Integer>();
		instancePlayer = new HashMap<Integer, String>();
	}

	public void copyFromInstance() {
		scores = instanceScores;
		player = instancePlayer;
	}

	public void copyToInstance() {
		instanceScores = scores;
		instancePlayer = player;
	}

	public static boolean newScore(final int score) {
		if (scores.size() >= 10) {
			final Integer min = getMinimum();
			if (min.intValue() > score)
				return false;
			scores.remove(min);
		}
		scores.add(score);
		player.put(score, "unknown Bomferman");
		return true;

	}

	private static Integer getMinimum() {
		Integer minimumInteger = Integer.MAX_VALUE;
		for (Integer score : scores) {
			if (score.intValue() < minimumInteger.intValue()) {
				minimumInteger = score;
			}
		}
		return minimumInteger;
	}

	public static int generateScore(final long numberOfTicks) {
		return (int) (1000000 - numberOfTicks * 423);
	}

	public static void printScore() {
		for (Integer score : scores) {
			System.out.println(score.intValue());
		}

	}

}
