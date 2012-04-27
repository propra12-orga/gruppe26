package bomberman.game.objects;

public class Bomb {
	/*
	 * TODO: do we want to finalize them? thinking of pushing/kicking bombs...
	 */
	final private int posX;
	final private int posY;
	private int timer;
	private boolean exists = true;

	public Bomb(final int posX, final int posY, final int timer) {
		this.posX = posX;
		this.posY = posY;
		this.timer = timer;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public void tick(final int countdown) {
		timer -= countdown;
		if (timer < 0)
			explode();
	}
	
	public void explode() {
		exists = false;
	}
	
	public boolean isStillThere() {
		return exists;
	}
}
