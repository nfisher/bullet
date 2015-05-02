package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

/**
 *
 */
public class GreaterThan implements Filterable {
    /** number to compare the set against.
     *
     */
    private final long comparison;

    /**
     *
     * @param c - value to be compared.
     */
    public GreaterThan(final long c) {
        comparison = c;
    }

    /**
     *
     * @param v - value to test if it's greater than.
     * @return
     */
    public boolean filter(final long v) {
        return (v > comparison);
    }

    @Override
    public boolean filter(final Value v) {
        return filter(v.asLong());
    }
}
