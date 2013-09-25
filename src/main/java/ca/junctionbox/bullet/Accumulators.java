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
    public static long min(final Data df, final String colName) {
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

    /** utility function to calculate sd.
     *
     * @param df - data frame to apply the sd() operation to.
     * @param colName - column name to calculate the sd of.
     * @return - the sd of the specified column.
     */
    public static double sd(final Data df, final String colName) {
        StdDev sd = new StdDev();
        df.apply(sd, colName);
        return sd.getResult();
    }

    /** naively calculated variance using a numerically unstable method.
     *
     *
     * @param df - data frame to apply the variancen() operation against.
     * @param colName - column name to calculate the variance of.
     * @param mean - the mean of the specified column.
     * @return - the variance of the specified column.
     */
    public static double variancen(final Data df, final String colName, final double mean) {
        NaiveVariance var = new NaiveVariance(mean);
        df.apply(var, colName);
        return var.getResult();
    }

    /** Knuth calculated variance.
     *
     * @param df - data frame.
     * @param colname - column to have variancek applied to.
     * @return - the calculated variance.
     */
    public static double variancek(final Data df, final String colname) {
        KnuthVariance var = new KnuthVariance();
        df.apply(var, colname);
        return var.getResult();
    }

    /**
     *
     * @param df - data frame to apply the mean() operation against.
     * @param colName - column name to calculate the mean of.
     * @return - the mean of the column.
     */
    public static double mean(final Data df, final String colName) {
        Mean mean = new Mean();
        df.apply(mean, colName);
        return mean.getResult();
    }

    /**
     *
     * @param df - data frame to apply the meanw() operation against.
     * @param colName - column to calculate the mean of.
     * @return - the weighted mean of the column.
     */
    public static double meanw(final Data df, final String colName) {
        WeightedMean mean = new WeightedMean();
        df.apply(mean, colName);
        return mean.getResult();
    }
}
