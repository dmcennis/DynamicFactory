/*
 * Copyright (c) 2009 Daniel McEnnis.
 *     All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS
 *  IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied.  See the License for the specific language
 *  governing permissions and limitations under the License.
 *
 * For more about this software visit:
 *
 *      https://github.com/dmcennis/DynamicFactory
 *
 */
/**
 * QueryFactory
 * Created Jan 5, 2009 - 11:13:55 PM
 */
package org.dynamicfactory.propertyQuery;

import org.dynamicfactory.property.Property;

/**
 *
 * @author Daniel McEnnis
 */
public interface PropertyQuery extends Comparable{

    public boolean execute(Property property);

    public PropertyQuery prototype();

    public void exportQuery(java.io.Writer writer);

    public void importQuery(java.io.Reader reader);

}
