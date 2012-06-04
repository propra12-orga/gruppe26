package messing.around.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bomberman.game.Board;
import bomberman.game.Controls;
import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.Game;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;

import com.sun.media.sound.InvalidFormatException;

/*
 *  accepted file format:
 *  
 *  # bomferman level file
 *  spawn 0 0 // 0 0 is the only allowed value atm
 *  exit x y
 *  board
 *  i i i i i i ... i 
 *  i i i i i i ... i
 *  i i i i i i ... i
 *  .
 *  .					// this has to be an n x m matrix
 *  .					// i : {0, 1, 2}
 *  i i i i i i ... i
 *  
 */

/**
 * @author jessica
 * @author philipp
 * 
 */

public class FileReader {

	private final List<String> file;
	private boolean valid = true;

	public static void main(String args[]) {
		FileReader fr = new FileReader();

		fr.playReadLevel();
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
			} catch (InvalidFormatException e) {
				this.valid = false;
			}
		}
	}

	public void playReadLevel() {
		if (!valid) {
			System.out.println("INVALID level file");
			return;
		}
		final Game g = parse();
		g.start();
	}

	private Game parse() {
		// Breite des Spielfeldes
		final int dimM = file.get(4).split(" ").length;
		final int len = file.size();

		// Koordinaten des Ausgangs
		final int exitX = Integer.parseInt(file.get(2).split(" ")[1]);
		final int exitY = Integer.parseInt(file.get(2).split(" ")[2]);

		// Feld: Listenlänge - 4, da Spielfeld erst später beginnt
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
		final Board b = new Board(field);
		final int TILESIZE = 50;
		final Controls c = new Controls(b, TILESIZE);
		final Exit e = new Exit(exitX, exitY, TILESIZE);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(field,
				TILESIZE);

		return new Game(c, e, eac, new GameGui(field, TILESIZE, eac));
	}

	private List<String> readFile() throws IOException {
		final List<String> list = new ArrayList<String>();
		FileInputStream fstream = new FileInputStream(
				"exploratory/messing/around/file/level.txt");

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

	private void checkValid() throws InvalidFormatException {
		assertHelper(file.size() > 4);
		assertHelper(file.get(0).equals("# bomferman level file"));
		assertHelper(file.get(1).equals("spawn 0 0"));

		assertHelper(file.get(2).startsWith("exit "));
		String[] arr = file.get(2).split(" ");
		assertHelper(arr.length == 3);

		try {
			if (Integer.parseInt(arr[1]) < 0 || Integer.parseInt(arr[2]) < 0)
				throw new InvalidFormatException();
		} catch (Exception e) {
			throw new InvalidFormatException();
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
					throw new InvalidFormatException();
				}
			}
		}
	}

	private void assertHelper(final boolean in) throws InvalidFormatException {
		if (!in)
			throw new InvalidFormatException();
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
