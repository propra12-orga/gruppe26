package bomberman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bomberman.gui.StdDraw;

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
			final Integer min = getMinimum(scores);
			if (min.intValue() > score)
				return false;
			scores.remove(min);
		}
		scores.add(score);
		String playername = System.getProperty("user.name");
		if (playername == null)
			playername = "unknown Bomferman";
		player.put(score, playername);
		return true;

	}

	private static Integer getMinimum(final Set<Integer> set) {
		Integer minimumInteger = Integer.MAX_VALUE;
		for (Integer score : set) {
			if (score.intValue() < minimumInteger.intValue()) {
				minimumInteger = score;
			}
		}
		return minimumInteger;
	}

	public static int generateScore(final long numberOfTicks) {
		final int score = (int) (1000000 - numberOfTicks * 423);
		return score < 0 ? 0 : score > 1000000 ? -1 : score;
	}

	final static double back_size_X = 60;
	final static double back_size_Y = 20;
	final static double text_x_back = 500;
	final static double text_y_back = 100;

	public static void printScore() {
		StdDraw.resetMousePressedStatus();
		boolean back = false;

		Set<Integer> scoreCopy = new HashSet<Integer>();
		for (Integer score : scores) {
			scoreCopy.add(score);
		}

		final int len = scoreCopy.size();
		final List<Integer> selectionSorted = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			final Integer min = getMinimum(scoreCopy);
			scoreCopy.remove(min);
			selectionSorted.add(0, min);
		}

		while (!back) {
			int count = 1;

			StdDraw.clear();
			StdDraw.text(500, 900, "High Score");

			for (Integer sorted : selectionSorted) {
				StdDraw.text(
						500,
						840 - 60 * count,
						count + ". " + player.get(sorted) + ": "
								+ sorted.intValue());
				count++;
			}

			if (mouseOverBack()) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			}
			StdDraw.text(text_x_back, text_y_back, "back");
			if (mouseOverBack()) {
				StdDraw.setPenColor(StdDraw.BLACK);
			}

			if (mouseOverBack() && StdDraw.mousePressed()) {
				back = true;
			}
			StdDraw.show();
		}
	}

	private static boolean mouseOverBack() {
		return (StdDraw.mouseX() <= text_x_back + back_size_X)
				&& (StdDraw.mouseX() >= text_x_back - back_size_X)
				&& (StdDraw.mouseY() <= text_y_back + back_size_Y)
				&& (StdDraw.mouseY() >= text_y_back - back_size_Y);
	}
}
