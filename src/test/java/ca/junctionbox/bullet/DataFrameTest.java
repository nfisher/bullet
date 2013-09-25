package ca.junctionbox.bullet;

import ca.junctionbox.bullet.accumulators.Mean;
import ca.junctionbox.bullet.data.Data;
import ca.junctionbox.bullet.data.StringColumn;
import ca.junctionbox.bullet.data.ValueTypes;
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
        long[] ages = { 30, 35, 25, 30 };
        df.col("ages", ages);
        assertThat("rowCount should be", df.rowCount(), is(4));
        assertThat("type should be", df.col("ages").type(), is(ValueTypes.LONG));
    }

    @Test
    public void should_return_expected_results_when_accumulators_are_applied() {
        long[] ages = { 30, 35, 25, 30 };
        df.col("ages", ages);
        double mean = mean(df, "ages");
        assertThat("mean should be", mean, closeTo(30.0, 0.0001));
        assertThat("sum should be", sum(df, "ages"), is(120L));
        assertThat("min should be", min(df, "ages"), is(25L));
        assertThat("count should be", count(df, "ages"), is(4));
        assertThat("variancen should be", variancen(df, "ages", mean), closeTo(16.66664295, 0.0001));

        /*
        assertThat("stddev should be", stddev(df, "ages"), closeTo(4.08248, 0.0001));
        Numeric vt = sum(df.col("ages"), gt(30));
        Numeric vt = count(df.col("ages"), lt(30));
        Numeric nt = div(sub(df.col("ages"), min(df.col("ages"))), 1000);
        */
    }

    @Test
    public void new_mean_should_return_0() {
        assertThat(new Mean().getResult(), closeTo(0.0, 0.0001));
    }

    @Test
    public void should_return_expected_variance_for_dog_height() {
        long[] height = { 600, 470, 170, 430, 300 };
        df.col("height", height);
        double mean = mean(df, "height");
        assertThat("height mean should be", mean, closeTo(394.0, 0.0001));
        assertThat("height variancen should be", variancen(df, "height", mean), closeTo(27130, 0.00001));
        assertThat("height variancek should be", variancek(df, "height"), closeTo(27130, 0.00001));
    }

    @Test
    public void should_return_expected_mean_for_random_range() {
        long[] range = { 1, 399, 200000, 3, 40000 };
        df.col("random", range);
        assertThat("random range mean should be", mean(df, "random"), closeTo(48080.6, 0.001));
//        assertThat("random range weighted mean should be", meanw(df, "random"), closeTo(48080.6, 0.001));
    }

    @Test
    public void toString_should_dump_columns() {
        final long[] ages = { 30, 35, 25, 28 };
        final String[] names = { "Nathan", "Mark", "James", "Valeria" };
        df.col("names", names);
        df.col("ages", ages);
        final String expected =
                "names\tages\n" +
                "Nathan\t30\n" +
                "Mark\t35\n" +
                "James\t25\n" +
                "Valeria\t28\n";
        assertThat("string should be formatted correctly", df.toString(), is(equalTo(expected)));
    }
}


