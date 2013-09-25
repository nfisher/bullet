package ca.junctionbox.bullet.data;

/** Encapsulates a double for returns from a row.
 *
 */
public final class DoubleValue implements Value {
    /**
     *
     */
    private final double value;

    /**
     *
     * @param v -
     */
    public DoubleValue(final double v) {
        value = v;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.DOUBLE;
    }

    @Override
    public double asDouble() {
        return value;
    }

    @Override
    public long asLong() {
        return (long) value;
    }
}
