package ca.junctionbox.bullet;

import ca.junctionbox.bullet.filters.*;

/** Utility class for various filters.
 *
 */
public final class Filters {
    /**
     *
     */
    private Filters() { }


    /**
     *
     * @param c - number to compare if greater than.
     * @return greater than filter.
     */
    public static Filterable gt(final long c) {
        return new GreaterThan(c);
    }

    public static Filterable gte(final long c) {
        return new GreaterThanOrEqual(c);
    }

    /**
     *
     * @param c - number to compare if less than.
     * @return less than filter.
     */
    public static Filterable lt(final long c) {
        return new LessThan(c);
    }

    public static Filterable lte(final long c) {
        return new LessThanOrEqual(c);
    }

    public static Filterable between(final long lower, final long upper) {
        return new BetweenExclusive(lower, upper);
    }

    public static Filterable between(final long lower, final long upper, final boolean isInclusive) {
        if (isInclusive) {
            return new BetweenInclusive(lower, upper);
        } else {
            return new BetweenExclusive(lower, upper);
        }
    }
}
