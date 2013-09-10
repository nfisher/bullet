package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.Data;

import java.util.Date;

import static ca.junctionbox.bullet.Accumulators.sum;

/**
 *

 Wed Sep 11 00:27:09 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 df       0   4,116,054,016   2,094,106,736     2480   4.0   2,250,000,000
 df       1   4,116,054,016   2,115,586,664     1555   4.0   2,250,000,000
 df       2   4,116,054,016   2,094,110,936     1594   4.0   2,250,000,000
 df       3   4,116,054,016   2,115,584,152     1562   4.0   2,250,000,000
 df       4   4,116,054,016   2,115,584,152     1596   4.0   2,250,000,000
 df       5   4,116,054,016   2,115,584,152     1599   4.0   2,250,000,000
 df       6   4,116,054,016   2,115,584,152     1568   4.0   2,250,000,000
 df       7   4,116,054,016   2,115,584,152     1541   4.0   2,250,000,000
 df       8   4,116,054,016   2,115,584,152     1561   4.0   2,250,000,000
 df       9   4,116,054,016   2,115,584,152     1537   4.0   2,250,000,000

 *
 */
public class DataFramePerfTest {
    public static final int ROWS = 500 * 1000 * 1000;

    public static void main(final String[] args) {
        System.err.format("Memory %,d total, %,d free\n",
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().freeMemory());
        System.err.flush();
        System.out.println();
        System.out.println();
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
        final long start = System.currentTimeMillis();
        final int[] array = createRows();
        long sum = 0;
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        Data df = Data.frame();
        df.col("a", array);
        long s = sum(df, "a");
        double mean = s / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "df", runNum, totalMemory, freeMemory, duration, mean, s);
    }

    private static int[] createRows() {
        int[] array = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}
