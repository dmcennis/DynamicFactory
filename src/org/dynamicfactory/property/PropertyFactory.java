/*

 * PropertyFactory.java

 *

 * Created on 1 May 2007, 17:03

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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.BasicParameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.descriptors.PropertyRestriction;

/**

 *

 * 

 * Class for creating Property objects

 * @author Daniel McEnnis

 */
public class PropertyFactory extends AbstractFactory<Property>{

    static PropertyFactory instance = null;

    /**
    
     * Create a reference to the singelton PropertyFactory
    
     * 
    
     * @return reference to the PropertyFactory
    
     */
    public static PropertyFactory newInstance() {

        if (instance == null) {

            instance = new PropertyFactory();

        }

        return instance;

    }

    @Override
    public AbstractFactory prototype() {
        return newInstance();
    }

    /** Creates a new instance of PropertyFactory */
    public PropertyFactory() {
        ParameterInternal name = new BasicParameter();
        name.setType("PropertyClass");
        name.setParameterClass(String.class);
        PropertyRestriction restrictionPart = new PropertyRestriction();
        restrictionPart.setMinCount(1);
        restrictionPart.setMaxCount(1);
        restrictionPart.setClassType(String.class);
        name.setRestrictions(restrictionPart);
        name.add("BasicProperty");
        properties.add(name);

        name = new BasicParameter();
        name.setType("PropertyValueClass");
        name.setParameterClass(Class.class);
        restrictionPart = new PropertyRestriction();
        restrictionPart.setMinCount(1);
        restrictionPart.setMaxCount(1);
        restrictionPart.setClassType(Class.class);
        name.setRestrictions(restrictionPart);
        name.add(String.class);
        properties.add(name);

        name = new BasicParameter();
        name.setType("PropertyID");
        name.setParameterClass(String.class);
        restrictionPart = new PropertyRestriction();
        restrictionPart.setMinCount(1);
        restrictionPart.setMaxCount(1);
        restrictionPart.setClassType(String.class);
        name.setRestrictions(restrictionPart);
        name.add("Default");
        properties.add(name);

        properties.setDefaultRestriction(new PropertyRestriction());

        map.put("BasicProperty", new BasicProperty("Default", String.class));
    }

    public Property create(String propertyClass, String id, Class objectType) {
        return create(propertyClass, id, objectType, properties);
    }
    
    public Property create(Properties props){
        return create(null,null,null,props);
    }
    

    public Property create(String propertyClass, String id, Class objectType, Properties props) {
        if (props == null){
            props = properties;
        }
        if (id == null) {
            if ((props != null)&&(props.get("PropertyID") != null) && (props.get("PropertyID").getParameterClass().getName().contentEquals("java.lang.String"))) {
                id = (String) props.get("PropertyID").getValue().iterator().next();
            } else {
                id = (String) properties.get("PropertyID").getValue().iterator().next();
            }
        }
        if (objectType == null) {
            objectType = (Class) properties.get("PropertyValueClass").getValue().iterator().next();
            if ((props != null)&&(props.get("PropertyValueClass") != null) && (props.get("PropertyValueClass").getParameterClass().getName().contentEquals("java.lang.Class"))) {
                objectType = (Class) props.get("PropertyValueClass").getValue().iterator().next();
            }
        }
        Property ret = null;
        if (propertyClass == null){
            propertyClass = (String) properties.get("PropertyClass").getValue().iterator().next();
            if ((props != null)&&(props.get("PropertyClass") != null) && (props.get("PropertyClass").getParameterClass().getName().contentEquals("java.lang.String"))) {
                propertyClass = (String) props.get("PropertyClass").getValue().iterator().next();
            }
        }
        if (map.containsKey(propertyClass)) {
            ret = map.get(propertyClass).prototype();
            ret.setType(id);
            ret.setClass(objectType);
        } else {
            ret = new BasicProperty(id, objectType);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unknown property type '" + propertyClass + "' requested: using BasicProperty as the default");
        }
        return ret;
    }

    public Parameter getClassParameter(){
        return properties.get("PropertyClass");
    }
/**
    
     * Creates a new Property Object. 'PropertyID' is used to set
    
     * the id of the resulting org.dynamicfactory.property.  'PropertyClass' is used to set what
    
     * class of objects can be added as values. A new BasicProperty object with the
    
     * given id and given values class is returned.
    
     * 
    
     * @param props map of initialization properties
    
     * @return new Property object
    
     */
}

