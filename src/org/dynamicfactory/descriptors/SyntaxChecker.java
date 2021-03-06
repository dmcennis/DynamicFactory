/**
 * SyntaxChecker
 * Created Jan 25, 2009 - 7:38:02 PM
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

/**
 *
 * @author Daniel McEnnis
 */
public interface SyntaxChecker {

    public boolean check(Property property);

    public boolean check(Property property, Object value);
    
    public boolean check(String type, Object value);
    
    public boolean check(String type, List value);
    
    public boolean check(Property property, List value);
    
    public boolean check(Parameter parameter);
    
    public int getMinCount();
    
    public int getMaxCount();
    
    public Class getClassType();
}
