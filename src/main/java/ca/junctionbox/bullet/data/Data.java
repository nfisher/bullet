package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;

/** Encapsulates row of a data frame, and provides convenience methods
 * for accessing columns.
 *
 * Date: 10/09/2013
 * Time: 19:42
 */
public final class Data {
    /** The starting size for the number of columns and column names.
     *
     */
    public static final int START_SIZE = 16;

    // This is knowingly a naive structure.
    private final String[] columnNames;
    private final Column[] columnData;

    // should probably create a fly-weight view with all values as final for comparator functions.

    /** retain the current count of columns.
     *
     */
    private volatile int columnCount = 0;

    /** Default constructor for columns
     *
     */
    private Data() {
        this(START_SIZE);
    }

    /**
     *
     * @param startSize - the size to intialize the column names and column data to.
     */
    private Data(final int startSize) {
        columnNames = new String[startSize];
        columnData = new Column[startSize];
    }

    /** Factory method for creating an default sized dataframe.
     *
     * @return - a default sized data frame.
     */
    public static Data frame() {
        return new Data();
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    private void appendColumn(final String colName, final Column colData) {
        // TODO: (NF 2013-09-09) This will need work for thread safety.
        columnNames[columnCount] = colName;
        columnData[columnCount] = colData;
        columnCount++;
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    public void col(final String colName, final String[] colData) {
        appendColumn(colName, StringColumn.create(colName, colData));
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    public void col(final String colName, final long[] colData) {
        appendColumn(colName, LongColumn.create(colName, colData));
    }

    /**
     *
     * @return
     */
    public Integer rowCount() {
        if (columnData[0] != null) {
            return columnData[0].size();
        }
        return 0;
    }

    /**
     *
     * @param columnName -
     * @return
     */
    public Column col(final String columnName) {
        int colIndex = findIndex(columnName);
        // don't like returning null.
        if (colIndex >= columnCount) return null;

        return columnData[colIndex];
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
     * @param f - the functor to be applied.
     * @param colName - name of the column to apply the binder too.
     */
    public void apply(final Applicable f, final String colName) {
        Column c = col(colName);
        c.apply(f);
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
                sb.append(columnData[c].row(r));
                sb.append((++c < columnCount) ? "\t" : "\n");
            }
        }

        return sb.toString();
    }
}
