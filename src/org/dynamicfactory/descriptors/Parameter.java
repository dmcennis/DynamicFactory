/*

 * Parameter.java

 *

 * Created on 14 September 2007, 12:31

 *

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
import org.dynamicfactory.descriptors.SyntaxChecker;



/**

 *


 * 

 * Base interface for parameter.  It allows reading of parameter values.  Will 

 * eventaully bne replaced by JavaBeans.

 * @author Daniel McEnnis
 */

public interface Parameter {

    /**

     * Name of this parameter. Typically, this value in a Properties object sets this parameter

     * 

     * @return name of this parameter

     */

    public String getType();

    

    /**

     * Returns the Class type of the parameter.

     * @return Class type of the parameter

     */


    

    /**

     * Returns the current value of this object.  Returns null if no value has been set 

     * and no default value exists. Typically, a parameter has a default value 

     * that will be returned.

     * 

     * @return value of this paramter.

     */

    public List<Object> getValue();

    

    /**

     * Will modification of this parameter cause a structural change in the component

     * that has it?

     * 

     * @return is a structural parameter.

     */

    public boolean isStructural();

    
    public String getDescription();
    
    public Class getParameterClass();
    
    public SyntaxChecker getRestrictions();
    
    public boolean check(Property property);
    
    public boolean check(String type, Object value);
    
    public boolean check(String type, List value);
    
    public boolean check(Parameter type);

    public void add(Object value);
    
    public void add(List value);
    
    public void set(Property property);
    
    public void clear();
    
    public Parameter duplicate();
    
    public Object get();

    public void set(Object value);

    public void set(List value);

}

