package bomberman.game.character;

import java.util.List;

import bomberman.game.Network;
import bomberman.game.Settings;
import bomberman.game.objects.Bomb;

/**
 * Human Player Bomferman Bomfermanklasse, welche Methoden fuer Bewegung und
 * Netzwerkanbindung bereit stellt.
 */
public class BomberHuman {
	/**
	 * unused
	 */
	private final boolean human;
	/**
	 * Bomferman's current x-position in pixels.
	 */
	private int posX;
	/**
	 * Bomferman's current y-position in pixels.
	 */
	private int posY;
	/**
	 * Bomferman's current speed.
	 */
	private int speed = Settings.BMANSPEED;
	/**
	 * Network object. May not be initialized.
	 */
	private Network nw;
	/**
	 * String containing Bomferman's position that will be send via the Network
	 * object.
	 */
	private String move = "mv 25 75";
	/**
	 * String containing whether a bomb was dropped that will be send via the
	 * Network object.
	 */
	private String bomb = "bomb 0";

	/**
	 * Konstruktor fuer den Einzelspielermodus.
	 * 
	 * @param human
	 *            Flag, ob Bomferman menschlich ist (wird ignoriert).
	 * @param posX
	 *            Startposition auf der X-Achse (> Kollisionsbox nach links).
	 * @param posY
	 *            Startposition auf der Y-Achse (> Kollisionsbox nach unten).
	 **/
	public BomberHuman(final boolean human, final int posX, final int posY) {
		if (posX < Math.abs(Settings.collisionLeftOffset)
				|| posY < Math.abs(Settings.collisionBottomOffset))
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomferhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Konstruktor fuer den Mehrspielermodus!
	 * 
	 * @param posX
	 *            Startposition auf der X-Achse (> Kollisionsbox nach links).
	 * @param posY
	 *            Startposition auf der Y-Achse (> Kollisionsbox nach unten).
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
	 * Bomferman anzugeben. network only
	 */
	public void addMove() {
		move = "mv " + posX + " " + posY;
	}

	/**
	 * Schreibt in das Feld "bomb" Netzwerkbefehl die Angabe, ob eine Bombe
	 * gesetzt wurde, oder nicht. network only
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
	 * Bekommt die Geschwindigkeit des Bomferman angegeben.
	 * 
	 * @return Gibt die Geschwindigkeit des Bomferman zurueck.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Liest fuer den Mitspieler die neue Position ein. network only
	 * 
	 * @param bombs
	 *            aktualisiert die Liste der Bomben im Spiel, falls eine Bombe
	 *            vom Gegner gelegt wurde.
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
	 * Gibt die x-Koordinate des Bomferman zurueck.
	 * 
	 * @return Bomfermans x-Koordinate.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Gibt die y-Koordinate des Bomferman zurueck.
	 * 
	 * @return Bomfermans y-Koordinate
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Aendert die x-Koordinate bei Bewegung nach rechts.
	 */
	public void moveRight() {
		posX += speed;
	}

	/**
	 * Aendert die x-Koordinate bei Bewegung nach links.
	 */
	public void moveLeft() {
		posX -= speed;
	}

	/**
	 * Aendert die y-Koordinate bei Bewegung nach oben. move up (higher posY by
	 * speed)
	 */
	public void moveUp() {
		posY += speed;
	}

	/**
	 * Aendert die y-Koordinate bei Bewegung nach unten.
	 */
	public void moveDown() {
		posY -= speed;
	}

	/**
	 * Horizontale Bewegung
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
	 * wird derzeit nicht gebraucht
	 * 
	 * @return human field
	 */
	@Deprecated
	public boolean isHuman() {
		return human;
	}

	/**
	 * Uebergibt in einen String die neuen x- und y-Koordinaten des eigenen
	 * Bomfermans. network only
	 * 
	 * @return Bomfermans Bewegung
	 */
	public String getMove() {
		return move;
	}

	/**
	 * Uebergibt in einen String, ob eine Bombe gelegt wurde, oder nicht.
	 * network only
	 * 
	 * @return "bomb 0", wenn keine Bombe gelegt wurde, "bomb 1", wenn eine
	 *         gelegt wurde.
	 */
	public String getBomb() {
		return bomb;
	}

	/**
	 * Setter fuer die x-Koordinate des Gegenspielers. network only
	 * 
	 * @param posX
	 *            x-Koordinate des Gegenspielers.
	 */
	public void setPosX(final int posX) {
		this.posX = posX;
	}

	/**
	 * Setter fuer die y-Koordinate des Gegenspielers. network only
	 * 
	 * @param posY
	 *            y-Koordinate des Gegenspielers.
	 */
	public void setPosY(final int posY) {
		this.posY = posY;
	}

}
