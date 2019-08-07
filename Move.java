package project3;

/******************************************************************
 *
 * Move class used to move pieces around the board
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public class Move {

    /** Holds the to and from positions in the form of rows and columns */
    public int fromRow, fromColumn, toRow, toColumn;

    /******************************************************************
     * Default constructor
     */
    public Move() {
    }

    /******************************************************************
     * Constructor that sets all instance variables to the given parameters
     * @param fromRow
     * @param fromColumn
     * @param toRow
     * @param toColumn
     */
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /******************************************************************
     * Sets just the fromRow field of a Move object
     * @param fromRow
     */
    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    /******************************************************************
     * Sets just the fromColumn field of a Move object
     * @param fromColumn
     */
    public void setFromColumn(int fromColumn) {
        this.fromColumn = fromColumn;
    }

    /******************************************************************
     * Sets just the toRow field of a Move object
     * @param toRow
     */
    public void setToRow(int toRow) {
        this.toRow = toRow;
    }

    /******************************************************************
     * Sets just the toColumn field of a Move object
     * @param toColumn
     */
    public void setToColumn(int toColumn) {
        this.toColumn = toColumn;
    }

    /******************************************************************
     * To string method of a move
     * @return
     */
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn="
                + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }


}