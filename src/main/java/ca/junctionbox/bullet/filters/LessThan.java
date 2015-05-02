package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

/**
 * Date: 14/10/2013
 * Time: 23:32
 */
public class LessThan implements Filterable {
    private final long comparison;

    public LessThan(final long c) {
        comparison = c;
    }

    public boolean filter(final long v) {
        return (v < comparison);
    }

    @Override
    public boolean filter(Value v) {
        return filter(v.asLong());
    }
}
