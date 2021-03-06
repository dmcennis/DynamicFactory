/*

 * ParameterInternal.java

 *

 * Created on 14 September 2007, 13:26

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

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.SyntaxObject;
import org.dynamicfactory.property.InvalidObjectTypeException;

import java.util.List;


/**

 *


 * 

 * Extension of the SettableParameter and Parameter interface that allows parameter

 * owners to set all parts of the parameter without knowing what specific implementation

 * of parameter is used.

 * @author Daniel McEnnis
 */

public interface ParameterInternal<Type> extends Parameter<Type>{

    /**
     * Sets the name that this parameter will be accessed by
     * 
     * @param name name of the parameter
     */

    public Parameter<Type> setType(String name);

    

    /**
     * Sets the Class that this parameter will hold.  This restriction can be a
     * hard restriction or a soft contract depending on the implementation.
     *
     * @param c Class expected for the parameter's value
     */

    public Parameter<Type> setParameterClass(Class c) throws InvalidObjectTypeException;

    

    /**
     * Set this as a parameter whose changes means the application structure needs to 
     * be rebuilt.  This is of primary interest for GUI development knowing whether 
     * a parameter modification means that dependancy checks need to be performed 
     * again.
     * 
     * @param b is a structural parameter or not.
     */
    public Parameter<Type> setStructural(boolean b);
    
    public Parameter<Type> setDescription(String b);

    public Parameter<Type> setLongDescription(String d);

    public Parameter<Type> setRestrictions(SyntaxObject syntax);
    
    public SyntaxObject getRestrictions();

    @Override
    public ParameterInternal<Type> prototype();

    public List<Type> getValue();

    @Override
    ParameterInternal<Type> prototype(Properties props);

    public Parameter<Type> set(String type, Class parameterClass, boolean structural, List value, String description, String longDecscription);

}

