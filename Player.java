package project3;

/******************************************************************
 *
 * Player enum that holds white or black
 *
 * @author Michael Fink
 * @version Winter 2019
 */
public enum Player {
    BLACK, WHITE;

    /**
     * Return the {@code Player} whose turn is next.
     *
     * @return the {@code Player} whose turn is next
     */
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;

        //	return this == BLACK ? WHITE : BLACK;
    }
}