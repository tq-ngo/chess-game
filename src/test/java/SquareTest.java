import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pieces.Pawn;
import pieces.Piece;
import state.Square;
import util.Color;

public class SquareTest {
    private Square square;
    private static int testCount;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Code to run once before all tests
        testCount = 0;
        System.out.println("Starting Square tests...");
    }

    @Before
    public void setUp() {
        // Code to run before each test
        testCount++;
        square = new Square(3, 4);
        System.out.println("Test " + testCount + " setup complete - Square(3, 4) created");
    }

    @Test
    public void testSquareConstructor() {
        assertEquals("File should be 3", 3, square.getFile());
        assertEquals("Rank should be 4", 4, square.getRank());
        assertNull("Piece should be null initially", square.getPiece());
    }

    @Test
    public void testGetFile() {
        Square square2 = new Square(0, 0);
        assertEquals("File should be 0", 0, square2.getFile());
        
        Square square3 = new Square(7, 7);
        assertEquals("File should be 7", 7, square3.getFile());
    }

    @Test
    public void testGetRank() {
        Square square2 = new Square(0, 0);
        assertEquals("Rank should be 0", 0, square2.getRank());
        
        Square square3 = new Square(7, 7);
        assertEquals("Rank should be 7", 7, square3.getRank());
    }

    @Test
    public void testGetPieceInitiallyNull() {
        assertNull("Piece should be null initially", square.getPiece());
    }

    @Test
    public void testSetPieceAndGetPiece() {
        Piece pawn = new Pawn(Color.WHITE);
        square.setPiece(pawn);
        
        assertNotNull("Piece should not be null after setting", square.getPiece());
        assertEquals("Piece should be the pawn we set", pawn, square.getPiece());
    }

    @Test
    public void testSetPieceMultipleTimes() {
        Piece pawn1 = new Pawn(Color.WHITE);
        Piece pawn2 = new Pawn(Color.BLACK);
        
        square.setPiece(pawn1);
        assertEquals("Piece should be pawn1", pawn1, square.getPiece());
        
        square.setPiece(pawn2);
        assertEquals("Piece should be replaced with pawn2", pawn2, square.getPiece());
    }

    @Test
    public void testSetPieceToNull() {
        Piece pawn = new Pawn(Color.WHITE);
        square.setPiece(pawn);
        assertNotNull("Piece should be set", square.getPiece());
        
        square.setPiece(null);
        assertNull("Piece should be null after setting to null", square.getPiece());
    }
}
