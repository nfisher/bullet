package ca.junctionbox.bullet;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ca.junctionbox.bullet - for every panda that won't screw to save it's species.
 */
public class DataFrameTest {
    Data df;
    @Before
    public void setUp() {
        df = Data.frame();
    }

    @Test
    public void should_create_empty_data_frame() {
        assertThat(df.size(), is(0));
        assertThat(Data.frame(), is(instanceOf(Data.class)));
    }

    @Test
    public void should_allow_column_of_strings_to_be_set() {
        String names[] = { "Nathan", "Mark", "James", "Ian" };

        df.col("names", names);
        assertThat(df.size(), is(4));
    }

    @Test
    public void should_allow_column_of_integers_to_be_set() {
        int ages[] = { 30, 35, 25, 30 };
        df.col("ages", ages);
        assertThat(df.size(), is(4));
    }

    @Test
    public void should_df() {
        int ages[] = { 30, 35, 25, 30 };
        assertThat(df.size(), is(0));
    }
}

class Data {
    public static final int START_SIZE=16;

    private String[] columnNames = new String[START_SIZE];
    private Column[] columnData = new Column[START_SIZE];
    private int columnCount = 0;

    private Data() { }

    public static Data frame() {
        return new Data();
    }

    private void appendColumn(final String colName, final Column colData) {
        columnNames[columnCount] = colName;
        columnData[columnCount] = colData;
        columnCount++;
    }

    public void col(final String colName, final String[] colData) {
        appendColumn(colName, StringColumn.create(colName, colData));
    }

    public void col(final String colName, final int[] colData) {
        appendColumn(colName, IntegerColumn.create(colName, colData));
    }

    public Integer size() {
        if (columnCount > 0) {
            return columnData[0].size();
        }
        return 0;
    }

    public void apply(final String column, final Applicable f) {

    }
}

enum ColumnTypes {
    STRING,
    INTEGER,
    DOUBLE
}

interface Column {

    public ColumnTypes type();
    public int size();
    public String name();
}

class StringColumn implements Column {
    private final String columnName;
    private final String columnData[];


    public static StringColumn create(final String colName, final String [] colData) {
        return new StringColumn(colName, colData);
    }

    private StringColumn(final String colName, final String [] colData) {
        columnName = colName;
        columnData = colData;
    }

    public ColumnTypes type() {
        return ColumnTypes.STRING;
    }

    public int size() {
        return columnData.length;
    }

    @Override
    public String name() {
        return columnName;
    }
}

class IntegerColumn implements Column {
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
    public ColumnTypes type() {
        return ColumnTypes.INTEGER;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return columnData.length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String name() {
        return columnName;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

interface Applicable {
    void each(final Object o);
}
