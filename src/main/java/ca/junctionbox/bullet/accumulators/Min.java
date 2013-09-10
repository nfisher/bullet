package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/** Retrieves the minimum value in the set.
 *
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class Min implements Applicable {
    /**
     *
     */
    private int min = Integer.MAX_VALUE;


    @Override
    public void each(final int i) {
        if (i < min) min = i;
    }

    /**
     *
     * @return
     */
    public int getResult() {
        return min;
    }
}
