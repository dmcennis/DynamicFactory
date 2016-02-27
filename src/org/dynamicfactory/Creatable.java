/**
 * Created by Daniel McEnnis on 2/27/2016
 * <p/>
 * Copyright Daniel McEnnis 2015
 */

package org.dynamicfactory;

import org.dynamicfactory.descriptors.Properties;

/**
 * Default Description Interface Creatable
 */
public interface Creatable<Type> {

    public Type prototype();

    public Type prototype(Properties props);
}
