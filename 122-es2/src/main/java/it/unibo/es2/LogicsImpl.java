package it.unibo.es2;

import java.util.stream.IntStream;

/**
 * Implementation of Logic interface.
 */
public class LogicsImpl implements Logics {

    private final int maxValue;
    private final boolean[][] matrix;

    /**
     * Constructor.
     * 
     * @param size the size of the matrix
     */
    public LogicsImpl(final int size) {
        maxValue = size;
        this.matrix = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.matrix[row][col] = false;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final int row, final int col) {
        if (this.matrix[row][col]) {
            this.matrix[row][col] = false;
            return false;
        } else {
            this.matrix[row][col] = true;
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        final boolean rowIsTrue = rowToQuit();
        final boolean colIsTrue = colToQuit();

        return rowIsTrue || colIsTrue;
    }

    private boolean rowToQuit() {
        return IntStream.range(0, this.maxValue)
                        .anyMatch(r ->
                            IntStream.range(0, this.maxValue)
                                     .allMatch(c -> this.matrix[r][c]));
    }

    private boolean colToQuit() {
        return IntStream.range(0, this.maxValue)
                        .anyMatch(c ->
                            IntStream.range(0, this.maxValue)
                                     .allMatch(r -> this.matrix[r][c]));
    }
}
