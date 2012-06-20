package bomberman.game.character;

import java.util.List;

import bomberman.game.Network;
import bomberman.game.Settings;
import bomberman.game.objects.Bomb;

/**
 * Human Player Bomferman
 * Bomfermanklasse, welche Methoden fuer Bewegung und Netzwerkanbindung bereit
 * stellt.
 */
public class BomberHuman {
	private final boolean human;
	private int posX;
	private int posY;
	private int speed = 1;
	private Network nw;
	private String move = "mv 25 75";
	private String bomb = "bomb 0";

	/**
<<<<<<< HEAD
	 * Konstruktor fuer den Einzelspielermodus.
	 * 
	 * @param human
	 *            Flag, ob Bomferman menschlich ist (wird ignoriert).
	 * @param posX
	 *            Startposition auf der X-Achse (> Kollisionsbox nach links).
	 * @param posY
	 *            Startposition auf der Y-Achse (> Kollisionsbox nach unten).
=======
	 * Constructs the local Bomferman at posX posY (Singleplayer)
	 * 
	 * @param human
	 *            - True: Controlled by Human Bomferman
	 * @param posX
	 *            - XPosition of Bomferman
	 * @param posY
	 *            - YPosition of Bomferman
>>>>>>> j to the ava d to the oc
	 */
	public BomberHuman(final boolean human, final int posX, final int posY) {
		if (posX <= Math.abs(Settings.collisionLeftOffset)
				|| posY <= Math.abs(Settings.collisionBottomOffset))
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomferhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
	}

	/**
<<<<<<< HEAD
	 * Konstruktor fuer den Mehrspielermodus!
	 * 
	 * @param posX
	 *            Startposition auf der X-Achse (> Kollisionsbox nach links).
	 * @param posY
	 *            Startposition auf der Y-Achse (> Kollisionsbox nach unten).
=======
	 * Constructs the local Bomferman at posX posY (Network-Multiplayer)
	 * 
	 * @param posX
	 *            - XPosition of Bomferman
	 * @param posY
	 *            - YPosition of Bomferman
>>>>>>> j to the ava d to the oc
	 * @param nw
	 *            Network Objekt, ueber das Informationen ausgetauscht werden.
	 * @param server
	 *            wird ignoriert (warum auch immer?).
	 */
	public BomberHuman(final int posX, final int posY, final Network nw,
			final boolean server) {
		this.posX = posX;
		this.posY = posY;
		this.nw = nw;
		this.human = true;
	}

	/**
	 * Schreibt in das Feld "move" den Netzwerkbefehl, um die neue Position vom
	 * Bomferman anzugeben.
	 * network only
	 */
	public void addMove() {
		move = "mv " + posX + " " + posY;
	}

	/**
	 * Schreibt in das Feld "bomb" Netzwerkbefehl die Angabe, ob eine Bombe
	 * gesetzt wurde, oder nicht.
	 * network only
	 * 
	 * @param bombDrop
	 *            Ist wahr, wenn eine Bombe im aktuellen Tick gelegt wurde.
	 */
	public void addBombStatus(final boolean bombDrop) {
		if (bombDrop) {
			bomb = "bomb 1";
		} else {
			bomb = "bomb 0";
		}
	}

	/**
<<<<<<< HEAD
	 * Bekommt die Geschwindigkeit des Bomferman angegeben.
	 * 
	 * @return
	 *         Gibt die Geschwindigkeit des Bomferman zurueck.
=======
	 * get speed of bomferman
	 * 
	 * @return [int] speed
>>>>>>> j to the ava d to the oc
	 */
	public int getSpeed() {
		return speed;
	}

	/**
<<<<<<< HEAD
	 * Liest fuer den Mitspieler die neue Position ein.
	 * network only
	 * 
	 * @param bombs
	 *            aktualisiert die Liste der Bomben im Spiel, falls eine Bombe
	 *            vom Gegner gelegt wurde.
=======
	 * get Moves of Networkplayer
	 * 
	 * @param bombs
	 *            - list containing the bombs
>>>>>>> j to the ava d to the oc
	 */

	public void getNetworkMovement(final List<Bomb> bombs) {
		if (nw == null)
			return;
		nw.read(this, bombs);
	}

