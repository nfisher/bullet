package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;

/**
 * Date: 10/09/2013
 * Time: 19:43
 */
public class IntegerColumn implements Column {
    private final int[] columnData;
    private final String columnName;

    public static IntegerColumn create(final String colName, final int[] colData) {
        return new IntegerColumn(colName, colData);
    }

    private IntegerColumn(final String colName, final int[] colData) {
        columnName = colName;
        columnData = colData;
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.INTEGER;  //To change body of implemented methods use File | Settings | File Templates.
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
    public void apply(Applicable f) {
        for (int row : columnData) {
            f.each(row);
        }
    }
}
