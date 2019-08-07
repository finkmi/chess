package project3;

/******************************************************************
 *
 * King Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class King extends ChessPiece {

    /** Holds if the King has moved yet or not */
    private boolean isFirstMove = true;

    /******************************************************************
     * Constructor that takes a King as a parameter and sets it's fields
     * equal to that rook's
     * @param oldKing
     */
    public King(King oldKing) {
        super(oldKing.player());
        isFirstMove = oldKing.isFirstMove;
    }

    /******************************************************************
     * Constructor for King that takes a player and sets that pawn's
     * player field to it.
     * @param player
     */
    public King(Player player) {
        super(player);
    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {
        return "King";
    }

    /******************************************************************
     * Gives back the value of the isFirstMove instance variable
     * @return boolean that determines if this king has moved
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /******************************************************************
     * Sets the king firstMove flag to the parameter given
     * @param firstMove
     */
    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    /******************************************************************
     * Determines if the move being made is valid specifically for king's
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;
        int rowDifference = Math.abs(move.toRow - move.fromRow);
        int colDifference = Math.abs(move.toColumn - move.fromColumn);

        valid = super.isValidMove(move,board);

        //King cannot move more than one space in any direction unless castling
        if(rowDifference > 1 || colDifference > 1)
            valid = false;


        if(isFirstMove && move.fromRow == move.toRow && colDifference > 1) {
            //Castling
            if (player() == Player.WHITE) {
                //White king
                if(move.toColumn == 6)
                    //Kingside Castle
                    if (board[7][5] == null && board[7][6] == null)
                        //Castle path must be clear
                        if(board[7][7] == null)
                            valid = false;
                        else if (board[7][7].type().equals("Rook"))
                            //Rook must be present to castle
                            if (((Rook) board[7][7]).isFirstMove())
                                //Must be rooks first move
                                valid = true;


                if(move.toColumn == 2)
                    //Queenside Castle
                    if(board[7][1] == null && board[7][2] == null
                            && board[7][3] == null)
                        //Castle path must be clear
                        if(board[7][0] == null)
                            valid = false;
                        else if(board[7][0].type().equals("Rook"))
                            //Rook must be present to castle
                            if(((Rook) board[7][0]).isFirstMove())
                                //Must be rooks first move
                                valid = true;
            }

            else if(player() == Player.BLACK) {
                //Black King
                if(move.toColumn == 6)
                    //Kingside Castle
                    if(board[0][5] == null && board[0][6] == null)
                        //Castle path must be clear
                        if(board[0][7] == null)
                            valid = false;
                        else if(board[0][7].type().equals("Rook"))
                            //Rook must be present to castle
                            if(((Rook) board[0][7]).isFirstMove())
                                //Must be rooks first move
                                valid = true;

                if(move.toColumn == 2)
                    //Queenside Castle
                    if(board[0][1] == null && board[0][2] == null
                            && board[0][3] == null)
                        //Castle path must be clear
                        if(board[0][0] == null)
                            valid = false;
                        else if(board[0][0].type().equals("Rook"))
                            //Rook must be present to castle
                            if(((Rook) board[0][0]).isFirstMove())
                                //Must be rooks first move
                                valid = true;


            }
        }

        return valid;
    }
}