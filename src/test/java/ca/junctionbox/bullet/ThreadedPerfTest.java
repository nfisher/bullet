package ca.junctionbox.bullet;

import java.util.Date;

/**
 *

 Thu Sep 12 21:31:07 BST 2013
 type   run     totalMemory      freeMemory duration  mean             sum
 th       0   4,116,054,016   2,072,630,480      119   4.0   2,250,000,000
 th       1   4,116,054,016   2,094,105,560      112   4.0   2,250,000,000
 th       2   4,116,054,016   2,094,105,648      117   4.0   2,250,000,000
 th       3   4,116,054,016   2,094,102,440      128   4.0   2,250,000,000
 th       4   4,116,054,016   2,094,102,440      116   4.0   2,250,000,000
 th       5   4,116,054,016   2,094,102,440      116   4.0   2,250,000,000
 th       6   4,116,054,016   2,094,102,440      115   4.0   2,250,000,000
 th       7   4,116,054,016   2,094,102,440      115   4.0   2,250,000,000
 th       8   4,116,054,016   2,094,102,440      114   4.0   2,250,000,000
 th       9   4,116,054,016   2,094,102,440      118   4.0   2,250,000,000

 *
 */
public class ThreadedPerfTest {
    public static final int ROWS = 500 * 1000 * 1000;
    public static final int NUM_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(final String[] args) throws InterruptedException {
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

    private static void perfRun(final int runNum) throws InterruptedException {
        int threadCount =  NUM_PROCESSORS;
        final int[] array = createRows();


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

    private static int[] createRows() {
        int[] array = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            array[i] = i % 10;
        }
        return array;
    }
}


final class ThreadedSum extends Thread {
    private final int[] array;
    private final int offset;
    private final int length;
    private long result = 0;

    public ThreadedSum(final int[] a, final int o, final int l) {
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