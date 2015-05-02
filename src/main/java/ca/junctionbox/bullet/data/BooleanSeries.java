package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

import static ca.junctionbox.bullet.Accumulators.count;
import static ca.junctionbox.bullet.Filters.ifTrue;

/** A series that contains exclusively boolean values.
 *
 * Date: 14-03-15
 * Time: 06:46
 */
public class BooleanSeries implements Series {
    private final boolean[] data;
    private final String name;
    private int trueCount = -1;

    public static BooleanSeries create(final String name, final boolean[] data) {
        return new BooleanSeries(name, data);
    }

    private BooleanSeries(final String name, final boolean[] data) {
        this.name = name;
        this.data = data;
    }

    public int getTrueCount() {
        if (trueCount == -1) {
            trueCount = (int) count(this, ifTrue());
        }

        return trueCount;
    }

    @Override
    public ValueTypes type() {
        return ValueTypes.BOOLEAN;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void apply(Applicable f) {
        BooleanValue bv = new BooleanValue();
        for (boolean row : data) {
            bv.setValue(row);
            f.each(bv);
        }
    }

    @Override
    public void filteredApply(Applicable f, Filterable filter) {
        BooleanValue bv = new BooleanValue();
        for (boolean row : data) {
            bv.setValue(row);
            if (filter.filter(bv)) f.each(bv);
        }
    }

    @Override
    public Value row(final int r) {
        return new BooleanValue(data[r]);
    }

    @Override
    public Series conditionalClone(BooleanSeries bs) {
        boolean[] newSeries = new boolean[bs.getTrueCount()];

        return create(name, newSeries);
    }

    @Override
    public boolean equals(Object obj) {
        BooleanSeries series = (BooleanSeries) obj;

        //if (series == null) return false;

        if (series.size() != size()) return false;

        boolean matches = true;

        for (int i = 0; i < series.size(); i++) {
            matches = (this.data[i] == series.data[i]) && matches;
        }

        return matches;
    }
}

class BooleanValue implements Value {
    private boolean value;

    public BooleanValue(final boolean value) {
        this.value = value;
    }

    public BooleanValue() {

    }

    @Override
    public ValueTypes type() {
        return ValueTypes.BOOLEAN;
    }

    @Override
    public boolean asBoolean() {
        return value;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    void setValue(boolean value) {
        this.value = value;
    }
}