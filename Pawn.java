package project3;

import static java.lang.Math.abs;

/******************************************************************
 *
 * Pawn Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Pawn extends ChessPiece {

    /** Holds if the pawn has moved yet or not */
    private boolean isFirstMove = true;

    /******************************************************************
     * Constructor that takes a Pawn as a parameter and sets it's fields
     * equal to that pawn's
     * @param oldPawn
     */
    public Pawn(Pawn oldPawn) {
        super(oldPawn.player());
        isFirstMove = oldPawn.isFirstMove;
    }

    /******************************************************************
     * Constructor for Pawn that takes a player and sets that pawn's
     * player field to it.
     * @param player
     */
    public Pawn(Player player) {
        super(player);
    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {
        return "Pawn";
    }

    /******************************************************************
     * Gives back the value of the isFirstMove instance variable
     * @return boolean that determines if this pawn has moved
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /******************************************************************
     * Sets the pawns firstMove flag to the parameter given
     * @param firstMove
     */
    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    /******************************************************************
     * determines if the move being made is valid specifically for pawn's
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = true;
        valid = super.isValidMove(move, board);

        if(player() == Player.BLACK) {
            //Handles validity for black pawns
            if (move.toRow > move.fromRow + 2)
                //Can't go further than 2 spaces ever
                valid = false;
            if (move.toRow > move.fromRow + 1 && !isFirstMove)
                //Can't go further than 1 space if not the first move
                valid = false;
            if (move.toRow == move.fromRow)
                //Can't move horizontally
                valid = false;
            if (move.toRow < move.fromRow)
                //Can't move backwards
                valid = false;
            if (abs(move.toColumn - move.fromColumn) > 1)
                //Can't move more than one space right or left
                valid = false;

            //On first move, Pawn should not be able to jump a piece
            if(isFirstMove && move.toRow == 3)
                if(board[2][move.fromColumn] != null)
                    valid = false;

            //If moving out of column, move can only be
            // one row up and spot MUST be occupied by opposing player
            if (move.toColumn != move.fromColumn)
                if(board[move.toRow][move.toColumn] == null
                        || move.toRow - move.fromRow > 1)
                    valid = false;

            if(move.toRow != move.fromRow && move.fromColumn == move.toColumn)
                //Can't take a piece directly in front of you
                if(board[move.toRow][move.toColumn] != null)
                    valid = false;

            if (move.toColumn != move.fromColumn)
                //En passant
                if(board[move.toRow][move.toColumn] == null
                        && move.fromRow == 4
                        && board[move.fromRow][move.toColumn] != null
                        && move.toRow == 5) {
                    if(board[move.fromRow][move.toColumn].type().equals("Pawn")) {
                        valid = true;
                    }
                    else
                        valid = false;
                }
        }

        if(player() == Player.WHITE) {
            //Handles validity for white pawns
            if (move.toRow < move.fromRow - 2)
                //Can't go further than 2 spaces ever
                valid = false;
            if (move.toRow < move.fromRow - 1 && !isFirstMove)
                //Can't go further than 1 space if not the first move
                valid = false;
            if (move.toRow == move.fromRow)
                //Can't move horizontally
                valid = false;
            if (move.toRow > move.fromRow)
                //Can't move backwards
                valid = false;
            if (abs(move.toColumn - move.fromColumn) > 1)
                //Can't move more than one space right or left
                valid = false;


            //On first move, Pawn should not be able to jump a piece
            if(isFirstMove && move.toRow == 4)
                if(board[5][move.fromColumn] != null)
                    valid = false;

            //If moving out of column, move can only be
            // one row up and spot MUST be occupied by opposing player
            if (move.toColumn != move.fromColumn)
                if(board[move.toRow][move.toColumn] == null
                        || move.fromRow - move.toRow > 1)
                    valid = false;

            if(move.toRow != move.fromRow && move.fromColumn == move.toColumn)
                //Can't take a piece directly in front of you
                if(board[move.toRow][move.toColumn] != null)
                    valid = false;

            if (move.toColumn != move.fromColumn)
                //En passant
                if(board[move.toRow][move.toColumn] == null
                        && move.fromRow == 3
                        && board[move.fromRow][move.toColumn] != null
                        && move.toRow == 2) {
                    if(board[move.fromRow][move.toColumn].type().equals("Pawn")) {
                        valid = true;
                    }
                    else
                        valid = false;
                }
        }

        return valid;
    }
}