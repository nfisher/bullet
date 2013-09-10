package ca.junctionbox.bullet.accumulators;

import ca.junctionbox.bullet.Applicable;

/** Sum stuff up.
 *
 * Date: 10/09/2013
 * Time: 19:45
 */
public final class Sum implements Applicable {
    /** cumulative sum.
     *
     */
    private long total = 0;

    @Override
    public void each(final int i) {
        // TODO: (NF 2013-09-10) Handle overflow.
        total += i;
    }

    /**
     *
     * @param f - value to accumulate.
     * @return
     */
    public float each(final float f) {
        return f;
    }

    /**
     *
     * @param d - value to accumulate.
     * @return
     */
    public double each(final double d) {
        return d;
    }

    /**
     *
     * @return
     */
    public long getResult() {
        return total;
    }
}
