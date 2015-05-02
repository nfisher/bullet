package ca.junctionbox.bullet.filters;

import ca.junctionbox.bullet.Filterable;
import ca.junctionbox.bullet.data.Value;

/**
 * Date: 14-03-17
 * Time: 23:22
 */
public class True implements Filterable {
    @Override
    public boolean filter(final Value v) {
        return v.asBoolean();
    }
}
