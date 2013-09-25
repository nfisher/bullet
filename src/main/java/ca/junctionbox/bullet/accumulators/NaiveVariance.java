package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/**
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class NaiveVariance implements Applicable {
    /**
     *
     */
    private final double mean;

    /**
     *
     */
    private double tally = 0;
    /**
     * counter starts at -1 assuming a sample than the population.
     */
    private long counter = -1;

    /**
     *
     * @param m - mean of the collection being evaluated.
     * @param isPopulation - specifies that the variancen is a population rather than a sample.
     */
    public NaiveVariance(final double m, final boolean isPopulation) {
        mean = m;
        counter = (isPopulation) ? 0 : -1;
    }

    /**
     *
     * @param m - mean of collection being evaluated.
     */
    public NaiveVariance(final double m) {
        this(m, false);
    }

    @Override
    public void each(final long i) {
        double diff = i - mean;
        tally += (diff * diff);
        counter++;
    }

    /**
     *
     * @return
     */
    public double getResult() {
        return tally / counter;
    }
}
