package bomberman.game;

import static org.junit.Assert.*;
import bomberman.game.character.BomberHuman
import spock.lang.Specification

class ExtendedControlsTest extends Specification {
	def b5
	def b3
	def c3
	def c5
	def bman

	def setup() {
		b3 = new Board([
			[0, 0, 0],
			[0, 1, 0],
			[0, 2, 2]]
		as int[][])
		b5 = new Board([
			[0, 0, 2, 0, 2],
			[0, 1, 0, 1, 0],
			[0, 0, 2, 0, 2],
			[2, 1, 0, 1, 0],
			[0, 0, 0, 2, 0]]
		as int[][])
		c3 = new Controls(b3, 50)
		c5 = new Controls(b5, 50)
		bman = new BomberHuman(true, 25, 25)
	}

	def "board is correctly set up"() {
		when:
		def b = b3.getField()
		then:
		b[2][0] == 0
		b[2][1] == 2
		b[0][1] == 0
	}

	def "bomberman cannot move into destructible wall"() {
		when:
		bman.setPosX(39)
		bman.setPosY(125)
		then:
		bman.posX == 39
		c3.getArrayPos(bman.posX) == 0
		c3.getArrayPos(bman.posY) == 2
		c3.canMoveThere('d' as char, bman) == false
		c3.canMoveThere('a' as char, bman) == true
		c3.canMoveThere('s' as char, bman) == true
		c3.canMoveThere('w' as char, bman) == true
	}

	def "bomberman cannot move against corner in y direction" () {
		when:
		bman.setPosX(40)
		bman.setPosY(40)
		bman.boostSpeed(10)
		then:
		c3.canMoveThere('w' as char, bman) == false
		c3.canMoveThere('a' as char, bman) == true
		c3.canMoveThere('s' as char, bman) == true
		c3.canMoveThere('d' as char, bman) == true
	}
}
