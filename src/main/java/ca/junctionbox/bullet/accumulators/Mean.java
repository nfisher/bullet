package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/**
 * Date: 10/09/2013
 * Time: 19:45
 */
public class Mean implements Applicable {
    private final int columnCount;
    private int cumulativeSum = 0;

    public Mean(final int colCount) {
        columnCount = colCount;
    }

    @Override
    public void each(int i) {
        // TODO: (NF 2013-09-10) need to modify this to ensure it is numerically stable.
        cumulativeSum += i;
    }

    public double getResult() {
        return cumulativeSum /columnCount;
    }
}
