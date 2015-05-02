package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

/** A column that contains exclusively strings.
 *
 * Date: 10/09/2013
 * Time: 19:44
 */
public final class StringSeries implements Series {
    private final String name;
    private final String[] data;

    /**
     *
     * @param name
     * @param data
     * @return
     */
    public static StringSeries create(final String name, final String [] data) {
        return new StringSeries(name, data);
    }

    /**
     *
     * @param name -
     * @param data -
     */
    private StringSeries(final String name, final String[] data) {
        this.name = name;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public ValueTypes type() {
        return ValueTypes.STRING;
    }

    /**
     *
     * @return
     */
    public int size() {
        return data.length;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void apply(final Applicable f) {
        for (String row : data) {
            // TODO: (NF 2013-10-14) Implement this.
            //f.each(row);
        }
    }

    @Override
    public void filteredApply(final Applicable f, final Filterable filter) {
        for (String row : data) {
            // TODO: (NF 2013-10-14) Implement this.
            //if (filter.filter(row)) f.each(row);
        }
    }

    @Override
    public Value row(final int r) {
        // TODO (NF 2013-09-13) Watch the GC fly with this little diddy.
        // Should probably inject a fly-weight that's created per thread.
        return new StringValue(data[r]);
    }

    @Override
    public Series conditionalClone(final BooleanSeries bs) {
        String[] newSeries = new String[bs.getTrueCount()];

        int oldIndex = 0;
        for (int newIndex = 0; newIndex < newSeries.length; newIndex++, oldIndex++) {
            while (!bs.row(oldIndex).asBoolean()) {
                oldIndex++;
            }
            newSeries[newIndex] = data[oldIndex];
        }

        return create(name, newSeries);
    }

    @Override
    public boolean equals(Object obj) {
        StringSeries stringSeries = (StringSeries) obj;
        boolean same = true;

        for (int i = 0; i < data.length; i++) {
            same = same && (data[i].equals(stringSeries.data[i]));
        }
        return same;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]).append("\n");
        }
        return sb.toString();
    }
}
