package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/**
 * Date: 14/10/2013
 * Time: 23:20
 */
public class Count implements Applicable {
    private long count = 0;

    public void each(final long v) {
        count++;
    }

    public long getResult() {
        return count;
    }
}