	/**
	 * Aendert die aktuelle Geschwindigkeit von Bomferman.
	 * 
	 * @param offset
	 *            Änderung der Geschwindigkeit.
	 */
	public void boostSpeed(final int offset) {
		// TODO: maybe we will use this as inverted controls powerdown.
		// if (speed + offset <= 0)
		// throw new IllegalArgumentException("negative speed is not allowed");
		speed += offset;
	}

	/**
<<<<<<< HEAD
	 * Gibt die x-Koordinate des Bomferman zurueck.
	 * 
	 * @return
	 *         Bomfermans x-Koordinate.
=======
	 * @return [int]posX
>>>>>>> j to the ava d to the oc
	 */
	public int getPosX() {
		return posX;
	}

	/**
<<<<<<< HEAD
	 * Gibt die y-Koordinate des Bomferman zurueck.
	 * 
	 * @return
	 *         Bomfermans y-Koordinate
=======
	 * @return [int]posY
>>>>>>> j to the ava d to the oc
	 */
	public int getPosY() {
		return posY;
	}

	/**
<<<<<<< HEAD
	 * Aendert die x-Koordinate bei Bewegung nach rechts.
=======
	 * move right (higher posY by speed)
>>>>>>> j to the ava d to the oc
	 */
	public void moveRight() {
		posX += speed;
	}

	/**
<<<<<<< HEAD
	 * Aendert die x-Koordinate bei Bewegung nach links.
=======
	 * move left(lower posY by speed)
>>>>>>> j to the ava d to the oc
	 */
	public void moveLeft() {
		posX -= speed;
	}

	/**
<<<<<<< HEAD
	 * Aendert die y-Koordinate bei Bewegung nach oben.
=======
	 * move up (higher posY by speed)
>>>>>>> j to the ava d to the oc
	 */
	public void moveUp() {
		posY += speed;
	}

	/**
<<<<<<< HEAD
	 * Aendert die y-Koordinate bei Bewegung nach unten.
=======
	 * move down (lower posY by speed)
>>>>>>> j to the ava d to the oc
	 */
	public void moveDown() {
		posY -= speed;
	}

	/**
<<<<<<< HEAD
	 * Horizontale Bewegung
=======
	 * move other character by offset
>>>>>>> j to the ava d to the oc
	 * 
	 * @param offset
	 *            Aenderung der x-Koordinate.
	 */
	public void moveHorizontally(final int offset) {
		posX += offset;
	}

	/**
	 * Vertikale Bewegung
	 * 
	 * @param offset
	 *            Aenderung der y-Koordinate.
	 * 
	 */
	public void moveVertically(final int offset) {
		posY += offset;
	}

	/**
<<<<<<< HEAD
	 * wird derzeit nicht gebraucht
	 * 
	 * @return
	 *         human field
=======
	 * @return whether Bomferman is controlled by Human (true or false)
>>>>>>> j to the ava d to the oc
	 */
	@Deprecated
	public boolean isHuman() {
		return human;
	}

	/**
<<<<<<< HEAD
	 * Uebergibt in einen String die neuen x- und y-Koordinaten des eigenen
	 * Bomfermans.
	 * network only
	 * 
	 * @return
	 *         Bomfermans Bewegung
=======
	 * @return String with moves (to be read through network)
>>>>>>> j to the ava d to the oc
	 */
	public String getMove() {
		return move;
	}

	/**
<<<<<<< HEAD
	 * Uebergibt in einen String, ob eine Bombe gelegt wurde, oder nicht.
	 * network only
	 * 
	 * @return
	 *         "bomb 0", wenn keine Bombe gelegt wurde, "bomb 1", wenn eine
	 *         gelegt wurde.
=======
	 * @return String of bombs(to be read through network)
>>>>>>> j to the ava d to the oc
	 */
	public String getBomb() {
		return bomb;
	}

	/**
	 * Setter fuer die x-Koordinate des Gegenspielers.
	 * network only
	 * 
	 * @param posX
<<<<<<< HEAD
	 *            x-Koordinate des Gegenspielers.
=======
	 *            - XPosition of Bomferman
>>>>>>> j to the ava d to the oc
	 */
	public void setPosX(final int posX) {
		this.posX = posX;
	}

	/**
	 * Setter fuer die y-Koordinate des Gegenspielers.
	 * network only
	 * 
	 * @param posY
<<<<<<< HEAD
	 *            y-Koordinate des Gegenspielers.
=======
	 *            - YPosition of Bomferman
>>>>>>> j to the ava d to the oc
	 */
	public void setPosY(final int posY) {
		this.posY = posY;
	}

}
