package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;

public class LessThanOrEqual implements Filterable {
    private final long comparison;

    public LessThanOrEqual(final long c) {
        comparison = c;
    }

    @Override
    public boolean filter(final long v) {
        return (v <= comparison);
    }
}
