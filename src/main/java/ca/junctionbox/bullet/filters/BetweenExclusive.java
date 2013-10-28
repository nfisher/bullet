package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;

public class BetweenExclusive implements Filterable {
    private final long lower;
    private final long upper;

    public BetweenExclusive(final long l, final long u) {
        lower = l;
        upper = u;
    }

    @Override
    public boolean filter(long v) {
        return (v > lower && v < upper);
    }
}
