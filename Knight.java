package project3;

/******************************************************************
 *
 * Knight Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Knight extends ChessPiece {

    /******************************************************************
     * Constructor for Knight that takes a player and sets that Knight's
     * player field to it.
     * @param player
     */
    public Knight(Player player) {
        super(player);
    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {
        return "Knight";
    }

    /******************************************************************
     * Determines if the move being made is valid specifically for Knights
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = true;
        int rowDifference = Math.abs(move.toRow - move.fromRow);
        int colDifference = Math.abs(move.toColumn - move.fromColumn);

        valid = super.isValidMove(move,board);

        if(colDifference > 2)
            //Can't ever move more than two columns away
            valid = false;
        if(rowDifference > 2)
            //Cant ever move more than two rows away
            valid = false;
        if(rowDifference == 2 && colDifference != 1)
            valid = false;
        if(colDifference == 2 && rowDifference != 1)
            valid = false;
        if(rowDifference == 1 && colDifference != 2)
            valid = false;
        if(colDifference == 1 && rowDifference != 2)
            valid = false;
        return valid;


    }

}