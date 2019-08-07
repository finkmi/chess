package project3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/******************************************************************
 *
 * ChessPanel class that takes info from the model and displays it
 * to the user
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class ChessPanel extends JPanel {

    /** 2D array holding all the buttons that will be the tiles of the board */
    private JButton[][] board;

    /** Chess model that will act as a backend */
    private ChessModel model;

    /** Undo button */
    private JButton undoBtn;
    /** AI button */
    private JButton AI;

    /** ImageIcon for white pieces */
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    /** ImageIcon for black pieces */
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    /** Holds whether this is first click or not
     * and should be setting the from or the to moves */
    private boolean firstTurnFlag;

    /** Determines whether a valid move occurred
     * and it's the next players turn or not */
    private boolean remainOnSamePlayer;

    /** Holds move to be made fields */
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    /** Button listener */
    private listener listener;

    /******************************************************************
     * Constructs the panel, instantiating all buttons, adding the
     * listener, and setting the image icons to them.
     */
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        undoBtn = new JButton("Undo");
        AI = new JButton("AI");
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(),
                model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if(model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }

        undoBtn.addActionListener(listener);
        AI.addActionListener(listener);
        buttonpanel.add(undoBtn);
        buttonpanel.add(AI);

        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /******************************************************************
     * Sets color of tiles
     * @param r
     * @param c
     */
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /******************************************************************
     * Places white pieces icons where they belong
     * @param r
     * @param c
     */
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * Sets black piece icons where they belong
     * @param r
     * @param c
     */
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * Creates all piece icons from resource file
     */
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wRook.png");
        wBishop = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wBishop.png");
        wQueen = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wQueen.png");
        wKing = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wKing.png");
        wPawn = new ImageIcon( "/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wPawn.png");
        wKnight = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/wKnight.png");

        //Sets the Image for the black player pieces
        bRook = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bRook.png");
        bBishop = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bBishop.png");
        bQueen = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bQueen.png");
        bKing = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bKing.png");
        bPawn = new ImageIcon( "/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bPawn.png");
        bKnight = new ImageIcon("/Users/michaelfink/IdeaProjects" +
                "/fink_projects163/resources/bKnight.png");
    }

    /******************************************************************
     * Updates what the board looks like so it is in line with the
     * model
     */
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                }

                else
                if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);

                }
        }
        repaint();
    }

    /******************************************************************
     * Represents action listener for buttons
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if(undoBtn == event.getSource()) {
                //So long as a move has taken place, undo
                if(model.getNumSaveStates() != 1) {
                    model.undo();
                    model.setNextPlayer();
                    displayBoard();
                    firstTurnFlag = true;
                }
            }

            if(AI == event.getSource()) {
                model.AI();
                displayBoard();

                //If they AI places you in check/checkmate, tell the user
                if(model.isComplete())
                    JOptionPane.showMessageDialog(null,"Game Over!");
                else if(model.inCheck(model.currentPlayer()))
                    JOptionPane.showMessageDialog(null,""
                            + model.currentPlayer() + " is in check!");
            }

            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;

                            try {   //If player whos turn it is clicks
                                // his/her piece then move on. If not
                                // then firstTurnFlag remains true
                                if (model.pieceAt(r, c).player()
                                        .equals(model.currentPlayer())) {
                                    firstTurnFlag = false;
                                    remainOnSamePlayer = false;
                                }
                            }
                            catch(NullPointerException e) {
                                //If you click on a black space
                                // wait for a click on a piece
                                firstTurnFlag = true;
                            }

                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m)) == true) {
                                model.move(m);


                                if(model.inCheck(model.currentPlayer())) {
                                    //Disallows player from moving
                                    // into/remaining in check
                                    model.undo();
                                    JOptionPane.showMessageDialog(null,
                                            "You cannot move yourself into check.");
                                    remainOnSamePlayer = true;
                                }

                                displayBoard();

                                if(!remainOnSamePlayer)
                                    model.setNextPlayer();


                                if(model.isComplete())
                                    JOptionPane.showMessageDialog(null,"Game Over!");
                                else if(model.inCheck(model.currentPlayer()))
                                    JOptionPane.showMessageDialog(null, "" +
                                            model.currentPlayer() + " is in check!");


                            }
                        }
        }
    }
}