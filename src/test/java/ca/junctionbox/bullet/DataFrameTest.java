package ca.junctionbox.bullet;

import ca.junctionbox.bullet.accumulators.Mean;
import ca.junctionbox.bullet.data.Data;
import ca.junctionbox.bullet.data.Frame;
import ca.junctionbox.bullet.data.StringColumn;
import ca.junctionbox.bullet.data.ValueTypes;
import org.junit.Before;
import org.junit.Test;

import static ca.junctionbox.bullet.Accumulators.*;
import static ca.junctionbox.bullet.Filters.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * ca.junctionbox.bullet - for every panda that won't screw to save it's species.
 */
public class DataFrameTest {
    Frame df;

    @Before
    public void setUp() {
        df = Data.frame();
    }

    @Test
    public void should_create_empty_data_frame() {
        assertThat("rowCount should be", df.rowCount(), is(0));
        assertThat("instance should be", Data.frame(), is(instanceOf(Frame.class)));
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
    public void should_allow_multiple_columns_to_be_set() {
        String names[] = { "Nathan", "Mark", "James", "Ian" };
        long[] ages = { 12, 35, 25, 30 };
        df.col("names", names)
          .col("ages", ages);
        assertThat("colCount should be", df.colCount(), is(2));
    }

    @Test
    public void should_return_expected_results_when_accumulators_are_applied() {
        long[] ages = { 30, 35, 25, 30 };
        df.col("ages", ages);
        assertThat("mean should be", mean(df, "ages"), closeTo(30.0, 0.0001));
        assertThat("sum should be", sum(df, "ages"), is(120L));
        assertThat("min should be", min(df, "ages"), is(25L));
        assertThat("count should be", count(df, "ages"), is(4));
        assertThat("variancek should be", variancek(df, "ages"), closeTo(16.66664295, 0.0001));

        assertThat("stddev should be", sd(df, "ages"), closeTo(4.08248, 0.0001));
        assertThat("gt sum should be", sum(df, "ages", gt(29)), is(95L));
        assertThat("lt count should be", count(df, "ages", lt(31)), is(3L));
        assertThat("gte count should be", count(df, "ages", gte(30)), is(3L));
        assertThat("lte count should be", count(df, "ages", lte(30)), is(3L));
        assertThat("between count should be", count(df, "ages", between(25, 31)), is(2L));
        assertThat("inclusive between count should be", count(df, "ages", between(25, 30, true)), is(3L));
    }

    @Test
    public void new_mean_should_return_0() {
        assertThat(new Mean().getResult(), closeTo(0.0, 0.0001));
    }

    @Test
    public void should_return_expected_variance_for_dog_height() {
        final long[] height = { 600, 470, 170, 430, 300 };
        df.col("height", height);
        final double mean = mean(df, "height");
        assertThat("height mean should be", mean, closeTo(394.0, 0.0001));
        assertThat("height variancen should be", variancen(df, "height", mean), closeTo(27130, 0.00001));
        assertThat("height variancek should be", variancek(df, "height"), closeTo(27130, 0.00001));
    }

    @Test
    public void should_return_expected_mean_for_random_range() {
        long[] range = { 1, 399, 200000, 3, 40000 };
        df.col("random", range);
        assertThat("random range mean should be", mean(df, "random"), closeTo(48080.6, 0.001));
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


