package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.ValueTypes;
import ca.junctionbox.bullet.data.Data;
import ca.junctionbox.bullet.data.StringColumn;
import org.junit.Before;
import org.junit.Test;

import static ca.junctionbox.bullet.Accumulators.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;
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
        assertThat("rowCount should be", df.rowCount(), is(0));
        assertThat("instance should be", Data.frame(), is(instanceOf(Data.class)));
    }

    @Test
    public void should_allow_column_of_strings_to_be_set() {
        String names[] = { "Nathan", "Mark", "James", "Ian" };

        df.col("names", names);
        assertThat("rowCount should be", df.rowCount(), is(4));
        assertThat("index should be", df.findIndex("names"), is(0));
        assertThat("col should be", df.col("names"), is(instanceOf(StringColumn.class)));
        assertThat("type should be", df.col("names").type(), is(ValueTypes.STRING));
    }

    @Test
    public void should_allow_column_of_integers_to_be_set() {
        int ages[] = { 30, 35, 25, 30 };
        df.col("ages", ages);
        assertThat("rowCount should be", df.rowCount(), is(4));
        assertThat("type should be", df.col("ages").type(), is(ValueTypes.INTEGER));
    }

    @Test
    public void should_return_expected_results_when_accumulators_are_applied() {
        int ages[] = { 30, 35, 25, 30 };
        df.col("ages", ages);
        assertThat("sum should be", sum(df, "ages"), is(120L));
        assertThat("min should be", min(df, "ages"), is(25));
        assertThat("count should be", count(df, "ages"), is(4));
        assertThat("mean should be", mean(df, "ages"), closeTo(30.0, 0.0001));
        assertThat("variance should be", variance(df, "ages"), closeTo(16.66664295, 0.0001));

        /*
        assertThat("stddev should be", stddev(df, "ages"), closeTo(4.08248, 0.0001));
        Numeric vt = sum(df.col("ages"), gt(30));
        Numeric vt = count(df.col("ages"), lt(30));
        Numeric nt = div(sub(df.col("ages"), min(df.col("ages"))), 1000);
        */
    }

    @Test
    public void toString_should_dump_columns() {
        final int[] ages = { 30, 35, 25, 30 };
        final String[] names = { "Nathan", "Mark", "James", "Ian" };
        df.col("names", names);
        df.col("ages", ages);
        System.out.println(df);
        final String expected =
                "names\tages\n" +
                "Nathan\t30\n" +
                "Mark\t35\n" +
                "James\t25\n" +
                "Ian\t30\n";
        assertThat("string should be formatted correctly", df.toString(), is(equalTo(expected)));
    }
}


