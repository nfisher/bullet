package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;

/** Integer Column.
 *
 * Date: 10/09/2013
 * Time: 19:43
 */
public final class LongColumn implements Column {
    /**
     *
     */
    private final long[] columnData;
    /**
     *
     */
    private final String columnName;

    /**
     *
     * @param colName
     * @param colData
     * @return
     */
    public static LongColumn create(final String colName, final long[] colData) {
        return new LongColumn(colName, colData);
    }

    /**
     *
     * @param colName
     * @param colData
     */
    private LongColumn(final String colName, final long[] colData) {
        columnName = colName;
        columnData = colData;
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.LONG;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return columnData.length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String name() {
        return columnName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void apply(final Applicable f) {
        for (long row : columnData) {
            f.each(row);
        }
    }

    @Override
    public Value row(final int r) {
        return new LongValue(columnData[r]);
    }

    /**
     *
     * @param r -
     * @param v -
     */
    public void row(final int r, final LongValue v) {
        v.setValue(columnData[r]);
    }
}
