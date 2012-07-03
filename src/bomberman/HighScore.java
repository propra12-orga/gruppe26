package bomberman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bomberman.gui.StdDraw;

/**
 * Implements a Highscore System The faster you are the higher will be your
 * score
 */
public class HighScore {

	/**
	 * Integer-Set of Score-Instances Sets can not contain more than one
	 * identical element.
	 */
	private Set<Integer> instanceScores;

	/**
	 * Map of Player-Instances Keys: Integer Values: String
	 */
	private Map<Integer, String> instancePlayer;

	/**
	 * Hash-Set(Integer) of scores (static)
	 * 
	 */
	private static Set<Integer> scores = new HashSet<Integer>();

	/**
	 * Hash-Map(Integer:String) of players (static)
	 */
	private static Map<Integer, String> player = new HashMap<Integer, String>();

	/**
	 * Constructor for Highscores.
	 */
	public HighScore() {
		instanceScores = new HashSet<Integer>();
		instancePlayer = new HashMap<Integer, String>();
	}

	/**
	 * copy score into a dynamic instance of scoreobject
	 */
	public void copyFromInstance() {
		scores = instanceScores;
		player = instancePlayer;
	}

	/**
	 * Copies the refrence of scoreobject to a static variable to be printable
	 * through xstream
	 */
	public void copyToInstance() {
		instanceScores = scores;
		instancePlayer = player;
	}

	/**
	 * Adds a score to the Highscoreset checks whether minimum highscore is
	 * below score and if yes adds it to the set together with playername also
	 * deletes older highscores
	 * 
	 * @param score
	 *            - score to add
	 * @return whether player scored a new highscore
	 */
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

	/**
	 * Get the Minimum Score of a Highscore-Set
	 * 
	 * @param set
	 *            - Set from which the Minimum should be obtained
	 * @return (Integer) the Minimum Score
	 */
	private static Integer getMinimum(final Set<Integer> set) {
		Integer minimumInteger = Integer.MAX_VALUE;
		for (Integer score : set) {
			if (score.intValue() < minimumInteger.intValue()) {
				minimumInteger = score;
			}
		}
		return minimumInteger;
	}

	/**
	 * Generate score out of ticks
	 * 
	 * @param numberOfTicks
	 *            - Number of Ticks since gamestart
	 * @return (int) Score
	 */
	public static int generateScore(final long numberOfTicks) {
		final int score = (int) (1000000 - numberOfTicks * 423);
		return score < 0 ? 0 : score > 1000000 ? -1 : score;
	}

	private final static double back_size_X = 60;
	private final static double back_size_Y = 20;
	private final static double text_x_back = 500;
	private final static double text_y_back = 100;

	/**
	 * Print Highscore List
	 */
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

	/**
	 * Is Mouse over Back-Button?
	 * 
	 * @return true or false
	 */
	private static boolean mouseOverBack() {
		return (StdDraw.mouseX() <= text_x_back + back_size_X)
				&& (StdDraw.mouseX() >= text_x_back - back_size_X)
				&& (StdDraw.mouseY() <= text_y_back + back_size_Y)
				&& (StdDraw.mouseY() >= text_y_back - back_size_Y);
	}
}
