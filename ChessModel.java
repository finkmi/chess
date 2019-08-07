package project3;

import javax.swing.*;
import java.util.ArrayList;

/******************************************************************
 *
 * Handles backend logic of chess game
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class ChessModel implements IChessModel {
    /** Board for chess game */
    private IChessPiece[][] board;

    /** Current Player */
    private Player player;

    /** ArrayList that holds save states of the board for the undo functionality */
    private ArrayList<IChessPiece[][]> boardSaves;


    /******************************************************************
     *Constructor for chess model. Creates 8x8 board of chess peices
     * and sets up board correctly in starting positions.
     */
    public ChessModel() {
        boardSaves = new ArrayList();
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        for(int col = 0; col <= 7; col++) {
            board[6][col] = new Pawn(Player.WHITE);
        }

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        for(int col = 0; col <= 7; col++) {
            board[1][col] = new Pawn(Player.BLACK);
        }

        save();

    }

    /******************************************************************
     *Gets number of saves being held in the boardSaves arraylist
     *
     * @return  number of board saves
     */
    public int getNumSaveStates() {
        return boardSaves.size();
    }

    /******************************************************************
     *Determines if a player is in checkmate
     *
     * @return boolean that is true or false depending on whether or not
     * a player is in checkmate
     */
    public boolean isComplete() {
        boolean valid = true;

        if(!inCheck(currentPlayer()))
            //If not in check, the game isn't over yet
            valid = false;
        else {
            Move possibleMove = new Move(0, 0, 0, 0);
            for (int r = 0; r < 8; r++)         //Go through every board position
                for (int c = 0; c < 8; c++) {
                    if (board[r][c] == null)
                        ;
                    else if (board[r][c].player().equals(player)) {
                        //Set current player piece as
                        // the fromRow and Col of possibleMove

                        possibleMove.setFromRow(r);
                        possibleMove.setFromColumn(c);

                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++) {
                                //Go through every position on the board

                                possibleMove.setToRow(i);
                                possibleMove.setToColumn(j);
                                //Set the toRow and Col as that spot

                                if (isValidMove(possibleMove)) {
                                    //If this is a valid move,
                                    // make it and see if you're still in check
                                    move(possibleMove);

                                    if (!inCheck(currentPlayer())) {
                                        //If any possible move takes you
                                        // out of check, you aren't checkmated.
                                        valid = false;
                                    }

                                    undo();
                                    //Don't make the move for them

                                }

                            }

                    }
                }
        }

        return valid;
    }

    /******************************************************************
     *Determines if a move is valid by calling specific piece's method
     *
     * @param move a {@link project3.Move} object describing the move to be made.
     * @return boolean determining if a move is valid
     */
    public boolean isValidMove(Move move) {
        boolean valid = false;

        if (board[move.fromRow][move.fromColumn] != null)
            if(board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                valid = true;

        return valid;
    }

    /******************************************************************
     *Makes a given move, checking for oddball moves such as castling
     *
     * @param move a {@link project3.Move} object describing the move to be made.
     */
    public void move(Move move) {
        save();

        checkEnPassant(move);

        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;

        //Check for oddball moves and execute if necessary
        checkPromotion(move);
        checkCastle(move);




        if(board[move.toRow][move.toColumn].type().equals("Pawn"))
            ((Pawn)board[move.toRow][move.toColumn]).setFirstMove(false);
        else if(board[move.toRow][move.toColumn].type().equals("Rook"))
            ((Rook)board[move.toRow][move.toColumn]).setFirstMove(false);
        else if(board[move.toRow][move.toColumn].type().equals("King"))
            ((King)board[move.toRow][move.toColumn]).setFirstMove(false);

    }

    /******************************************************************
     *Determines if a given player is currently in check
     *
     * @param  p {@link project3.Move} the Player being checked
     * @return boolean describing whether or not a player is in check
     */
    public boolean inCheck(Player p) {
        boolean valid = false;
        Move move = new Move(0,0,0,0);

        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                //Finds king of this player and
                // sets the toRow and Col of move as the king
                if(board[r][c] == null)
                    ;
                else if (board[r][c].type().equals("King")
                        && board[r][c].player().equals(p)) {
                    move.setToRow(r);
                    move.setToColumn(c);
                }

        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                //Checks every opposing piece with to see
                // if attacking the kings square is a valid move
                if(board[r][c] == null)
                    ;
                else if(board[r][c].player().equals(p.next())) {
                    move.setFromRow(r);
                    move.setFromColumn(c);
                    if(isValidMove(move))
                        valid = true;
                    //If it is a valid move, the king of this player is in check
                }

        return valid;
    }


    /******************************************************************
     *Returns the player whose turn it currently is.
     * @return object of type Player whose turn it currently is
     */
    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     * Gets the number of rows on the board
     * @return the number of rows on the board
     */
    public int numRows() {
        return 8;
    }

    /******************************************************************
     *Gets the number of columns on the board
     * @return the number of columns on the board
     */
    public int numColumns() {
        return 8;
    }

    /******************************************************************
     *Gets the piece at a spot on the board
     * @param row
     * @param column
     * @return Chess piece at a position
     */
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /******************************************************************
     *Sets piece at a spot on the board to given piece
     * @param row
     * @param column
     * @param piece
     */
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /******************************************************************
     *Changes the player such that it doesn't stay one player's turn.
     */
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     *Saves off the current state of the board such that an undo can
     * take place
     */
    public void save() {
        IChessPiece[][] tempBoard = new IChessPiece[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                if (board[i][j] instanceof Pawn)
                    tempBoard[i][j] = new Pawn((Pawn)board[i][j]);

                else if (board[i][j] instanceof Rook)
                    tempBoard[i][j] = new Rook((Rook)board[i][j]);

                else if (board[i][j] instanceof King)
                    tempBoard[i][j] = new King((King)board[i][j]);

                else
                    tempBoard[i][j] = board[i][j];
            }

        boardSaves.add(tempBoard);
    }

    /******************************************************************
     * Makes the board exactly alike to what it was before the previous
     * move.
     */
    public void undo() {

        if(boardSaves.size() > 1) {
            //board = boardSaves.get(boardSaves.size() - 2);
            for(int i = 0; i < 8; i++)
                for(int j = 0; j < 8; j++)
                    board[i][j] = boardSaves.get(boardSaves.size() - 1)[i][j];




            boardSaves.remove(boardSaves.size() - 1);
        }
    }

    /******************************************************************
     *Determines if a castle is a legal move and makes it if it is.
     * @param move
     */
    private void checkCastle(Move move) {
        if(move.toRow == move.fromRow && move.fromColumn == 4 &&
                pieceAt(move.toRow,move.toColumn).type().equals("King"))
            if(move.toColumn == 6) {    //Kingside
                if (move.toRow == 7) {      //White
                    board[move.toRow][7] = null;
                    board[move.toRow][5] = new Rook(Player.WHITE);
                } else if (move.toRow == 0) {  //Black
                    board[move.toRow][7] = null;
                    board[move.toRow][5] = new Rook(Player.BLACK);
                }
            }
            else if(move.toColumn == 2) {   //Queenside
                if (move.toRow == 7) {      //White
                    board[move.toRow][0] = null;
                    board[move.toRow][3] = new Rook(Player.WHITE);
                } else if (move.toRow == 0) {  //Black
                    board[move.toRow][0] = null;
                    board[move.toRow][3] = new Rook(Player.BLACK);
                }
            }
    }

    /******************************************************************
     *Determines if a pawn is to be promoted to a queen and executes
     * the promotion if necessary.
     * @param move
     */
    private void checkPromotion(Move move) {
        if(move.toRow == 0 &&pieceAt(move.toRow,move.toColumn).type().equals("Pawn"))
            board[move.toRow][move.toColumn] = new Queen(Player.WHITE);
        if(move.toRow == 7 &&pieceAt(move.toRow,move.toColumn).type().equals("Pawn"))
            board[move.toRow][move.toColumn] = new Queen(Player.BLACK);
    }

    /******************************************************************
     * Determines if an en passant is happeneing and null the pawn that
     * is "in passing" such that the isValidMove() of Pawn doesn't have
     * to take care of this.
     * @param move
     */
    private void checkEnPassant(Move move) {
        if (move.toColumn != move.fromColumn)           //En passant
            if(board[move.toRow][move.toColumn] == null && (move.fromRow == 4
                    || move.fromRow == 3)
                    && board[move.fromRow][move.toColumn] != null) {
                if (board[move.fromRow][move.toColumn].type().equals("Pawn"))
                    board[move.fromRow][move.toColumn] = null;
            }
    }

    /******************************************************************
     * Automatically moves the black players pieces.
     */
    public void AI() {
        if(currentPlayer() == Player.BLACK) {

            if (inCheck(currentPlayer())) {
                moveOutOfCheck();
                setNextPlayer();
                return;
            }

            else {
                if(attemptToCheck()) {
                    setNextPlayer();
                    return;
                }
                else if(moveOutOfDanger()) {
                    setNextPlayer();
                    return;
                }
                else if(moveForward()) {
                    setNextPlayer();
                    return;
                }
            }


        }
        else
            JOptionPane.showMessageDialog(null,"Not Black's Turn");
    }

    /******************************************************************
     * Assisting method for the AI attempts to move out of check if the
     * black player is in check
     */
    private void moveOutOfCheck() {
        Move possibleMove = new Move(0, 0, 0, 0);

        for (int r = 0; r < 8; r++)         //Go through every board position
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == null)
                    ;
                else if (board[r][c].player().equals(player)) {
                    //Set current player piece as the
                    // fromRow and Col of possibleMove
                    possibleMove.setFromRow(r);
                    possibleMove.setFromColumn(c);

                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) {
                            //Go through every position on the board
                            possibleMove.setToRow(i);
                            possibleMove.setToColumn(j);
                            //Set the toRow and Col as that spot

                            if (board[possibleMove.fromRow][possibleMove.fromColumn]
                                    .player() == Player.BLACK
                                    && isValidMove(possibleMove)) {
                                //If this is a valid move,
                                // make it and see if you're still in check
                                move(possibleMove);

                                if(inCheck(currentPlayer()))
                                    undo();
                                else
                                    return;
                            }


                        }
                }
            }
    }

    /******************************************************************
     * Attempt to put the other player in check if not currently in check
     * @return whether or not a move was made
     */
    private boolean attemptToCheck() {
        Move possibleMove = new Move(0, 0, 0, 0);

        for (int r = 0; r < 8; r++)         //Go through every board position
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == null)
                    ;
                else if (board[r][c].player().equals(player)) {
                    //Set current player piece as
                    // the fromRow and Col of possibleMove
                    possibleMove.setFromRow(r);
                    possibleMove.setFromColumn(c);

                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) {
                            //Go through every position on the board
                            possibleMove.setToRow(i);
                            possibleMove.setToColumn(j);
                            //Set the toRow and Col as that spot

                            if (board[r][c].player() == Player.BLACK &&
                                    isValidMove(possibleMove)) {
                                //If this is a valid move,
                                // make it and see if you checked opponent
                                move(possibleMove);

                                if(inCheck(player.next()) && !inCheck(player)
                                && inDanger(possibleMove.toRow, possibleMove.toColumn)
                                        == null)
                                    return true;
                                else
                                    undo();
                            }
                        }

                }
            }
        return false;
    }

    /******************************************************************
     * Attempts to find a piece in danger and subsequently move that
     * piece out of danger
     * @return whether or not a move was made
     */
    private boolean moveOutOfDanger() {
        boolean result = false;
        boolean pieceInDangerFound = false;
        Move possibleMove = new Move(0,0,0,0);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                //Go through every position on the board
                if(board[i][j] != null) {
                    if (board[i][j].player() == Player.BLACK
                            && inDanger(i, j) != null) {
                        possibleMove.setFromRow(i);
                        possibleMove.setFromColumn(j);
                        pieceInDangerFound = true;
                        String[] attackerPos = inDanger(i,j).split("/");
                        int aRow = Integer.parseInt(attackerPos[0]);
                        int aCol = Integer.parseInt(attackerPos[1]);
                        possibleMove.setToRow(aRow);
                        possibleMove.setToColumn(aCol);
                    }
                }

                if(pieceInDangerFound) {

                    if (board[possibleMove.fromRow][possibleMove.fromColumn].player()
                            == Player.BLACK && isValidMove(possibleMove)) {
                        //If you can take attacking piece do it
                        move(possibleMove);
                        if (inDanger(possibleMove.toRow, possibleMove.toColumn)
                                != null || inCheck(player))
                            undo();
                        else
                            return true;
                    }


                    for (int r = 0; r < 8; r++)
                        for (int c = 0; c < 8; c++) {
                            possibleMove.setToRow(r);
                            possibleMove.setToColumn(c);


                            if (board[possibleMove.fromRow][possibleMove.fromColumn].player() == Player.BLACK &&
                                    isValidMove(possibleMove)) {
                                move(possibleMove);
                                if (inDanger(possibleMove.toRow
                                        , possibleMove.toColumn)
                                        != null || inCheck(player))
                                    undo();
                                else
                                    return true;

                            }
                        }
                }

            }



        return result;
    }

    /******************************************************************
     * Helper method that determines if a piece at a spot is in danger
     * @param row
     * @param col
     * @return a string containing the location of the piece endangering
     * the piece at row, col
     */
    private String inDanger(int row, int col) {
        Move possibleMove = new Move(0,0,0,0);

        possibleMove.setToRow(row);
        possibleMove.setToColumn(col);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                //Go through every position on the board
                if(board[i][j] == null)
                    ;
                else if(board[i][j].player() == Player.WHITE){
                    possibleMove.setFromRow(i);
                    possibleMove.setFromColumn(j);
                    //Set the fromRow and Col as that spot
                }

                if (isValidMove(possibleMove)) {
                    return "" + i + "/" + j;
                }
            }
        return null;
    }

    /******************************************************************
     * Moves pawns forward in the case of no other moves available
     * @return whether or not a move happened
     */
    private boolean moveForward() {
        boolean result = false;
        Move possibleMove = new Move(0,0,0,0);

        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                //Finds pawns of this player and
                // sets the fromRow and Col of move as them
                if (board[r][c] == null)
                    ;
                else if (board[r][c].type().equals("Pawn")
                        && board[r][c].player() == Player.BLACK) {
                    possibleMove.setFromRow(r);
                    possibleMove.setFromColumn(c);

                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) {
                            possibleMove.setToRow(i);
                            possibleMove.setToColumn(j);

                            if(board[possibleMove.fromRow][possibleMove.fromColumn]
                                    .player() == Player.BLACK
                                    && isValidMove(possibleMove)) {
                                move(possibleMove);
                                if(inDanger(possibleMove.toRow,possibleMove.toColumn)
                                        != null || inCheck(player))
                                    undo();
                                else
                                    return true;
                            }
                        }
                }
            }



        return result;
    }

}