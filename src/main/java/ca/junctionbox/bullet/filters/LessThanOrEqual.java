package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

public class LessThanOrEqual implements Filterable {
    private final long comparison;

    public LessThanOrEqual(final long c) {
        comparison = c;
    }

    public boolean filter(final long v) {
        return (v <= comparison);
    }

    @Override
    public boolean filter(Value v) {
        return filter(v.asLong());
    }
}
