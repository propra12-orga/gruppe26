package bomberman.game.objects;

public class Bomb {
	/*
	 * TODO: do we want to finalize them? thinking of pushing/kicking bombs...
	 */
	final private int posX;
	final private int posY;

	public Bomb(final int posX, final int posY) {
		this.posX = posX;
		this.posY = posY;
	}
}
