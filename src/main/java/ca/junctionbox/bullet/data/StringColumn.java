package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

/** A column that contains exclusively strings.
 *
 * Date: 10/09/2013
 * Time: 19:44
 */
public final class StringColumn implements Column {
    /**
     *
     */
    private final String columnName;
    /**
     *
     */
    private final String[] columnData;

    /**
     *
     * @param colName
     * @param colData
     * @return
     */
    public static StringColumn create(final String colName, final String [] colData) {
        return new StringColumn(colName, colData);
    }

    /**
     *
     * @param colName -
     * @param colData -
     */
    private StringColumn(final String colName, final String [] colData) {
        columnName = colName;
        columnData = colData;
    }

    /**
     *
     * @return
     */
    public ValueTypes type() {
        return ValueTypes.STRING;
    }

    /**
     *
     * @return
     */
    public int size() {
        return columnData.length;
    }

    @Override
    public String name() {
        return columnName;
    }

    @Override
    public void apply(final Applicable f) {
        for (String row : columnData) {
            // TODO: (NF 2013-10-14) Implement this.
            //f.each(row);
        }
    }

    @Override
    public void filteredApply(final Applicable f, final Filterable filter) {
        for (String row : columnData) {
            // TODO: (NF 2013-10-14) Implement this.
            //if (filter.filter(row)) f.each(row);
        }
    }

    @Override
    public Value row(final int r) {
        // TODO (NF 2013-09-13) Watch the GC fly with this little diddy.
        // Should probably inject a fly-weight that's created per thread.
        return new StringValue(columnData[r]);
    }
}
