package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

/** Long Series.
 *
 */
public final class LongSeries implements Series {
    /**
     *
     */
    private final long[] data;
    /**
     *
     */
    private final String name;

    /**
     *
     * @param colName
     * @param colData
     * @return
     */
    public static LongSeries create(final String colName, final long[] colData) {
        return new LongSeries(colName, colData);
    }

    /**
     *
     * @param colName
     * @param colData
     */
    private LongSeries(final String colName, final long[] colData) {
        name = colName;
        data = colData;
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.LONG;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return data.length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String name() {
        return name;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void apply(final Applicable f) {
        LongValue lv = new LongValue();
        for (long row : data) {
            lv.setValue(row);
            f.each(lv);
        }
    }

    @Override
    public void filteredApply(final Applicable f, final Filterable filter) {
        LongValue lv = new LongValue();
        for (long row : data) {
            lv.setValue(row);
            if (filter.filter(lv)) f.each(lv);
        }
    }

    @Override
    public Value row(final int r) {
        return new LongValue(data[r]);
    }

    @Override
    public Series conditionalClone(final BooleanSeries bs) {
        long[] newSeries = new long[bs.getTrueCount()];

        int oldIndex = 0;
        for (int newIndex = 0; newIndex < newSeries.length; newIndex++, oldIndex++) {
            while (!bs.row(oldIndex).asBoolean()) {
                oldIndex++;
            }
            newSeries[newIndex] = data[oldIndex];
        }

        return create(name, newSeries);
    }

    /**
     *
     * @param r -
     * @param v -
     */
    public void row(final int r, final LongValue v) {
        v.setValue(data[r]);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        LongSeries longSeries = (LongSeries) obj;
        boolean same = true;

        for (int i = 0; i < data.length; i++) {
            same = same && (data[i] == longSeries.data[i]);
        }
        return same;
    }
}
