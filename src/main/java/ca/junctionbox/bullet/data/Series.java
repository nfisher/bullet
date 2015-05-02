package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;
import ca.junctionbox.bullet.Filterable;

/** Base interface for all column types in a data frame.
 *
 * Date: 10/09/2013
 * Time: 19:42
 */
public interface Series {
    String name = "";
    public ValueTypes type();
    public int size();
    public String name();
    public void apply(final Applicable f);
    public void filteredApply(final Applicable f, final Filterable filter);
    public Value row(int r);
    public Series conditionalClone(final BooleanSeries bs);
}
