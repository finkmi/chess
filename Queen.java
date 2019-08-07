package project3;

/******************************************************************
 *
 * Queen Chess piece
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Queen extends ChessPiece {

    /******************************************************************
     * Constructor for Queen that takes a player and sets that queen's
     * player field to it.
     * @param player
     */
    public Queen(Player player) {
        super(player);

    }

    /******************************************************************
     * Returns the type of piece that this is
     * @return string holding this piece's type
     */
    public String type() {
        return "Queen";

    }

    /******************************************************************
     * Determines if the move being made is valid specifically for queen's
     * @param move
     * @param board
     * @return true or false depending on if the move is valid or not
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}