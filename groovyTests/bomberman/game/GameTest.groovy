package bomberman.game;

import bomberman.game.objects.Bomb
import bomberman.game.objects.Exit
import spock.lang.Specification
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


class GameTest extends Specification {

	def Game g;

	def setup() {

		final int TILESIZE = 50;

		final Board b = new Board(15, 30);
		final Controls controls = new Controls(b, TILESIZE);
		final Exit exit = new Exit(21, 10, TILESIZE);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(
				b.getField(), TILESIZE);

		g = new Game(b, controls, exit, eac, null)
	}

	def "the game object is constructed properly"() {
		expect:
		g.alive == true;
		g.won == false;
		assertNotNull(g.eac)
		assertNotNull(g.bman)
		assertNotNull(g.exit)
		assertNull(g.gui) // let's not care about gui
		assertNotNull(g.controls)
	}

	def "bombs explode in chain reaction"() {
		when:
		// let's move bomberman away first to avoid having a gui
		g.bman.posX = 500;
		def a = new Bomb(10, 10, 1)
		def b = new Bomb(10, 10, 5000)
		a.tick()
		g.bombs.add(a)
		g.bombs.add(b)
		g.manageBombs()

		then:
		b.getTimer() == 20
	}

	def "bombs don't explode in chain reaction if they are not in explosion range"() {
		when:
		def a = new Bomb(10, 10, 5000)
		def b = new Bomb(500, 500, 1)
		b.tick()
		g.bombs.add(a)
		g.bombs.add(b)
		g.manageBombs()

		then:
		a.getTimer() == 4999
	}
}
