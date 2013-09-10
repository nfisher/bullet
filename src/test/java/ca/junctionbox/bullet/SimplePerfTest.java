package ca.junctionbox.bullet;

import java.util.Date;

/**
 *

 Wed Sep 11 00:18:15 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 raw      0   4,116,054,016   2,094,106,688     3283   4.0   2,250,000,000
 raw      1   4,116,054,016   2,115,589,128     1586   4.0   2,250,000,000
 raw      2   4,116,054,016   2,115,583,704     1548   4.0   2,250,000,000
 raw      3   4,116,054,016   2,115,585,848     1559   4.0   2,250,000,000
 raw      4   4,116,054,016   2,115,585,848     1550   4.0   2,250,000,000
 raw      5   4,116,054,016   2,115,585,848     1651   4.0   2,250,000,000
 raw      6   4,116,054,016   2,115,585,848     1625   4.0   2,250,000,000
 raw      7   4,116,054,016   2,115,585,848     1544   4.0   2,250,000,000
 raw      8   4,116,054,016   2,115,585,848     1601   4.0   2,250,000,000
 raw      9   4,116,054,016   2,115,585,848     1560   4.0   2,250,000,000

 *
 */
public class SimplePerfTest {
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
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        double mean = sum / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "raw", runNum, totalMemory, freeMemory, duration, mean, sum);
    }

    private static int[] createRows() {
        int[] array = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}
