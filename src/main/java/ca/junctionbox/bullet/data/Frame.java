package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

/** Encapsulates row of a data frame, and provides convenience methods
 * for accessing columns.
 *
 */
public final class Frame {
    /** The starting size for the number of columns and column names.
     *
     */
    public static final int START_SIZE = 64;

    // This is knowingly a naive structure.
    private final String[] columnNames;
    private final Series[] seriesData;

    // should probably create a fly-weight view with all values as final for comparator functions.

    /** retain the current count of columns.
     *
     */
    private volatile int columnCount = 0;

    /** Default constructor for columns
     *
     */
    protected Frame() {
        this(START_SIZE);
    }

    /**
     *
     * @param startSize - the size to intialize the column names and column data to.
     */
    protected Frame(final int startSize) {
        columnNames = new String[startSize];
        seriesData = new Series[startSize];
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    private void appendColumn(final String colName, final Series colData) {
        // TODO: (NF 2013-09-09) This will need work for thread safety.
        columnNames[columnCount] = colName;
        seriesData[columnCount] = colData;
        columnCount++;
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    public Frame col(final String colName, final String[] colData) {
        appendColumn(colName, StringSeries.create(colName, colData));
        return this;
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    public Frame col(final String colName, final long[] colData) {
        appendColumn(colName, LongSeries.create(colName, colData));
        return this;
    }

    /**
     *
     * @return
     */
    public int rowCount() {
        if (seriesData[0] != null) {
            return seriesData[0].size();
        }
        return 0;
    }

    /**
     *
     * @return number of columns.
     */
    public int colCount() {
        return columnCount;
    }

    /**
     *
     * @param columnName -
     * @return
     */
    public Series col(final String columnName) {
        int colIndex = findIndex(columnName);
        // don't like returning null.
        if (colIndex >= columnCount) return null;

        return seriesData[colIndex];
    }

    /**
     *
     * @param colName -
     * @return
     */
    public int findIndex(final String colName) {
        int i = 0;
        for (; i <= columnCount; i++) {
            if (colName.equals(columnNames[i])) break;
        }

        return i;
    }

    /**
     *
     * @param colName
     * @param filterable
     * @return a series of boolean values which match the result of the filter.
     */
    public BooleanSeries is(final String colName, final Filterable filterable) {
        WiggyWoo f = new WiggyWoo(rowCount(), filterable);
        apply(f, colName);
        return f.getResult();
    }

    /**
     *
     * @param f - the functor to be applied.
     * @param colName - name of the column to apply the binder too.
     */
    public void apply(final Applicable f, final String colName) {
        Series c = col(colName);
        c.apply(f);
    }

    /**
     *
     * @param f - function to apply value to.
     * @param colName - column name to extract values from.
     * @param filter - filter to limit values with.
     */
    public void filteredApply(final Applicable f, final String colName, final Filterable filter) {
        Series c = col(colName);
        c.filteredApply(f, filter);
    }

    /**
     *
     */
    public String toString() {
        // for now will lazily set 1 tab between columns and newlines between rows.
        StringBuilder sb = new StringBuilder();
        int c = 0;
        for (String col : columnNames) {
            if (c >= columnCount) break;
            sb.append(col);
            sb.append((++c < columnCount) ? "\t" : "\n");
        }

        for (int r = 0; r < rowCount(); r++) {
            for (c = 0; c < columnCount;) {
                sb.append(seriesData[c].row(r));
                sb.append((++c < columnCount) ? "\t" : "\n");
            }
        }

        return sb.toString();
    }

    public final String[] columns() {
        final String[] columns = new String[colCount()];
        for (int i = 0; i < columns.length; i++) columns[i] = columnNames[i];
        return columns;
    }

    public Frame subset(final BooleanSeries bs) {
        Frame df = Data.frame();

        for (int columnIndex = 0; columnIndex < colCount(); columnIndex++) {
            Series column = this.seriesData[columnIndex];
            Series newColumn = column.conditionalClone(bs);

            df.col(newColumn.name(), newColumn);
        }

        return df;
    }

    /**
     *
     * @param name
     * @param series
     */
    private Frame col(String name, Series series) {
        appendColumn(name, series);
        return this;
    }
}

class WiggyWoo implements Applicable {
    private boolean[] series;
    private final Filterable filter;
    private int count = 0;
    LongValue lv = new LongValue();

    public WiggyWoo(final int rowCount, final Filterable filter) {
        this.series = new boolean[rowCount];
        this.filter = filter;
    }

    public void each(final long v) {
        lv.setValue(v);
        series[count] = filter.filter(lv);
        count++;
    }

    public BooleanSeries getResult() {
        return BooleanSeries.create("", series);
    }

    @Override
    public void each(final Value v) {
        each(v.asLong());
    }
}