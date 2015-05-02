package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.Value;

/**
 *
 */
public interface Filterable {
    /**
     *
     * @param v - value to test against the filter.
     * @return true if the value is to be included, false excludes it.
     */
    boolean filter(final Value v);
}
