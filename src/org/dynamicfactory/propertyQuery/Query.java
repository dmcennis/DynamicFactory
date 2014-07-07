/*
 * Query - Created 1/02/2009 - 3:14:57 AM
 * Copyright Daniel McEnnis, see license.txt
 */

package org.dynamicfactory.propertyQuery;

import java.lang.Comparable;

/**
 *
 * @author Daniel McEnnis
 */
public interface Query extends Comparable{
 enum State {UNINITIALIZED,READY,LOADING};
}
