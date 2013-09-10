package ca.junctionbox.bullet;

import ca.junctionbox.bullet.accumulators.*;
import ca.junctionbox.bullet.data.Data;

/** Utility class to hold some of the standard operators.
 *
 * Date: 10/09/2013
 * Time: 19:39
 */
public final class Accumulators {
    /** Marker of an integer operation.
     *
     */
    public static final int INT_OP = 0;

    /** Marker of a long operation.
     *
     */
    public static final long LONG_OP = 0;

    /** Marker for a float operation.
     *
     */
    public static final float FLOAT_OP = 0.0f;

    /** Marker for a double operation.
     *
     */
    public static final double DOUBLE_OP = 0.0;

    /**
     *
     */
    private Accumulators() { }

    /** Integer based sum.
     *
     *
     * @param df - data frame to apply the sum() operation to.
     * @param colName - column name to calculate the sum of.
     * @return - the sum of all columns.
     */
    public static long sum(final Data df, final String colName) {
        Sum sum = new Sum();
        df.apply(sum, colName);
        // TODO: (NF 2013-09-10) Need to handle overflow.
        return sum.getResult();
    }

    /**
     *
     * @param df - data frame to apply the min() operation to.
     * @param colName - column name to calculate the min of.
     * @return - the minimum value in the column.
     */
    public static int min(final Data df, final String colName) {
        Min min = new Min();
        df.apply(min, colName);
        return min.getResult();
    }

    /**
     *
     * @param df - data frame to count the columns of.
     * @param colName - column name to count tee-hee
     * @return
     */
    public static int count(final Data df, final String colName) {
        return df.rowCount();
    }

    /** utility function to calculate stddev.
     *
     * @param df - data frame to apply the stddev() operation to.
     * @param colName - column name to calculate the stddev of.
     * @return - the stddev of the specified column.
     */
    public static double stddev(final Data df, final String colName) {
        StdDev sd = new StdDev();
        df.apply(sd, colName);
        return sd.getResult();
    }

    /**
     *
     * @param df - data frame to apply the variance() operation against.
     * @param colName - column name to calculate the variance of.
     * @return - the variance of the specified column.
     */
    public static double variance(final Data df, final String colName) {
        Variance var = new Variance();
        df.apply(var, colName);
        return var.getResult();
    }

    /**
     *
     * @param df - data frame to apply the mean() operation against.
     * @param colName - column name to calculate the mean of.
     * @return
     */
    public static double mean(final Data df, final String colName) {
        Mean mean = new Mean(df.rowCount());
        df.apply(mean, colName);
        return mean.getResult();
    }
}
