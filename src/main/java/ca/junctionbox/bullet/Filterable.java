package ca.junctionbox.bullet;

/**
 *
 */
public interface Filterable {
    /**
     *
     * @param v - value to test against the filter.
     * @return true if the value is to be included, false excludes it.
     */
    boolean filter(final long v);
}
