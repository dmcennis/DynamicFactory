package org.dynamicfactory;

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
 * Created Jan 26, 2009 - 2:56:20 PM
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.property.InvalidObjectTypeException;

/**
 *
 * @author Daniel McEnnis
 */
public abstract class AbstractFactory<Type> {
    
    protected HashMap<String,Type> map = new HashMap<String,Type>();
    protected PropertiesInternal properties = new PropertiesImplementation();
    
    public abstract Type create(Properties props);

    public Type create(String name){
        Properties props = properties.prototype();
        return create(name,props);
    }

    public Type create(String name, Properties props){
        props.add("FactoryName",name);
        return create(props);
    }

    public void setDefaultProperty(ParameterInternal value) {
        properties.add(value);
    }
    
    public void addDefaultProperty(String type, Object value) throws InvalidObjectTypeException{
        properties.add(type,value);
    }

    public Properties getParameter() {
        return properties;
    }
    
    public Parameter getParameter(String type){
        return properties.get(type);
    }
    
    public void addType(String type,Type prototype){
        if(type == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null org.dynamicfactory.property class name added");
        }   
        if(prototype == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "No org.dynamicfactory.property obejct provided");
        }
        if((type != null)&&(prototype != null)){
            map.put(type,prototype);
        }
    }
    
    public boolean check(Properties props){
        boolean good = true;
        Iterator<Parameter> collection = props.get().iterator();
        while(collection.hasNext()){
            if(!properties.check(collection.next())){
                return false;
            }
        }
        return true;       
    }
    
    public boolean check(Parameter parameter){
        return properties.check(parameter);
    }
    
    public abstract Parameter getClassParameter();
    
    public Collection<String> getKnownTypes(){
        LinkedList<String> ret = new LinkedList<String>();
        ret.addAll(map.keySet());
        return ret;
    }
}
