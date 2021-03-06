package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

/**
 * Date: 27/10/2013
 * Time: 19:15
 */
public class GreaterThanOrEqual implements Filterable {


    private final long comparison;

    public GreaterThanOrEqual(final long c) {
        comparison = c;
    }

    public boolean filter(final long v) {
        return (v >= comparison);
    }

    @Override
    public boolean filter(final Value v) {
        return filter(v.asLong());
    }
}
