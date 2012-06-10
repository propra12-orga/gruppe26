package bomberman.game;

import bomberman.game.objects.Bomb
import bomberman.game.objects.Exit
import bomberman.gui.GameGui

import spock.lang.Ignore;
import spock.lang.Specification
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Ignore
class GameTest extends Specification {

	def Game g
	def exit

	def setup() {
		final int TILESIZE = 50;

		final Board b = new Board(15, 30);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(
				b.getField(), TILESIZE);

		exit = mock(Exit.class)
		def c = mock(Controls.class)
		def gui = mock(GameGui.class)

		g = new Game(c, exit, eac, gui)
	}

	def "the game object is constructed properly"() {
		expect:
		g.alive == true;
		g.won == false;
		assertNotNull(g.eac)
		assertNotNull(g.bman)
		assertNotNull(g.exit) // mocked
		assertNotNull(g.gui) // this is mocked
		assertNotNull(g.controls) // also this is mocked
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

	def "bombs are not deleted from List object while in explosion status"() {
		when:
		def b = new Bomb(0, 0, 1)
		b.tick()
		g.bombs.add(b);
		g.manageBombs();

		then:
		g.bombs.size() == 1;
		g.bombs.get(0).getTimer() == -1;
		g.bombs.get(0).isCurrentlyExploding() == true;
	}

	def "bombs are deleted from List if completely exploded"() {
		when:
		def b = new Bomb(0, 0, 1)
		b.timer = -49
		g.bombs.add(b)
		g.manageBombs()

		then:
		b.timer == -50
		g.bombs.size() == 0
	}

	def "player is able to win the game"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX() - 10)
		when(exit.getPosY()).thenReturn(g.bman.getPosY() - 10)
		g.checkWin()

		then:
		g.won == true
	}

	def "player does not win if bman is too far away horizontally"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX() - 21)
		when(exit.getPosY()).thenReturn(g.bman.getPosY())
		g.checkWin()

		then:
		g.won == false
	}

	def "player does not win if bman is too far away horizontally 2"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX() + 21)
		when(exit.getPosY()).thenReturn(g.bman.getPosY())
		g.checkWin()

		then:
		g.won == false
	}

	def "player does not win if bman is too far away vertically"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX())
		when(exit.getPosY()).thenReturn(g.bman.getPosY() + 21)
		g.checkWin()

		then:
		g.won == false
	}

	def "player does not win if bman is too far away vertically 2"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX())
		when(exit.getPosY()).thenReturn(g.bman.getPosY() - 21)
		g.checkWin()

		then:
		g.won == false
	}

	def "player wins if bman stand exactly on exit"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX())
		when(exit.getPosY()).thenReturn(g.bman.getPosY())
		g.checkWin()

		then:
		g.won == true
	}

	def "player wins on edge case"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX() + 19)
		when(exit.getPosY()).thenReturn(g.bman.getPosY() - 19)
		g.checkWin()

		then:
		g.won == true
	}

	def "player does not win on edge case"() {
		when:
		when(exit.getPosX()).thenReturn(g.bman.getPosX() + 20)
		when(exit.getPosY()).thenReturn(g.bman.getPosY() - 19)
		g.checkWin()

		then:
		g.won == false
	}

	def "player does not win if enemies exist"() {
		when:
		g.enemies.add(mock(Object.class))
		g.checkWin()
		when(exit.getPosX()).thenReturn(g.bman.getPosX())
		when(exit.getPosY()).thenReturn(g.bman.getPosY())

		then:
		g.enemies.size() == 1
		g.won == false
	}
}
