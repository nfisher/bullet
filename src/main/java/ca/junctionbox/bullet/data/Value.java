package ca.junctionbox.bullet.data;

/** Interface to wrap a column value.
 *
 * Date: 10/09/2013
 * Time: 22:09
 */
public interface Value {
    /**
     *
     * @return
     */
    ValueTypes type();

    /**
     *
     * @return
     */
    boolean asBoolean();

    /**
     *
     * @return
     */
    double asDouble();

    /**
     *
     * @return
     */
    long asLong();

    /**
     *
     * @return
     */
    String toString();
}
