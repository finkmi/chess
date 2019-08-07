package project3;

/******************************************************************
 *
 * Bishop Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Bishop extends ChessPiece {

    /******************************************************************
     * Constructor for Bishop that takes a player and sets that bishop's
     * player field to it.
     * @param player
     */
    public Bishop(Player player) {
        super(player);
    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {
        return "Bishop";
    }

    /******************************************************************
     * Determines if the move being made is valid specifically for bishop's
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = true;
        int rowDifference = Math.abs(move.toRow - move.fromRow);
        int colDifference = Math.abs(move.toColumn - move.fromColumn);

        valid = super.isValidMove(move,board);

        if(rowDifference != colDifference)
            //Ensures that bishops must move diagonally
            valid = false;

        //Checking for a clear path
        //There are 4 possible cases
        if(valid) {
            if (move.toRow > move.fromRow && move.toColumn > move.fromColumn)
                //Row increased and Col increased
                for (int i = 1; i < rowDifference; i++)
                    if (board[move.fromRow + i][move.fromColumn + i] != null)
                        valid = false;

            if (move.toRow > move.fromRow && move.toColumn < move.fromColumn)
                //Row increased and Col decreased
                for (int i = 1; i < rowDifference; i++)
                    if (board[move.fromRow + i][move.fromColumn - i] != null)
                        valid = false;

            if (move.toRow < move.fromRow && move.toColumn > move.fromColumn)
                //Row decreased and Col increased
                for (int i = 1; i < rowDifference; i++)
                    if (board[move.fromRow - i][move.fromColumn + i] != null)
                        valid = false;

            if (move.toRow < move.fromRow && move.toColumn < move.fromColumn)
                //Row decreased and Col decreased
                for (int i = 1; i < rowDifference; i++)
                    if (board[move.fromRow - i][move.fromColumn - i] != null)
                        valid = false;
        }

        return valid;

    }
}