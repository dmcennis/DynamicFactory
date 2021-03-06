/**
 * PropertiesInternal
 * Created Jan 25, 2009 - 7:48:09 PM
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
package org.dynamicfactory.descriptors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.property.InvalidObjectTypeException;

import java.util.List;

/**
 *
 * @author Daniel McEnnis
 */
public interface PropertiesInternal extends Properties, Creatable<PropertiesInternal>{

    void add(String type, List value)throws InvalidObjectTypeException;

    void add(String type, Class c, List value)throws InvalidObjectTypeException;

    void set(String type, Object value)throws InvalidObjectTypeException;

    void set(String type, List value)throws InvalidObjectTypeException;

    void set(String type, Class c, Object value)throws InvalidObjectTypeException;

    void set(String type, Class c, List value)throws InvalidObjectTypeException;

    void set(String type, List value, String description)throws InvalidObjectTypeException;

    void set(String type, Class c, Object value, String description)throws InvalidObjectTypeException;

    void set(String type, Class c, List value, String description)throws InvalidObjectTypeException;

    void set(String type, List value, String description, String longDescription)throws InvalidObjectTypeException;

    void set(String type, Class c, Object value, String description, String longDescription)throws InvalidObjectTypeException;

    void set(String type, Class c, List value, String description, String longDescription)throws InvalidObjectTypeException;

    void add(ParameterInternal parameter);

    void set(ParameterInternal parameter);

    void add(String type, Object value)throws InvalidObjectTypeException;

    void add(String name, Class type, Object value)throws InvalidObjectTypeException;
        
    void remove(String type);
    
    void replace(Parameter type);

    void setDefaultRestriction(SyntaxObject restriction);

    SyntaxObject getDefaultRestriction();
    
    PropertiesInternal prototype();

    ParameterInternal get(String string);

    List<ParameterInternal> iterate();

    void clear();
}
