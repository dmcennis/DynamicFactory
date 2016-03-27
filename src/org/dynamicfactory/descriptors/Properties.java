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
public interface Properties extends Comparable<Properties>{

    void add(String type, Object value);

    void add(String type, List value);

    void add(String type, Class c, Object value);

    void add(String type, Class c, List value);

    void set(String type, Object value);

    void set(String type, List value);

    void set(String type, Class c, Object value);

    void set(String type, Class c, List value);

    void set(String type, List value, String description);

    void set(String type, Class c, Object value, String description);

    void set(String type, Class c, List value, String description);

    void set(String type, List value, String description, String longDescription);

    void set(String type, Class c, Object value, String description, String longDescription);

    void set(String type, Class c, List value, String description, String longDescription);

    Properties prototype();
    
    List<Parameter> get();

    Parameter get(String string);

    SyntaxChecker getDefaultRestriction();

    void set(Property value);

    boolean check(Parameter type);
    
    boolean check(Properties props);

    /**
     * Given a source set of properties, merge in any non-conflicting properties from the right side
     *
     * @param props set of defaults to merge in
     * @return new Properties object combining the two types
     */
    Properties merge(Properties props);

    boolean quickCheck(String s,Class type);

    /**
     * Because so many expect this the other way around
     * @param s
     * @param type
     * @return
     */
    boolean checkQuick(String s,Class type);

    Object quickGet(String s);

    /**
     * Because so many expect this the other way around
     * @param s
     * @return
     */
    Object getQuick(String s);

}
