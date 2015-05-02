package ca.junctionbox.bullet.data;

import org.junit.Test;

import static ca.junctionbox.bullet.Accumulators.count;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Date: 14-03-17
 * Time: 22:12
 */
public class BooleanSeriesTest {
    @Test
    public void should_not_be_equal() {
        BooleanSeries bs1 = BooleanSeries.create("", new boolean[] { });
        BooleanSeries bs2 = BooleanSeries.create("", new boolean[] { true });
        BooleanSeries bs3 = BooleanSeries.create("", new boolean[] {false});

        assertThat(bs1, is(not(equalTo(bs2))));
        assertThat(bs2, is(not(equalTo(bs3))));
    }

    @Test
    public void should_be_equal() {
        BooleanSeries bs1 = BooleanSeries.create("", new boolean[] {true});
        BooleanSeries bs2 = BooleanSeries.create("", new boolean[] {true});
        assertThat(bs1, is(equalTo(bs2)));
    }

    @Test
    public void should_be_appliable() {
        BooleanSeries bs = BooleanSeries.create("", new boolean[] { true, false, true });
        assertThat(count(bs), is(3L));
    }
}
