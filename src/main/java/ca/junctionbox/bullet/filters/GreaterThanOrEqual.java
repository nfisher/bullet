package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;

/**
 * Date: 27/10/2013
 * Time: 19:15
 */
public class GreaterThanOrEqual implements Filterable {


    private final long comparison;

    public GreaterThanOrEqual(final long c) {
        comparison = c;
    }

    @Override
    public boolean filter(long v) {
        return (v >= comparison);
    }
}
