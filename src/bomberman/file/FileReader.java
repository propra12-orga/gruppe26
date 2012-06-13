package bomberman.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bomberman.game.Game;
import bomberman.game.Level;
import bomberman.game.Settings;
import bomberman.game.objects.Exit;

/**
 * Class to read a level from a file, evaluate the validity of a level, parse a
 * valid level file and play parsed level in single player mode.
 * 
 * accepted file format:
 * 
 * <pre>
 * # bomferman level file
 * spawn 0 0 // 0 0 is the only allowed value atm
 * exit x y
 * board
 * i i i i i i ... i
 * i i i i i i ... i
 * i i i i i i ... i
 * .
 * . this has to be an n x m matrix
 * . i : {0, 1, 2}
 * i i i i i i ... i
 * </pre>
 * 
 * @author jessica
 * @author philipp
 * 
 */

public class FileReader {

	/**
	 * List that contains the to-be evaluated/parsed level file.
	 */
	private final List<String> file;
	private boolean valid = true;

	/**
	 * Only way right now to play a level from a file.
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String args[]) {
		FileReader fr = new FileReader();

		fr.playReadLevel();
	}

	/**
	 * Reads a file that is saved in a List of Strings already. Used by network
	 * level communication. Requires a valid level file. Notice that no
	 * guarantees about the valid flag are made.
	 * 
	 * @param in
	 *            StringList (valid level file)
	 */
	public FileReader(List<String> in) {
		file = in;
	}

	/**
	 * Konstruktor nur für LevelTest
	 * 
	 * @param in
	 *            String, den Level.toString() erzeugt
	 */
	@SuppressWarnings("unused")
	private FileReader(String in) {
		file = stringToList(in);
		try {
			checkValid();
		} catch (IllegalArgumentException e) {
			this.valid = false;
		}
	}

	/**
	 * Konstruiert ein FileReader Objekt. Versucht von
	 * "exploratory/messing/around/file/level.txt" zu lesen. Wenn die Datei
	 * existiert, wird der Inhalt automatisch validiert.
	 */

	public FileReader() {
		List<String> file;
		try {
			file = readFile();
		} catch (IOException e) {
			file = null;
			valid = false;
			System.out.println("ERROR reading from file");
		}
		this.file = file;

		if (file != null) {
			try {
				checkValid();
			} catch (IllegalArgumentException e) {
				this.valid = false;
			}
		}
	}

	/**
	 * Creates and starts a Game if read level file is valid.
	 */
	public void playReadLevel() {
		if (!valid) {
			System.out.println("INVALID level file");
			return;
		}
		final Level l = parse();
		final Game g = l.createGameFromLevel();
		g.start();
	}

	/**
	 * Parses a valid read file. No guarantees are made if parse() is called on
	 * an invalid level file.
	 * 
	 * @return Level object
	 * 
	 */
	public Level parse() {
		// Breite des Spielfeldes
		final int dimM = file.get(4).split(" ").length;
		final int len = file.size();

		// Koordinaten des Ausgangs
		final int exitX = Integer.parseInt(file.get(2).split(" ")[1]);
		final int exitY = Integer.parseInt(file.get(2).split(" ")[2]);

		// Feld: Listenlaenge - 4, da Spielfeld erst spaeter beginnt
		final int[][] field = new int[file.size() - 4][dimM];
		String[] split;

		// Board parsen
		for (int i = 0; i < file.size() - 4; i++) {
			split = file.get(len - i - 1).split(" ");
			for (int j = 0; j < dimM; j++) {
				field[i][j] = Integer.parseInt(split[j]);
			}
		}

		// alles zusammenbauen
		final Exit e = new Exit(exitX, exitY, Settings.TILESIZE);
		final Level l = new Level(field, e);

		return l;
	}

	/**
	 * Reads the content of a file (at the moment
	 * src/bomberman/file/level.txt").
	 * 
	 * @return StringList, if file exists
	 * @throws IOException
	 *             if file does not exist
	 */
	private List<String> readFile() throws IOException {
		final List<String> list = new ArrayList<String>();
		FileInputStream fstream = new FileInputStream(
				"src/bomberman/file/level.txt");

		// magic java reading stuff
		final DataInputStream in = new DataInputStream(fstream);
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String tmp;
		// Lese eine Zeile aus der Datei in den String tmp ein.
		// Wenn es noch eine Zeile gab (!=null), dann füge sie der Liste hinzu.
		while ((tmp = br.readLine()) != null)
			list.add(tmp);
		in.close();

		return list;
	}

	/**
	 * Checks validity of a given level saved in the file field.
	 * 
	 * @throws IllegalArgumentException
	 *             if level format is not valid.
	 */
	private void checkValid() {
		assertHelper(file.size() > 4);
		assertHelper(file.get(0).equals("# bomferman level file"));
		assertHelper(file.get(1).equals("spawn 0 0"));

		assertHelper(file.get(2).startsWith("exit "));
		String[] arr = file.get(2).split(" ");
		assertHelper(arr.length == 3);

		try {
			if (Integer.parseInt(arr[1]) < 0 || Integer.parseInt(arr[2]) < 0)
				throw new IllegalArgumentException();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		assertHelper(file.get(3).equals("board"));

		String[] split = file.get(4).split(" ");
		// Breite des Feldes ist Länge der ersten Zeile des Feldes
		final int dimM = split.length;
		assertHelper(dimM > 0);
		// laufe jede Zeile durch
		for (int i = 4; i < file.size(); i++) {

			split = file.get(i).split(" ");
			assertHelper(split.length == dimM);

			// lese jede Zahl von jeder Zeile im Feld ein
			for (int j = 0; j < dimM; j++) {

				try {
					// versuche jede Zahl als int zu parsen
					int m = Integer.parseInt(split[j]);
					// die geparste Zahl darf nur aus dem Intervall [0, 2] sein
					assertHelper(m >= 0);
					assertHelper(m <= 2);

				} catch (Exception e) {
					throw new IllegalArgumentException();
				}
			}
		}

		// Ausgang darf nicht unter Typ-1 Block liegen (unzerstoerbar)
		final int exitx = Integer.parseInt(arr[1]);
		final int exity = Integer.parseInt(arr[2]);

		final String line = file.get(file.size() - exity - 1);
		String[] tmp = line.split(" ");
		final int walltype = Integer.parseInt(tmp[exitx]);
		assertHelper(walltype != 1);

		// spawn 0 0 is not blocked
		final String lastLine = file.get(file.size() - 1);
		tmp = lastLine.split(" ");
		assertHelper(Integer.parseInt(tmp[0]) == 0);
		assertHelper(Integer.parseInt(tmp[1]) == 0);
		tmp = file.get(file.size() - 2).split(" ");
		assertHelper(Integer.parseInt(tmp[0]) == 0);

	}

	/**
	 * Asserts the validity of an expression.
	 * 
	 * @param in
	 *            expression
	 * @throws IllegalArgumentException
	 *             if expression is false
	 */
	private void assertHelper(final boolean in) throws IllegalArgumentException {
		if (!in)
			throw new IllegalArgumentException();
	}

	/**
	 * Creates a List of Strings split on '\n'.
	 * 
	 * @param in
	 *            String containing a level.
	 * @return StringList split on new lines.
	 */
	public List<String> stringToList(final String in) {
		String[] arr = in.split("\n");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}

		return list;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();

		for (String string : file) {
			sb.append(string + "\n");
		}

		return sb.toString();
	}
}
