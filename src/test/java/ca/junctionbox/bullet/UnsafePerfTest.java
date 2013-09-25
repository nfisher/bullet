package ca.junctionbox.bullet;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Date;

/**
 *

 Thu Sep 12 21:28:11 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 raw      0   4,116,054,016   2,094,106,688      225   4.0   2,250,000,000
 raw      1   4,116,054,016   2,115,589,128      227   4.0   2,250,000,000
 raw      2   4,116,054,016   2,094,112,872      223   4.0   2,250,000,000
 raw      3   4,116,054,016   2,115,585,848      219   4.0   2,250,000,000
 raw      4   4,116,054,016   2,115,585,848      227   4.0   2,250,000,000
 raw      5   4,116,054,016   2,115,585,848      221   4.0   2,250,000,000
 raw      6   4,116,054,016   2,115,585,848      220   4.0   2,250,000,000
 raw      7   4,116,054,016   2,115,585,848      219   4.0   2,250,000,000
 raw      8   4,116,054,016   2,115,585,848      225   4.0   2,250,000,000
 raw      9   4,116,054,016   2,115,585,848      226   4.0   2,250,000,000

 *
 */
public class UnsafePerfTest {
    public static final int ROWS = 500 * 1000 * 1000;
    private static final Unsafe unsafe;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

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
        final int[] array = createRows();
        final long start = System.currentTimeMillis();
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
