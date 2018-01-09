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
    
    protected HashMap<String,Creatable<Type>> map = new HashMap<String,Creatable<Type>>();
    protected PropertiesInternal properties = new PropertiesImplementation();
    
    public Type create(Properties props){
        if((props != null)&&(props.quickCheck("ClassName",String.class))){
            if(map.containsKey(props.quickGet("ClassName"))) {
                return map.get(props.quickGet("ClassName")).prototype(props);
            }else{
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING,String.format("Class %s is unknown. Using the default instead", props.quickGet("ClassName")));
                return map.get("Default").prototype(props);
            }
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Class parameter is missing. Using the default instead");
            return map.get("Default").prototype(props);
        }
    }

    public Type create(){
        return create(properties);
    }

    public Type create(String name){
        PropertiesInternal props = properties.prototype();
        return create(name,props);
    }

    public Type create(String name, PropertiesInternal props){
        try {
            props.add("FactoryName",name);
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER(this.getClass().getName()): FactoryName property passed to this Factory should not have a property 'FactoryName' of type other than string");
        }
        return create(props);
    }

    public abstract AbstractFactory<Type> prototype();

    public AbstractFactory<Type> prototype(Properties props) {
        return prototype();
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
    
    public void addType(String type,Creatable<Type> prototype){
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

    public Collection<String> getKnownTypes(){
        LinkedList<String> ret = new LinkedList<String>();
        ret.addAll(map.keySet());
        return ret;
    }

    public Parameter getClassParameter() {
        return properties.get("ClassName");
    }

}
