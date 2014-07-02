/**
 * Properties
 * Created Jan 25, 2009 - 7:45:59 PM
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

import java.util.List;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.descriptors.Parameter;

/**
 *
 * @author Daniel McEnnis
 */
public interface Properties {

    void add(String type, Object value);

    void add(String type, List value);

    void set(String type, Object value);

    void set(String type, List value);

    Properties duplicate();
    
    List<Parameter> get();

    Parameter get(String string);

    SyntaxChecker getDefaultRestriction();

    void set(Property value);

    boolean check(Parameter type);
    
    boolean check(Properties props);
    
}
