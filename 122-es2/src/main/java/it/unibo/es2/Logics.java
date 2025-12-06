package it.unibo.es2;

/**
 * Interface used as the logic part of the application.
 */
public interface Logics {

    /**
     * Changes value in matrix giving it the opposite of what he had before.
     * 
     * @param row the row of the chosen slot
     * @param col the coloumn of the chosen slot
     * @return the state of the slot after the change
     */
    boolean hit(int row, int col);

    /**
     * Measures if it's time to quit the application (i.e., full row or full coloumn true).
     * 
     * @return true if it's time to quit
     */
    boolean toQuit();
}
