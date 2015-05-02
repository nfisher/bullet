package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

public class BetweenExclusive implements Filterable {
    private final long lower;
    private final long upper;

    public BetweenExclusive(final long l, final long u) {
        lower = l;
        upper = u;
    }

    public boolean filter(long v) {
        return (v > lower && v < upper);
    }

    @Override
    public boolean filter(final Value v) {
        return filter(v.asLong());
    }
}
