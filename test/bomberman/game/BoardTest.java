package bomberman.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testConstructingBoard() {
		Board board = new Board(10, 15);
		assertEquals(11, board.getHeight());
		assertEquals(15, board.getWidth());
	}

	@Test
	public void testInitializeField() {
		final Board board = new Board(7, 7);
		int[][] expected = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 } };
		assertArrayEquals(expected, board.getField());
	}

}
