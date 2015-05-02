package ca.junctionbox.bullet.data;

/** Encapsulates a long for returns from a row.
 *
 */
public final class LongValue implements Value {
    /**
     *
     */
    private long value;

    /**
     *
     * @param v - the value this object encapsulates.
     */
    public LongValue(final long v) {
        value = v;
    }

    /**
     *
     */
    public LongValue() {
        this(0);
    }

    /**
     *
     * @return
     */
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.LONG;
    }

    @Override
    public boolean asBoolean() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(final long v) {
        value = v;
    }

    @Override
    public double asDouble() {
        return (double) value;
    }

    @Override
    public long asLong() {
        return value;
    }
}
