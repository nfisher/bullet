package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/** Calculates the standard deviation of a column.
 *
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class StdDev implements Applicable {
    // TODO: (NF 2013-09-25) Should probably use a factory method and create an accumulator interface.
    private final KnuthVariance variance = new KnuthVariance();

    @Override
    public void each(final long i) {
        variance.each(i);
    }

    /**
     *
     * @return
     */
    public double getResult() {
        return Math.sqrt(variance.getResult());
    }
}
