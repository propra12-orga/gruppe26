package bomberman.game.objects;

public class Exit {

	private final int posX;
	private final int posY;

	public Exit(final int posX, final int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

}
