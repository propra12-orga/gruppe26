package bomberman.game;

import bomberman.game.objects.Bomb
import spock.lang.Specification
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


class GameTest extends Specification {

	def Game g;

	def setup() {
		g = new Game(5, 10)
	}

	def "the game object is constructed properly"() {
		expect:
		g.board.getHeight() == 5;
		g.board.getWidth() == 11;
		g.alive == true;
		g.won == false;
		assertNotNull(g.eac)
		assertNotNull(g.bman)
		assertNotNull(g.exit)
		assertNotNull(g.gui)
		assertNotNull(g.controls)
	}

	def "bombs explode in chain reaction"() {
		when:
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
		def a = new Bomb(10, 10, 1)
		def b = new Bomb(500, 500, 5000)
		a.tick()
		g.bombs.add(a)
		g.bombs.add(b)
		g.manageBombs()

		then:
		b.getTimer() == 4999
	}
}
