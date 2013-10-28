package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;

public class BetweenInclusive implements Filterable {
    private final long lower;
    private final long upper;

    public BetweenInclusive(final long l, final long u) {
        lower = l;
        upper = u;
    }

    @Override
    public boolean filter(final long v) {
        return (v >= lower && v <= upper);
    }
}
