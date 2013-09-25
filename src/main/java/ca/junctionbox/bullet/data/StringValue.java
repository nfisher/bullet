package ca.junctionbox.bullet.data;

/** Encapsulates a string for returns from a row.
 *
 */
public final class StringValue implements Value {
    /**
     *
     */
    private final String value;

    /**
     *
     * @param v - value to embed.
     */
    public StringValue(final String v) {
        value = v;
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.STRING;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return
     */
    public String toString() {
        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public double asDouble() {
        return Double.NaN;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public long asLong() {
        // TODO: (NF 2013-09-13) This is going to be a problem.
        return Long.MIN_VALUE;
    }
}
