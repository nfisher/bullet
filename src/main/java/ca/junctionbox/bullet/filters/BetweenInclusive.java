package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

public class BetweenInclusive implements Filterable {
    private final long lower;
    private final long upper;

    public BetweenInclusive(final long l, final long u) {
        lower = l;
        upper = u;
    }

    public boolean filter(final long v) {
        return (v >= lower && v <= upper);
    }

    @Override
    public boolean filter(Value v) {
        return filter(v.asLong());
    }
}
