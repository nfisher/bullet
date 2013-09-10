package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/** Calculates the standard deviation of a column.
 *
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class StdDev implements Applicable {
    /**
     *
     */
    private double result = 0;

    @Override
    public void each(final int i) {

    }

    /**
     *
     * @return
     */
    public double getResult() {
        return result;
    }
}
