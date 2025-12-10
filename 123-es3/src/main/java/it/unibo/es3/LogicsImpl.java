package it.unibo.es3;

import java.util.Arrays;
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
        randomFirst();
    }

    private void randomFirst() {
        double row;
        double col;
        for (int i = 0; i < 3; i++) {
            row = Math.random() * maxValue;
            col = Math.random() * maxValue;
            //Alternativamente si può usare:
            //x = ThreadLocalRandom.current().nextInt(maxValue);
            //evito anche il casting.
            this.matrix[(int) row][(int) col] = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replicate() {
        final boolean[][] copy = matrixCopy();
        for (int row = 0; row < maxValue; row++) {
            for (int col = 0; col < maxValue; col++) {
                if (copy[row][col]) {
                    checkNeighbors(row, col);
                }
            }
        }
    }

    private boolean[][] matrixCopy() {
        final boolean[][] copy = new boolean[maxValue][maxValue];
        for (int i = 0; i < maxValue; i++) {
            copy[i] = Arrays.copyOf(this.matrix[i], maxValue);
        }
        return copy;
    }

    private void checkNeighbors(final int row, final int col) {
        for (int cRow = row - 1; cRow < row + 2; cRow++) {
            for (int cCol = col - 1; cCol < col + 2; cCol++) {
                if (cRow >= 0 && cRow < maxValue && cCol >= 0
                    && cCol < maxValue && !this.matrix[cRow][cCol]) {
                        this.matrix[cRow][cCol] = true;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkCell(final Pair<Integer, Integer> button) {
        return this.matrix[button.x()][button.y()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean[][] getMatrix() {
        return matrixCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return Arrays.stream(this.matrix)
                     .flatMap(r -> IntStream.range(0, maxValue).mapToObj(c -> r[c]))
                     .allMatch(n -> n);
    }
}
