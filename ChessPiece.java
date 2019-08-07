package project3;

/******************************************************************
 *
 * Chess piece class that implements the IChessPiece class
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public abstract class ChessPiece implements IChessPiece {

    /** Holds the player who owns this piece */
    private Player owner;

    /******************************************************************
     * Default constructor for ChessPiece's
     */
    public ChessPiece() {
        super();
    }

    /******************************************************************
     * Constructor that sets the chess pieces owner field to the player
     * parameter given
     * @param player
     */
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /******************************************************************
     * Abstract method that will return what type of piece this is
     * @return
     */
    public abstract String type();

    /******************************************************************
     * Returns the player that owns this piece
     * @return
     */
    public Player player() {
        return owner;
    }

    /******************************************************************
     * Determines if the current move is generally valid. Checks for out
     * of bounds, not moving onto piece of own owner and things of this
     * nature.
     * @param move  a {@link project3.Move} object describing the move to be made.
     * @param board the {@link project3.IChessBoard} in which this piece resides.
     * @return
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        if(board[move.toRow][move.toColumn] == null)
            valid = true;
        if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn))
            valid = false;
        if (move.toRow < 0)
            valid = false;
        if (move.toColumn < 0)
            valid = false;
        if (move.toRow > 7)
            valid = false;
        if (move.toColumn > 7)
            valid = false;
        if(board[move.toRow][move.toColumn] != null
                && board[move.toRow][move.toColumn].player() == this.player())
            valid = false;

        return valid;
    }
}