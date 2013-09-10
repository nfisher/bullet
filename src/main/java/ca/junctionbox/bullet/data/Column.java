package ca.junctionbox.bullet.data;

import ca.junctionbox.bullet.Applicable;

/** Base interface for all column types in a data frame.
 *
 * Date: 10/09/2013
 * Time: 19:42
 */
public interface Column {
    public ValueTypes type();
    public int size();
    public String name();
    public void apply(Applicable f);
}
