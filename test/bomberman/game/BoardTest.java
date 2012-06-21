package bomberman.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testConstructingBoardWithEvenParameters() {
		Board board = new Board(10, 14);
		assertEquals(11, board.getHeight());
		assertEquals(15, board.getWidth());
	}

	@Test
	public void testConstructingBoardWithOddParameters() {
		Board board = new Board(13, 17);
		assertEquals(13, board.getHeight());
		assertEquals(17, board.getWidth());
	}

	@Test
	public void testInitializeField() {
		// Warning: randomization
		final Board board = new Board(7, 7);
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				if (i % 2 == 1 && j % 2 == 1) {
					assertTrue(board.getField()[i][j] == 1);
				} else {
					assertTrue(board.getField()[i][j] == 0
							|| board.getField()[i][j] == 2);
				}
			}
		}
		assertEquals(board.getField()[0][0], 0);
		assertEquals(board.getField()[1][0], 0);
		assertEquals(board.getField()[0][1], 0);
	}

	@Test
	public void testCorrectDimensionsOfBoard() {
		final Board board = new Board(5, 7);
		assertEquals(board.getHeight(), 5);
		assertEquals(board.getWidth(), 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionOnConstruct() {
		new Board(1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionOnConstruct2() {
		new Board(-1, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionOnConstruct3() {
		new Board(8, -1);
	}

}
