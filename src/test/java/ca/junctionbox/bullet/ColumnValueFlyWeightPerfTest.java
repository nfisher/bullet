package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.LongColumn;
import ca.junctionbox.bullet.data.LongValue;

import java.util.Date;

/**
 *

 Thu Sep 19 18:56:57 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 cvf      0   4,116,054,016   1,694,106,576      202   4.0   1,350,000,000
 cvf      1   4,116,054,016   1,715,587,096      198   4.0   1,350,000,000
 cvf      2   4,116,054,016   1,715,587,448      193   4.0   1,350,000,000
 cvf      3   4,116,054,016   1,694,113,472      194   4.0   1,350,000,000
 cvf      4   4,116,054,016   1,715,584,304      195   4.0   1,350,000,000
 cvf      5   4,116,054,016   1,715,584,304      223   4.0   1,350,000,000
 cvf      6   4,116,054,016   1,715,584,304      195   4.0   1,350,000,000
 cvf      7   4,116,054,016   1,715,584,328      202   4.0   1,350,000,000
 cvf      8   4,116,054,016   1,715,584,328      198   4.0   1,350,000,000
 cvf      9   4,116,054,016   1,715,584,328      197   4.0   1,350,000,000

 *
 */
public class ColumnValueFlyWeightPerfTest {

    public static void main(final String[] args) throws InterruptedException {
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
        long sum = 0;
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        LongColumn longColumn = LongColumn.create("test", array);
        LongValue lv = new LongValue();
        for (int i = 0; i < array.length; i++) {
            longColumn.row(i, lv);
            sum += lv.asLong();
        }
        double mean = sum / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "cvf", runNum, totalMemory, freeMemory, duration, mean, sum);
    }

    private static long[] createRows() {
        long[] array = new long[PerfTest.ROWS];
        for (int i = 0; i < PerfTest.ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}
