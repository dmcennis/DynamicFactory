/**
 * ParameterFactory
 * Created Jan 26, 2009 - 2:49:40 PM
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

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.Creatable;
import org.dynamicfactory.GenericCreatable;
import org.dynamicfactory.Python;
import org.dynamicfactory.property.InvalidObjectTypeException;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 *
 * @author Daniel McEnnis
 */
public class ParameterFactory<T> extends AbstractFactory<ParameterInternal<T>> {

    private static ParameterFactory instance = null;

    static public ParameterFactory newInstance() {
        if (instance == null) {
            instance = new ParameterFactory(0);
        }

        return instance;
    }

    @Override
    public ParameterFactory<T> prototype() {
        return newInstance();
    }

    private ParameterFactory(int o){
        load();
    }

    public ParameterFactory() {
        super();
        newInstance();
    }

    private void load(){
            ParameterInternal name = new BasicParameter<String>();
            name.setType("Name");
            try {
                name.setParameterClass(String.class);
            } catch (InvalidObjectTypeException e) {
                Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING, String.format("Warning - error occurred: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(), "\n")));
            }
            PropertyRestriction restriction = new PropertyRestriction();
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            restriction.setClassType(String.class);
            name.setRestrictions(restriction);
            name.add("GenericParameter");
            properties.add(name);

            name = new BasicParameter<String>();
            name.setType("ParameterClass");
            try {
                name.setParameterClass(String.class);
            } catch (InvalidObjectTypeException e) {
                Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING, String.format("Warning - error occurred: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(), "\n")));
            }
            restriction = new PropertyRestriction();
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            restriction.setClassType(String.class);
            name.setRestrictions(restriction);
            name.add("BasicParameter");
            properties.add(name);

            name = new BasicParameter<Class>();
            name.setType("Class");
            try {
                name.setParameterClass(Class.class);
            } catch (InvalidObjectTypeException e) {
                Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING, String.format("Warning - error occurred: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(), "\n")));
            }
            restriction = new PropertyRestriction();
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            restriction.setClassType(Class.class);
            name.setRestrictions(restriction);
            name.add(String.class);
            properties.add(name);

            name = new BasicParameter<String>();
            name.setType("Description");
            try {
                name.setParameterClass(String.class);
            } catch (InvalidObjectTypeException e) {
                Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING, String.format("Warning - error occurred: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(), "\n")));
            }
            restriction = new PropertyRestriction();
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            restriction.setClassType(String.class);
            name.setRestrictions(restriction);
            name.add("No Description Provided");
            properties.add(name);

            properties.setDefaultRestriction(new PropertyRestriction());

            newInstance().map.put("BasicParameter", new BasicParameter());

    }

    public ParameterInternal<T> create(String type, Class<T> classType) {
        return create(type, classType, null, properties);
    }

    public ParameterInternal<T> create(String type, Class<T> classType, String description) {
        return create(type, classType, description, properties);
    }

    public ParameterInternal<T> create(String type, Class<T> classType, String description, Properties props) {
        if (type == null) {
            if ((props.get("Name") != null) && (props.get("Name").getParameterClass().getName().contentEquals("java.lang.String"))) {
                type = (String) props.get("Name").getValue().iterator().next();
            } else {
                type = (String) properties.get("Name").getValue().iterator().next();
            }
        }

        if (classType == null) {
            if ((props.get("Class") != null) && (props.get("Class").getParameterClass().getName().contentEquals("java.lang.Class"))) {
                classType = (Class) props.get("Class").getValue().iterator().next();
            } else {
                classType = (Class) properties.get("Class").getValue().iterator().next();
            }
        }

        String parameterType = (String) properties.get("ParameterClass").getValue().iterator().next();
        if ((props != null)&&(props.get("ParameterClass") != null) && (props.get("ParameterClass").getParameterClass().getName().contentEquals("java.lang.String"))) {
            parameterType = (String) props.get("ParameterClass").getValue().iterator().next();
        }

        ParameterInternal ret = null;
        if (newInstance().map.containsKey(parameterType)) {
            ret = ((ParameterInternal<T>)newInstance().map.get(parameterType)).prototype();
        } else {
            ret = new BasicParameter<T>();
            Logger.getLogger(newInstance().getClass().getName()).log(Level.SEVERE, "Requested Parameter type '" + parameterType + "' does not exist");
        }
        ret.setType(type);
        try {
            ret.setParameterClass(classType);
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING, String.format("Warning - error occurred: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(), "\n")));
        }
        if (description == null) {
            if ((props.get("Description") != null) && (props.get("Description").getParameterClass().getName().contentEquals("java.lang.String"))) {
                description = (String) props.get("Description").getValue().iterator().next();
            } else {
                description = type;
            }
        }
        ret.setDescription(description);

        return ret;
    }

    public ParameterInternal<T> create(Parameter p,Properties props){
        PropertiesInternal merge = new PropertiesImplementation();
        if(props != null){
            merge.merge(props);
        }
        try {
            merge.mergeDefaults(properties);
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(newInstance().getClass().getName()).log(Level.WARNING,"Defaults were not merged in as the new parameters contained properties incompatible with the types of the defaults");
        }
        if(p == null){
            return create(merge);
        }
        ParameterInternal ret = create(p.getType(),p.getParameterClass(),p.getDescription(),merge);
        LinkedList l = new LinkedList();
        l.addAll(p.getValue());
        ret.set(p.getType(),p.getParameterClass(),false, l,p.getDescription(),p.getLongDescription());
        return ret;
    }

    public ParameterInternal<T> create(Parameter p){
        return create(p,null);
    }

    public Parameter<String> getClassParameter(){
        return properties.get("ParameterClass");
    }
}
