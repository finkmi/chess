package project3;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelTest {

    @Test
    //Tests ChessModel constructor to ensure all pieces are in correct place
    public void testModelConstructor() {
        ChessModel model = new ChessModel();
        assertEquals(model.pieceAt(0,0).type(),"Rook");
        assertEquals(model.pieceAt(0,1).type(),"Knight");
        assertEquals(model.pieceAt(0,2).type(),"Bishop");
        assertEquals(model.pieceAt(0,3).type(),"Queen");
        assertEquals(model.pieceAt(0,4).type(),"King");
        assertEquals(model.pieceAt(0,5).type(),"Bishop");
        assertEquals(model.pieceAt(0,6).type(),"Knight");
        assertEquals(model.pieceAt(0,7).type(),"Rook");

        for(int i = 0; i < 8; i++)
            assertEquals(model.pieceAt(1,i).type(), "Pawn");

        assertEquals(model.pieceAt(7,0).type(),"Rook");
        assertEquals(model.pieceAt(7,1).type(),"Knight");
        assertEquals(model.pieceAt(7,2).type(),"Bishop");
        assertEquals(model.pieceAt(7,3).type(),"Queen");
        assertEquals(model.pieceAt(7,4).type(),"King");
        assertEquals(model.pieceAt(7,5).type(),"Bishop");
        assertEquals(model.pieceAt(7,6).type(),"Knight");
        assertEquals(model.pieceAt(7,7).type(),"Rook");

        for(int i = 0; i < 8; i++)
            assertEquals(model.pieceAt(6,i).type(), "Pawn");

    }

    @Test
    //Testing pieceAt method
    public void testPieceAt() {
        ChessModel model = new ChessModel();
        assertEquals("Rook", model.pieceAt(0,0).type());
    }

    @Test
    //Testing setPiece method
    public void testSetPiece() {
        ChessModel model = new ChessModel();
        assertEquals("Rook", model.pieceAt(0,0).type());
        model.setPiece(0,0, new King(Player.WHITE));
        assertEquals("King", model.pieceAt(0,0).type());
        assertEquals(Player.WHITE, model.pieceAt(0,0).player());
    }

    @Test
    //Tests getNumSaveStates method to see if every move gets saved
    public void testNumSaveStatesGetter() {
        ChessModel model = new ChessModel();
        assertEquals(1, model.getNumSaveStates());

        Move move = new Move(1,0,2,0);
        model.move(move);
        assertEquals(2, model.getNumSaveStates());

    }

    @Test
    //Testing move method to see if spot moved
    // to is now the piece and spot moved from is null
    public void testMovement() {
        ChessModel model = new ChessModel();
        Move move = new Move(1,0,2,0);

        model.move(move);
        assertTrue(model.pieceAt(1,0) == null);
        assertEquals( "Pawn", model.pieceAt(2,0).type());
    }

    @Test
    //Testing Pawn valid move simple move forward one
    public void testPawnValidMove() {
        ChessModel model = new ChessModel();
        Move move = new Move(1,0,2,0);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Pawn valid first move, two forward
    public void testPawnValidFirstMove() {
        ChessModel model = new ChessModel();
        Move move = new Move(1,0,3,0);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Pawn invalid move 3 forward
    public void testPawnInvalidMove1() {
        ChessModel model = new ChessModel();
        Move move = new Move(1,0,4,0);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Pawn invalid move, moving diagonally
    public void testPawnInvalidMove2() {
        ChessModel model = new ChessModel();
        Move move = new Move(1,1,2,0);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Pawn invalid move, moving backwards
    public void testPawnInvalidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        Move move = new Move(5,5,4,5);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Rook valid move, horizontal
    public void testRookValidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,0, new Rook(Player.WHITE));
        Move move = new Move(0,0,0,7);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Rook valid move, vertical
    public void testRookValidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,0, new Rook(Player.WHITE));
        Move move = new Move(0,0,7,0);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Rook valid move, diagonal
    public void testRookInvalidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,0, new Rook(Player.WHITE));
        Move move = new Move(0,0,7,7);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Rook valid move, no piece hopping
    public void testRookInvalidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,0, new Rook(Player.WHITE));
        model.setPiece(0,3, new Pawn(Player.WHITE));
        Move move = new Move(0,0,0,7);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move forward 2 left 1
    public void testKnightValidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,2,3);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move forward 2 right 1
    public void testKnightValidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,2,5);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move forward 1 left 2
    public void testKnightValidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,3,2);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move forward 1 right 2
    public void testKnightValidMove4() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,3,6);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move back 2 left 1
    public void testKnightValidMove5() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,6,3);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move back 2 right 1
    public void testKnightValidMove6() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,6,5);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move back 1 left 2
    public void testKnightValidMove7() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,5,2);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight valid move simple move back 1 right 2
    public void testKnightValidMove8() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,5,6);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Knight invalid move, just forward
    public void testKnightInvalidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,6,4);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Knight invalid move, just sideways
    public void testKnightInvalidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Knight(Player.WHITE));
        Move move = new Move(4,4,4,6);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Bishop valid move, up and left
    public void testBishopValidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,2,2);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Bishop valid move, up and right
    public void testBishopValidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,2,6);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Bishop valid move, down and left
    public void testBishopValidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,6,2);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Bishop valid move, down and right
    public void testBishopValidMove4() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,6,6);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Bishop invalid move, horizontal
    public void testBishopInvalidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,4,2);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Bishop invalid move, vertical
    public void testBishopInvalidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        Move move = new Move(4,4,2,4);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Bishop invalid move, jumping piece
    public void testBishopInvalidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Bishop(Player.WHITE));
        model.setPiece(3,3, new Pawn(Player.WHITE));
        Move move = new Move(4,4,2,2);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Queen valid move, vertical
    public void testQueenValidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Queen(Player.WHITE));
        Move move = new Move(4,4,0,4);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Queen valid move, horizontal
    public void testQueenValidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Queen(Player.WHITE));
        Move move = new Move(4,4,4,0);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Queen valid move, diagonal
    public void testQueenValidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Queen(Player.WHITE));
        Move move = new Move(4,4,6,6);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing Queen invalid move, knight-like movement
    public void testQueenInvalidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Queen(Player.WHITE));
        Move move = new Move(4,4,2,5);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing Queen invalid move, piece jumping
    public void testQueenInvalidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new Queen(Player.WHITE));
        model.setPiece(4,5, new Pawn(Player.WHITE));
        Move move = new Move(4,4,4,7);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove1() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,4,5);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove2() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,4,3);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove3() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,5,4);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove4() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,3,4);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove5() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,3,3);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove6() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,5,5);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove7() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,3,5);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King valid move
    public void testKingValidMove8() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,5,3);

        assertTrue(model.isValidMove(move));
    }

    @Test
    //Testing King invalid move, more than 1 space
    public void testKingInvalidMove() {
        ChessModel model = new ChessModel();
        clearBoard(model);
        model.setPiece(4,4, new King(Player.WHITE));
        Move move = new Move(4,4,6,4);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing the numRows method
    public void testNumRows() {
        ChessModel model = new ChessModel();
        assertEquals(8,model.numRows());
    }

    @Test
    //Testing the numColumn method
    public void testNumCols() {
        ChessModel model = new ChessModel();
        assertEquals(8,model.numColumns());
    }

    @Test
    //Testing the current player method
    public void testCurrentPlayer() {
        ChessModel model = new ChessModel();

        assertEquals(Player.WHITE, model.currentPlayer());
        model.setNextPlayer();
        assertEquals(Player.BLACK, model.currentPlayer());
    }

    @Test
    //Testing setNextPlayer method
    public void testSetNextPlayer() {
        ChessModel model = new ChessModel();

        assertEquals(Player.WHITE, model.currentPlayer());
        model.setNextPlayer();
        assertEquals(Player.BLACK, model.currentPlayer());
        model.setNextPlayer();
        assertEquals(Player.WHITE, model.currentPlayer());
    }

    @Test
    //Testing inCheck method
    public void testInCheck() {
        ChessModel model = new ChessModel();
        Move move = new Move(7,0,1,4);
        model.move(move);
        assertTrue(model.inCheck(Player.BLACK));

    }

    @Test
    //Testing isComplete method when in checkmate
    public void testIsComplete1() {
        ChessModel model = new ChessModel();
        model.setNextPlayer();

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                model.setPiece(i,j,new Queen(Player.WHITE));

        model.setPiece(0,0, new King(Player.BLACK));

        assertTrue(model.isComplete());
    }

    @Test
    //Testing isComplete method when not in check
    public void testIsComplete2() {
        ChessModel model = new ChessModel();
        model.setNextPlayer();

        model.setPiece(0,0, new King(Player.BLACK));

        assertFalse(model.isComplete());
    }

    @Test
    //Testing isComplete method when not in checkmate
    public void testIsComplete3() {
        ChessModel model = new ChessModel();
        model.setNextPlayer();

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                model.setPiece(i,j,null);

        model.setPiece(1,0, new King(Player.BLACK));
        model.setPiece(1,1, new Rook(Player.WHITE));

        assertFalse(model.isComplete());
    }

    @Test
    //Testing castling kingside white
    public void testKingsideWhiteCastle() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(7,4, new King(Player.WHITE));
        model.setPiece(7,7, new Rook(Player.WHITE));

        Move move = new Move(7,4,7,6);

        assertTrue(model.isValidMove(move));
        model.move(move);
        assertEquals("King", model.pieceAt(7,6).type());
        assertEquals("Rook", model.pieceAt(7,5).type());
    }

    @Test
    //Testing castling queenside white
    public void testQueensideWhiteCastle() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(7,4, new King(Player.WHITE));
        model.setPiece(7,0, new Rook(Player.WHITE));

        Move move = new Move(7,4,7,2);

        assertTrue(model.isValidMove(move));
        model.move(move);
        assertEquals("King", model.pieceAt(7,2).type());
        assertEquals("Rook", model.pieceAt(7,3).type());
    }

    @Test
    //Testing castling kingside white
    public void testKingsideBlackCastle() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,4, new King(Player.BLACK));
        model.setPiece(0,7, new Rook(Player.BLACK));

        Move move = new Move(0,4,0,6);

        assertTrue(model.isValidMove(move));
        model.move(move);
        assertEquals("King", model.pieceAt(0,6).type());
        assertEquals("Rook", model.pieceAt(0,5).type());
    }

    @Test
    //Testing castling queenside white
    public void testQueensideBlackCastle() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(0,4, new King(Player.BLACK));
        model.setPiece(0,0, new Rook(Player.BLACK));

        Move move = new Move(0,4,0,2);

        assertTrue(model.isValidMove(move));
        model.move(move);
        assertEquals("King", model.pieceAt(0,2).type());
        assertEquals("Rook", model.pieceAt(0,3).type());
    }

    @Test
    //Testing that castling requires first move be true for king
    public void testNonFirstMoveCastleKing() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(7,4, new King(Player.WHITE));
        model.setPiece(7,7, new Rook(Player.WHITE));

        Move move1 = new Move(7,4,7,5);
        Move move2 = new Move(7,5,7,4);

        model.move(move1);
        model.move(move2);

        Move move = new Move(7,4,7,6);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing that castling requires first move be true for rook
    public void testNonFirstMoveCastleRook() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(7,4, new King(Player.WHITE));
        model.setPiece(7,7, new Rook(Player.WHITE));

        Move move1 = new Move(7,7,7,6);
        Move move2 = new Move(7,6,7,7);

        model.move(move1);
        model.move(move2);

        Move move = new Move(7,4,7,6);

        assertFalse(model.isValidMove(move));
    }

    @Test
    //Testing promotion for white pieces
    public void testWhitePromotion() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(1,0, new Pawn(Player.WHITE));
        Move move = new Move(1,0,0,0);

        model.move(move);

        assertEquals("Queen", model.pieceAt(0,0).type());
    }

    @Test
    //Testing en passant white
    public void testEnPassantWhite() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(3,4, new Pawn(Player.WHITE));
        model.setPiece(3,5, new Pawn(Player.BLACK));
        Move move = new Move(3,4,4,5);

        assertTrue(model.isValidMove(move));

        model.move(move);

        assertEquals(null, model.pieceAt(3,5));
    }

    @Test
    //Testing en passant black
    public void testEnPassantBlack() {
        ChessModel model = new ChessModel();
        clearBoard(model);

        model.setPiece(4,4, new Pawn(Player.WHITE));
        model.setPiece(4,5, new Pawn(Player.BLACK));
        Move move = new Move(4,5,3,4);

        assertTrue(model.isValidMove(move));

        model.move(move);

        assertEquals(null, model.pieceAt(4, 5));
    }

    private void clearBoard(ChessModel model) {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                model.setPiece(i,j,null);
    }

}