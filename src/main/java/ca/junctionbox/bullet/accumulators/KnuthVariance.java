package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.data.LongValue;
import ca.junctionbox.bullet.data.Value;

/** Calculates variance using Knuth's algorithm.
 * Date: 25/09/2013
 * Time: 22:56
 */
public final class KnuthVariance implements Applicable {
    /**
     *
     */
    private final Mean mean = new Mean();
    private final LongValue longValue = new LongValue();

    /**
     *
     */
    private double m2 = 0;

    /**
     *
     */
    private int n = 0;

    public void each(final long x) {
        double delta = x - mean.getResult();
        longValue.setValue(x);
        mean.each(longValue);
        m2 = m2 + delta * (x - mean.getResult());
    }

    /**
     *
     * @return - the calculated variance.
     */
    public double getResult() {
        return m2 / (mean.getN() - 1);
    }

    @Override
    public void each(final Value v) {
        each(v.asLong());
    }
}
