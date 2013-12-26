package org.mc4j.ems.connection.bean.parameter;

/**
 * An MBean operation parameter.
 *
 * @author Greg Hinkle (ghinkle@users.sourceforge.net)
 * @version $Revision: 620 $($Author: ianpspringer $ / $Date: 2010-08-02 21:03:05 +0200 (Mo, 02 Aug 2010) $)
 */
public interface EmsParameter extends Comparable {
    /**
     * Returns the name of this parameter.
     *
     * @return the name of this parameter
     */
    String getName();

    /**
     * Returns a human readable description of this parameter; may be null.
     *
     * @return a human readable description of this parameter; may be null
     */
    String getDescription();

    /**
     * Returns the type (i.e. fully qualified class name as defined by {@link Class#getName()}) of this parameter.
     *
     * @return the type (i.e. fully qualified class name as defined by {@link Class#getName()}) of this parameter
     */
    String getType();
}
