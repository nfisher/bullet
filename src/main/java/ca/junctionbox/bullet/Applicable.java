package ca.junctionbox.bullet;

import ca.junctionbox.bullet.data.Value;

/** Interface for an iterative binder.
 *
 * Date: 10/09/2013
 * Time: 19:42
 */
public interface Applicable {
    /** Not faster than a bullet but, perhaps a panda?
     *
     * @param v - the value.
     */
    void each(final Value v);
}
