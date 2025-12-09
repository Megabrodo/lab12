package it.unibo.es3;

/**
 * Interface used as the logic part of the application.
 */
public interface Logics {

    /**
     * Changes value in matrix replicating the values.
     */
    void replicate();

    /**
     * Checks the appropiate cell to see its content.
     * 
     * @param n coordinates of the cell
     * @return the state of the cell (true or false)
     */
    boolean checkCell(Pair<Integer, Integer> n);

    /**
     * Functional getter.
     * 
     * @return the current matrix
     */
    boolean[][] getMatrix(); 

    /**
     * Measures if it's time to quit the application (i.e., full row or full coloumn true).
     * 
     * @return true if it's time to quit
     */
    boolean toQuit();
}
