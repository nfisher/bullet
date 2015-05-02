package ca.junctionbox.bullet;

import ca.junctionbox.bullet.accumulators.Mean;
import ca.junctionbox.bullet.data.*;
import org.junit.Test;

import static ca.junctionbox.bullet.Accumulators.*;
import static ca.junctionbox.bullet.Filters.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * ca.junctionbox.bullet - for every panda that won't screw to save it's species.
 */
public class FrameTest {
    @Test
    public void should_create_empty_data_frame() {
        Frame df = Data.frame();
        assertThat("rowCount should be", df.rowCount(), is(0));
        assertThat("instance should be", Data.frame(), is(instanceOf(Frame.class)));
    }

    @Test
    public void should_allow_column_of_strings_to_be_set() {
        final String[] names = { "Nathan", "Mark", "James", "Ian" };
        Frame df = Data.frame();
        df.col("names", names);

        assertThat("rowCount should be", df.rowCount(), is(4));
        assertThat("index should be", df.findIndex("names"), is(0));
        assertThat("col should be", df.col("names"), is(instanceOf(StringSeries.class)));
        assertThat("type should be", df.col("names").type(), is(ValueTypes.STRING));
    }

    @Test
    public void should_allow_column_of_integers_to_be_set() {
        long[] ages = { 30, 35, 25, 30 };
        Frame df = Data.frame();
        df.col("ages", ages);

        assertThat("rowCount should be", df.rowCount(), is(4));
        assertThat("type should be", df.col("ages").type(), is(ValueTypes.LONG));
    }

    @Test
    public void should_allow_multiple_columns_to_be_set() {
        final String[] expectedLabels = { "names", "ages" };

        final String[] names = { "Nathan", "Mark", "James", "Ian" };
        final long[] ages = { 12, 35, 25, 30 };

        Frame df = Data.frame();
        df.col("names", names)
          .col("ages", ages);

        assertThat("colCount should be", df.colCount(), is(2));
        assertThat("column labels should be", df.columns(), is(expectedLabels));
    }

    @Test
    public void should_return_expected_results_when_accumulators_are_applied() {
        final long[] ages = { 30, 35, 25, 30 };
        Frame df = Data.frame();
        df.col("ages", ages);

        assertThat("mean should be", mean(df, "ages"), closeTo(30.0, 0.0001));
        assertThat("sum should be", sum(df, "ages"), is(120L));
        assertThat("min should be", min(df, "ages"), is(25L));
        assertThat("count should be", count(df, "ages"), is(4));
        assertThat("variance should be", variance(df, "ages"), closeTo(16.66664295, 0.0001));

        assertThat("stddev should be", sd(df, "ages"), closeTo(4.08248, 0.0001));
        assertThat("gt sum should be", sum(df, "ages", gt(29)), is(95L));
        assertThat("lt count should be", count(df, "ages", lt(31)), is(3L));
        assertThat("gte count should be", count(df, "ages", gte(30)), is(3L));
        assertThat("lte count should be", count(df, "ages", lte(30)), is(3L));
        assertThat("between count should be", count(df, "ages", between(25, 31)), is(2L));
        assertThat("inclusive between count should be", count(df, "ages", between(25, 30, true)), is(3L));
    }

    @Test
    public void should_subset_frame_correctly() {
        final BooleanSeries expectedBooleans = BooleanSeries.create("", new boolean[]{true, false, false, true});
        final Series expectedAges = LongSeries.create("ages", new long[] {30, 28});
        final StringSeries expectedNames = StringSeries.create("names", new String[] { "Nathan", "Ian" });

        final String[] names = { "Nathan", "Mark", "James", "Ian" };
        final long[] ages = { 30, 35, 25, 28 };
        Frame df = Data.frame();
        df.col("names", names)
          .col("ages", ages);

        BooleanSeries series = df.is("ages", between(25, 31));
        Frame df2 = df.subset(series);

        assertThat("between count should be", series.size(), is(df.rowCount()));
        assertThat("series should be", series, is(equalTo(expectedBooleans)));
        assertThat("subset row count should be", df2.rowCount(), is(2));
        assertThat("subset ages should be", (LongSeries) df2.col("ages"), is(equalTo(expectedAges)));
        assertThat("subset names should be", (StringSeries) df2.col("names"), is(equalTo(expectedNames)));
    }

    @Test
    public void new_mean_should_return_zero() {
        assertThat(new Mean().getResult(), closeTo(0.0, 0.0001));
    }

    @Test
    public void should_return_expected_variance_for_dog_height() {
        final long[] height = { 600, 470, 170, 430, 300 };
        Frame df = Data.frame();
        df.col("height", height);
        assertThat("height mean should be", mean(df, "height"), closeTo(394.0, 0.0001));
        assertThat("height variance should be", variance(df, "height"), closeTo(27130, 0.00001));
    }

    @Test
    public void should_return_expected_mean_for_random_range() {
        final long[] range = { 1, 399, 200000, 3, 40000 };
        Frame df = Data.frame();
        df.col("random", range);
        assertThat("random range mean should be", mean(df, "random"), closeTo(48080.6, 0.001));
    }

    @Test
    public void toString_should_dump_columns() {
        final String expected =
                "names\tages\n" +
                        "Nathan\t30\n" +
                        "Mark\t35\n" +
                        "James\t25\n" +
                        "Valeria\t28\n";

        final long[] ages = { 30, 35, 25, 28 };
        final String[] names = { "Nathan", "Mark", "James", "Valeria" };
        Frame df = Data.frame();
        df.col("names", names);
        df.col("ages", ages);

        assertThat("string should be formatted correctly", df.toString(), is(expected));
    }
}
