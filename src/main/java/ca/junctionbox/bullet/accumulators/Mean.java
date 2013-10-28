package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

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
    public void each(final long i) {
        n++;
        mean = mean + (1.0 / n) * (i - mean);
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
