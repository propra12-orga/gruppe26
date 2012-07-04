package bomberman.game.network;

import java.util.List;

import bomberman.game.Level;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

/**
 * Interface zur Netzwerkkommunikation
 * 
 * <pre>
 * Netzwerkprotokoll:
 * Server akzeptiert Verbindung.
 * Server schreibt das Level.toString() ins Netzwerk, gefolgt von "poison".
 * Client liest und antwortet mit "ack".
 * 
 * Game loop beginnt hier.
 * Server und Client schreiben je zwei Zeilen ins Netzwerk:
 * 1. mv X Y
 * 	wobei X die X- und Y die Y-Koordinate des Bomferman des jeweiligen Spielers ist.
 * 2. bomb Z
 * 	wobei Z entweder 0 oder 1 ist. 0 bedeutet, es wurde keine Bombe im Tick gelegt, 1 bedeutet, es wurde eine gelegt.
 * Server und Client lesen die Informationen.
 * Game loop endet hier.
 * Zuletzt schreiben beide Seiten noch einmal die beiden Zeilen, wie im Game loop ins Netzwerk, um sicherzustellen, dass auch der letzte Tick synchronisiert wird.
 * </pre>
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
	 * @return gelesenes Level
	 */
	Level readLevel();
}
