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
		final Board board = new Board(7, 7);
		int[][] expected = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 } };
		assertArrayEquals(expected, board.getField());
	}

	@Test
	public void testCorrectDimensionsOfBoard() {
		final Board board = new Board(5, 7);
		int[][] expected = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 } };
		assertArrayEquals(expected, board.getField());
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
