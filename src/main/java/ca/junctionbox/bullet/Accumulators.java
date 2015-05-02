package ca.junctionbox.bullet;

import ca.junctionbox.bullet.accumulators.*;
import ca.junctionbox.bullet.data.Frame;
import ca.junctionbox.bullet.data.Series;

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
    public static long sum(final Frame df, final String colName) {
        final Sum sum = new Sum();
        df.apply(sum, colName);
        // TODO: (NF 2013-09-10) Need to handle overflow.
        return sum.getResult();
    }

    /**
     *
     * @param df - data frame to be summed.
     * @param colName - column to be summed.
     * @param filter - filter for values to be summed.
     * @return - the sum of the values that meet the filter criteria.
     */
    public static long sum(final Frame df,
                           final String colName,
                           final Filterable filter) {

        final Sum sum = new Sum();
        df.filteredApply(sum, colName, filter);
        return sum.getResult();
    }

    /**
     *
     * @param df - data frame to apply the min() operation to.
     * @param colName - column name to calculate the min of.
     * @return - the minimum value in the column.
     */
    public static long min(final Frame df, final String colName) {
        Min min = new Min();
        df.apply(min, colName);
        return min.getResult();
    }

    /**
     *
     * @param df - data frame to count the columns of.
     * @param colName - column name to count tee-hee
     * @return - row count.
     */
    public static int count(final Frame df, final String colName) {
        return df.rowCount();
    }

    /**
     *
     * @param df  - data frame to count the columns of.
     * @param colName  - column name to count tee-hee
     * @param filter - only count values that match filter.
     * @return - filtered row count.
     */
    public static long count(final Frame df, final String colName, final Filterable filter) {
        Count count = new Count();
        df.filteredApply(count, colName, filter);
        return count.getResult();
    }

    /**
     *
     * @param s
     * @return
     */
    public static long count(final Series s) {
        Count count = new Count();
        s.apply(count);
        return count.getResult();
    }

    public static long count(final Series s, final Filterable filter) {
        Count count = new Count();
        s.filteredApply(count, filter);
        return count.getResult();
    }

    /** utility function to calculate sd.
     *
     * @param df - data frame to apply the sd() operation to.
     * @param colName - column name to calculate the sd of.
     * @return - the sd of the specified column.
     */
    public static double sd(final Frame df, final String colName) {
        StdDev sd = new StdDev();
        df.apply(sd, colName);
        return sd.getResult();
    }

    /** Knuth calculated variance.
     *
     * @param df - data frame.
     * @param colname - column to have variance applied to.
     * @return - the calculated variance.
     */
    public static double variance(final Frame df, final String colname) {
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
    public static double mean(final Frame df, final String colName) {
        Mean mean = new Mean();
        df.apply(mean, colName);
        return mean.getResult();
    }
}
