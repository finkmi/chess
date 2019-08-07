package project3;

/******************************************************************
 *
 * Rook Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Rook extends ChessPiece {

    /** Holds if the Rook has moved yet or not */
    private boolean isFirstMove = true;

    /******************************************************************
     * Constructor that takes a Rook as a parameter and sets it's fields
     * equal to that rook's
     * @param oldRook
     */
    public Rook(Rook oldRook) {
        super(oldRook.player());
        isFirstMove = oldRook.isFirstMove;
    }

    /******************************************************************
     * Constructor for Rook that takes a player and sets that pawn's
     * player field to it.
     * @param player
     */
    public Rook(Player player) {

        super(player);
    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {

        return "Rook";
    }

    /******************************************************************
     * Gives back the value of the isFirstMove instance variable
     * @return boolean that determines if this rook has moved
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /******************************************************************
     * Sets the rook firstMove flag to the parameter given
     * @param firstMove
     */
    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    /******************************************************************
     * Determines if the move being made is valid specifically for rook's
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true,
                checkingRow = false,
                checkingColumn = false;
        valid = super.isValidMove(move, board);

        if(move.toRow != move.fromRow)
            //If the row is different the column must be the same
            if(move.toColumn != move.fromColumn)
                valid = false;
            else
                checkingColumn = true;

        if(move.toColumn != move.fromColumn)
            //If the column is different the row must be the same
            if(move.toRow != move.fromRow)
                valid = false;
            else
                checkingRow = true;

        if(valid) {
            //If valid to this point, check that the path is clear by...
            if (checkingColumn) {
                //checking the spaces in the same column between to and from

                if(move.toRow > move.fromRow)
                    //If the toRow is greater, check from there down to fromRow
                    for(int i = move.toRow - 1; i > move.fromRow; i--) {
                      if(board[i][move.fromColumn] != null)
                          //If any spaces are occupied, path is not clear
                          valid = false;
                    }

                else
                    //If the toRow is less, check from there up to fromRow
                    for(int i = move.toRow + 1; i < move.fromRow; i++) {
                        if(board[i][move.fromColumn] != null)
                            //If any spaces are occupied, path is not clear
                            valid = false;
                    }
            }

            else if (checkingRow) {

                if(move.toColumn > move.fromColumn)
                    //If the toRow is greater, check from there down to fromRow
                    for(int i = move.toColumn - 1; i > move.fromColumn; i--) {
                        if(board[move.fromRow][i] != null)
                            //If any spaces are occupied, path is not clear
                            valid = false;
                    }

                else
                    //If the toRow is less, check from there up to fromRow
                    for(int i = move.toColumn + 1; i < move.fromColumn; i++) {
                        if(board[move.fromRow][i] != null)
                            //If any spaces are occupied, path is not clear
                            valid = false;
                    }
            }
        }

        return valid;

    }

}