package ca.junctionbox.bullet;

import java.util.Date;

/**
 *

 Thu Sep 19 18:22:01 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 th       0   4,116,054,016   1,672,630,552      464   4.0   1,350,000,000
 th       1   4,116,054,016   1,694,112,744      133   4.0   1,350,000,000
 th       2   4,116,054,016   1,672,636,576      132   4.0   1,350,000,000
 th       3   4,116,054,016   1,694,109,624      131   4.0   1,350,000,000
 th       4   4,116,054,016   1,694,109,624      133   4.0   1,350,000,000
 th       5   4,116,054,016   1,694,109,624      136   4.0   1,350,000,000
 th       6   4,116,054,016   1,694,109,624      132   4.0   1,350,000,000
 th       7   4,116,054,016   1,694,109,624      134   4.0   1,350,000,000
 th       8   4,116,054,016   1,694,109,624      133   4.0   1,350,000,000
 th       9   4,116,054,016   1,694,109,624      132   4.0   1,350,000,000

 *
 */
public class ThreadedPerfTest {
    public static final int NUM_PROCESSORS = Runtime.getRuntime().availableProcessors();

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

    private static void perfRun(final int runNum) throws InterruptedException {
        int threadCount =  NUM_PROCESSORS;
        final long[] array = createRows();

        final long start = System.currentTimeMillis();
        ThreadedSum[] threadedSums = new ThreadedSum[threadCount];
        int workLen = array.length / threadCount;
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        for (int i = 0; i < threadCount; i++) {
            int o = i * workLen;
            int l = workLen;
            if (o + l > array.length) l = array.length -  o;

            threadedSums[i] = new ThreadedSum(array, i * workLen, l);
        }

        for (int n = 0; n < threadCount; n++) {
            threadedSums[n].start();
        }

        long s = 0;
        for (int j = 0; j < threadCount; j++) {
            threadedSums[j].join();
            s += threadedSums[j].getResult();
        }

        double mean = s / array.length;

        final long duration = System.currentTimeMillis() - start;

        System.out.format("%1$-5s %2$4s %3$,15d %4$,15d %5$8s %6$5s %7$,15d\n",
                "th", runNum, totalMemory, freeMemory, duration, mean, s);
    }

    private static long[] createRows() {
        long[] array = new long[PerfTest.ROWS];
        for (int i = 0; i < PerfTest.ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}


final class ThreadedSum extends Thread {
    private final long[] array;
    private final int offset;
    private final int length;
    private long result = 0;

    public ThreadedSum(final long[] a, final int o, final int l) {
        super();
        array = a;
        offset = o;
        length = l;
    }

    @Override
    public void run() {
        final int max = offset + length;
        for (int i = offset; i < max; i++) {
            result += array[i];
        }
    }

    public long getResult() {
        return result;
    }
}