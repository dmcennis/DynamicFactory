/*
 * PropertyValueDB - Created 14/03/2009 - 6:28:24 PM
 */
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

package org.dynamicfactory.property.database;

import java.sql.Connection;
import org.dynamicfactory.propertyQuery.Query.State;

/**
 *
 * @author Daniel McEnnis
 */
public interface PropertyValueDB<Type> {

    public enum ObjectType {GRAPH,LINK,ACTOR};

    public abstract State buildingStatus();
    
    public abstract PropertyValueDB newCopy();

    public abstract void initializeDatabase(Connection con);

    public abstract void setConnection(Connection con);

    public abstract void clearDatabase(Connection con);

    public abstract Type get(int id);

    public abstract void remove(int id);

    public int put(Type object);

    public Class getValueClass();
}
