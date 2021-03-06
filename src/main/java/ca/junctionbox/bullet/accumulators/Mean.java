package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.data.Value;

/**
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class Mean implements Applicable {
    /**
     * cumulative mean.
     */
    private double mean = 0;

    /**
     * cumulative count.
     */
    private int n = 0;


    @Override
    public void each(final Value value) {
        n++;
        mean = mean + (1.0 / n) * (value.asLong() - mean);
    }

    /**
     *
     * @return the mean of the iterated collection.
     */
    public double getResult() {
        return mean;
    }

    /**
     *
     * @return - the number of iterations passed through this object.
     */
    public int getN() {
        return n;
    }
}
