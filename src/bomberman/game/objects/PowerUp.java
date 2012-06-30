package bomberman.game.objects;


public class PowerUp {

	private final EPowerUps type;

	public PowerUp(final EPowerUps type) {
		this.type = type;
	}

	public EPowerUps getType() {
		return type;
	}

}
