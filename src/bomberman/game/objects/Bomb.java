package bomberman.game.objects;

public class Bomb {
	/*
	 * TODO: do we want to finalize them? thinking of pushing/kicking bombs...
	 */
	final private int posX;
	final private int posY;
	final private int maxTimer;
	private int timer;
	private boolean exists = true;

	public Bomb(final int posX, final int posY, final int timer) {
		if (timer <= 0 || posX < 0 || posY < 0)
			throw new IllegalArgumentException("setting detonator failed");
		this.posX = posX;
		this.posY = posY;
		this.timer = timer;
		this.maxTimer = timer;
	}

	public int getMaxTimer() {
		return maxTimer;
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

	public void goBomf() {
		timer = (timer > 20) ? 20 : timer;

	}

	public void tick() {
		timer--;
		if (timer <= 0)
			explode();
	}

	public void explode() {
		exists = false;
	}

	public boolean isStillThere() {
		return exists;
	}

	public boolean isCurrentlyExploding() {
		return (timer <= 0 && timer > -50);
	}
}
