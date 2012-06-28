package bomberman.game.network;

import java.util.List;

import bomberman.game.Level;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

/**
 * Interface zur Netzwerkkommunikation
 * 
 * @author jessica
 * 
 */
public interface Reader {
	/**
	 * schreibt den String ins Netzwerk.
	 * 
	 * @param str
	 *            zu schreibender String
	 */
	public void write(String str);

	/**
	 * liest aus dem Netzwerk und schreibt in bomferman und gegebenenfalls in
	 * bomben.
	 * 
	 * @param bman
	 *            gegnerischer bomferman
	 * @param bombs
	 *            Liste von Bomben aus dem Spiel.
	 */
	public void read(BomberHuman bman, List<Bomb> bombs);

	/**
	 * liest Leveldatei aus dem Netzwerk
	 * 
	 * @return
	 *         gelesenes Level
	 */
	Level readLevel();
}
