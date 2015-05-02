package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.LongSeries;

import java.util.Date;

/**
 *

 Thu Sep 19 18:26:18 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 cvn      0   4,116,054,016   1,672,630,552      259   4.0   1,350,000,000
 cvn      1   4,116,054,016   1,715,587,144      246   4.0   1,350,000,000
 cvn      2   4,116,054,016   1,715,587,496      246   4.0   1,350,000,000
 cvn      3   4,116,054,016   1,694,113,520      245   4.0   1,350,000,000
 cvn      4   4,116,054,016   1,715,584,352      235   4.0   1,350,000,000
 cvn      5   4,116,054,016   1,715,584,352      237   4.0   1,350,000,000
 cvn      6   4,116,054,016   1,715,584,352      236   4.0   1,350,000,000
 cvn      7   4,116,054,016   1,715,584,376      234   4.0   1,350,000,000
 cvn      8   4,116,054,016   1,715,584,376      238   4.0   1,350,000,000
 cvn      9   4,116,054,016   1,715,584,376      241   4.0   1,350,000,000

 *
 */
public class ColumnValuePerfTest {

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
        final long[] array = createRows();

        final long start = System.currentTimeMillis();
        LongSeries longColumn = LongSeries.create("test", array);
        long sum = 0;
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        for (int i = 0; i < array.length; i++) {
            sum += longColumn.row(i).asLong();
        }
        double mean = sum / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "cvn", runNum, totalMemory, freeMemory, duration, mean, sum);
    }

    private static long[] createRows() {
        long[] array = new long[PerfTest.ROWS];
        for (int i = 0; i < PerfTest.ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}
