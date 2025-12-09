package it.unibo.es1;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int maxValue;
    private final List<Integer> values;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        maxValue = size;
        this.values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            values.add(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return List.copyOf(this.values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return values.stream()
                .map(t -> t < maxValue)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        values.set(elem, values.get(elem) + 1);
        return values.get(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return values.stream()
                .map(t -> t.toString()) // NOPMD: No, using Integer::toString generates another error
                .collect(Collectors.joining("|", "<<", ">>"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return values.stream()
                .allMatch(t -> t.equals(values.getFirst()));
    }
}
