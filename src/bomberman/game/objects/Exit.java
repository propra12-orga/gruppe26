package bomberman.game.objects;

public class Exit {

	private final int posX;
	private final int posY;
	private final int TILESIZE;

	public Exit(final int arrayPosX, final int arrayPosY, final int TILESIZE) {
		this.posX = arrayPosX;
		this.posY = arrayPosY;
		this.TILESIZE = TILESIZE;
	}

	public int getPosX() {
		return posX * TILESIZE + TILESIZE / 2;
	}

	public int getPosY() {
		return posY * TILESIZE + TILESIZE / 2;
	}

	public int getArrayPosX() {
		return posX;
	}

	public int getArrayPosY() {
		return posY;
	}

}
