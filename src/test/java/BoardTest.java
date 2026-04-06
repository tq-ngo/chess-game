import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import state.Board;

public class BoardTest {
    Board board;
    @Before
    public void setUpBeforeClass() {
        board = new Board(2, 2);
    }

    @Test
    public void testConstructor(){
        var square = board.getSquare(0, 0);
        assertNotNull(square);
        assertEquals(0, square.getFile());
        assertEquals("Square at (0, 0) should have rank 0", 0, square.getRank());
    }

    @Test
    public void testGetSquare(){
        var square = board.getSquare(-1, -1);
        assertNull(square);
    }
}
