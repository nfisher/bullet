package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.Data;
import ca.junctionbox.bullet.data.Frame;

import java.util.Date;

import static ca.junctionbox.bullet.Accumulators.sum;

/**
 *

 Thu Sep 12 21:27:25 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 df       0   4,116,054,016   2,094,106,664      237   4.0   2,250,000,000
 df       1   4,116,054,016   2,115,586,664      224   4.0   2,250,000,000
 df       2   4,116,054,016   2,094,110,936      224   4.0   2,250,000,000
 df       3   4,116,054,016   2,115,584,152      223   4.0   2,250,000,000
 df       4   4,116,054,016   2,115,584,152      223   4.0   2,250,000,000
 df       5   4,116,054,016   2,115,584,152      223   4.0   2,250,000,000
 df       6   4,116,054,016   2,115,584,152      222   4.0   2,250,000,000
 df       7   4,116,054,016   2,115,584,152      229   4.0   2,250,000,000
 df       8   4,116,054,016   2,115,584,152      229   4.0   2,250,000,000
 df       9   4,116,054,016   2,115,584,152      256   4.0   2,250,000,000

 *
 */
public class DataFramePerfTest {

    public static void main(final String[] args) {
        System.err.format("Memory %,d total, %,d free\n",
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().freeMemory());
        System.err.flush();
        System.out.flush();
        System.out.println(new Date());
        System.out.printf("%1$-5s %2$4s %3$15s %4$15s %5$8s %6$5s %7$15s\n",
                          "type", "run", "totalMemory", "freeMemory", "duration", "mean", "sum");
        for (int i = 0; i < 10; i++) {
            System.gc();
            perfRun(i);
        }
        System.gc();
        System.err.format("Memory %,d total, %,d free\n",
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().freeMemory());
    }

    private static void perfRun(final int runNum) {
        final long[] array = createRows();

        final long start = System.currentTimeMillis();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        Frame df = Data.frame();
        df.col("a", array);
        long s = sum(df, "a");
        double mean = s / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "df", runNum, totalMemory, freeMemory, duration, mean, s);
    }

    private static long[] createRows() {
        long[] array = new long[PerfTest.ROWS];
        for (int i = 0; i < PerfTest.ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}
