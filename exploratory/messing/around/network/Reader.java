package messing.around.network;

import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public interface Reader {
	public void write(String str);

	public void read(BomberHuman bman, List<Bomb> bombs);
}
