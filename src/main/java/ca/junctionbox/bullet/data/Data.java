package ca.junctionbox.bullet.data;

/** Utility class for data frames.
 * Date: 01/10/2013
 * Time: 01:23
 */
public final class Data {
    /**
     * Stub constructor to prevent this class from being instantiated.
     */
    private Data() { }


    /** Factory method for creating an default sized dataframe.
     *
     * @return - a default sized data frame.
     */
    public static Frame frame() {
        return new Frame();
    }

    /**
     *
     * @param filename - name of the file to read.
     * @return
     */
    public static Frame readCsv(final String filename) {
        return null;
    }

    public static Frame readTsv(final String filename) {
        return null;
    }

    public static Frame readW3C(final String filename) {
        return null;
    }
}

class W3CReader {
    // GZip first two bytes; 0x1f,0x8b
    public void eachLine(final String line) {

    }

    public Frame getFrame() {
        return null;
    }
}