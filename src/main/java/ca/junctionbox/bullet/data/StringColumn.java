package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;

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
     * @param colName
     * @param colData
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
            //f.each(row);
        }
    }
}
