/*

 * Properties.java

 *

 * Created on 1 May 2007, 14:47

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


package org.dynamicfactory.property;



//import nz.ac.waikato.mcennis.rat.graph.model.Model;


import org.dynamicfactory.Creatable;

/**

 *


 * Class for defining a org.dynamicfactory.property on either actor, link, or graph

 * @author Daniel McEnnis

 * 
 */

public interface Property<Type> extends java.io.Serializable, Comparable<Property>, Creatable<Property>{//}, Model {

    

    public static final int ADD_VALUE = 0;

    /**

     * Add a new value to this org.dynamicfactory.property

     * 

     * @param value new object to be added

     */

    public void add(Type value) throws InvalidObjectTypeException;

    

    /**

     * return this org.dynamicfactory.property's unique id

     * 

     * @return id of this object

     */

    public String getType();

    

    /**

     * return array of all values. Returns an empty array if no properties are present

     * 

     * @return array of org.dynamicfactory.property values

     */

    public java.util.List<Type> getValue();
    
    
    public void setType(String id);

    public void setClass(Class classType) throws InvalidObjectTypeException;
    

    /**

     * create a deep copy of this org.dynamicfactory.property

     * 

     * @return deep copy of this org.dynamicfactory.property

     */

    public Property prototype();

    

    /**

     * Return the type of class that this org.dynamicfactory.property represents.  All objects in

     * one org.dynamicfactory.property must be of the same type.

     * @return name of class types of objects

     */

    public Class getPropertyClass();

    

    

}

