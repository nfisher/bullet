package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.data.Value;

/**
 * Date: 14/10/2013
 * Time: 23:20
 */
public class Count implements Applicable {
    private long count = 0;

    public long getResult() {
        return count;
    }

    @Override
    public void each(Value v) {
        count++;
    }
}
