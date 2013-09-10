package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/**
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class Variance implements Applicable {
    private double result;

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
