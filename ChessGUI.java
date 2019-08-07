package project3;

import java.awt.Dimension;
import javax.swing.JFrame;

/******************************************************************
 *
 * ChessGUI class used to display the board to user
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class ChessGUI {

    /******************************************************************
     * Creates the frame and panel for the chess game
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 637));
        frame.pack();
        frame.setVisible(true);
    }
}