/**
 * SyntaxCheckerFactory
 * Created Jan 26, 2009 - 2:48:38 PM
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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.property.InvalidObjectTypeException;
import org.dynamicfactory.propertyQuery.NullPropertyQuery;
import org.dynamicfactory.propertyQuery.PropertyQuery;

/**
 *
 * @author Daniel McEnnis
 */
public class SyntaxCheckerFactory extends AbstractFactory<SyntaxObject> {

    private static SyntaxCheckerFactory instance = null;

    static public SyntaxCheckerFactory newInstance() {
        if (instance == null) {
            instance = new SyntaxCheckerFactory();
        }
        return instance;
    }

    private SyntaxCheckerFactory() {
        try {
            ParameterInternal parameter = new BasicParameter();
            parameter.setParameterClass(String.class);
            parameter.setType("SyntaxCheckerClass");
            PropertyRestriction restriction = new PropertyRestriction();
            restriction.setClassType(String.class);
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            parameter.setRestrictions(restriction);
            parameter.add("PropertyRestriction");
            properties.add(parameter);

            parameter = new BasicParameter();
            parameter.setParameterClass(Class.class);
            parameter.setType("ClassType");
            restriction = new PropertyRestriction();
            restriction.setClassType(Class.class);
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            parameter.setRestrictions(restriction);
            parameter.add(Object.class);
            properties.add(parameter);

            parameter = new BasicParameter();
            parameter.setParameterClass(Integer.class);
            parameter.setType("MinCount");
            restriction = new PropertyRestriction();
            restriction.setClassType(Integer.class);
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            parameter.setRestrictions(restriction);
            parameter.add(new Integer(0));
            properties.add(parameter);

            parameter = new BasicParameter();
            parameter.setParameterClass(Integer.class);
            parameter.setType("MaxCount");
            restriction = new PropertyRestriction();
            restriction.setClassType(Integer.class);
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            parameter.setRestrictions(restriction);
            parameter.add(Integer.MAX_VALUE);
            properties.add(parameter);

            parameter = new BasicParameter();
            parameter.setParameterClass(PropertyQuery.class);
            parameter.setType("Test");
            restriction = new PropertyRestriction();
            restriction.setClassType(PropertyQuery.class);
            restriction.setMinCount(1);
            restriction.setMaxCount(1);
            parameter.setRestrictions(restriction);
            NullPropertyQuery query = new NullPropertyQuery();
            query.buildQuery(true);
            parameter.add(query);
            properties.add(parameter);

            properties.setDefaultRestriction(new PropertyRestriction());

            map.put("PropertyRestriction", new PropertyRestriction());
        } catch (InvalidObjectTypeException e) {
            e.printStackTrace();
        }
    }

    public SyntaxObject create(int minCount, int maxCount, PropertyQuery query, Class classType) {
        return create(minCount, maxCount, query, classType, properties);
    }

    @Override
    public AbstractFactory prototype() {
        return newInstance();
    }

    public SyntaxObject create(int minCount, int maxCount, PropertyQuery query, Class classType, Properties props) {
        String type = (String) properties.get("SyntaxCheckerClass").getValue().iterator().next();
        if(props == null){
            props = properties;
        }
        if ((props.get("SyntaxCheckerClass") != null) && (props.get("SyntaxCheckerClass").getParameterClass().getName().contentEquals("java.lang.String"))) {
            type = (String) props.get("SyntaxCheckerClass").getValue().iterator().next();
        }

        SyntaxObject prototype = map.get(type).prototype();
        if(prototype == null){
            prototype = new PropertyRestriction();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Unknown SyntaxObject '"+type+"' requested - assuming PropertyRestriction");
        }
        
        if (query == null) {
            query = (PropertyQuery) properties.get("Test").getValue().iterator().next();
            if ((props.get("Test") != null) && (PropertyQuery.class.isAssignableFrom(props.get("Test").getParameterClass()))) {
                query = (PropertyQuery) props.get("Test").getValue().iterator().next();
            }
        }
        prototype.setTest(query);

        if (classType == null) {
            classType = (Class) properties.get("ClassType").getValue().iterator().next();
            if ((props.get("ClassType") != null) && (props.get("ClassType").getParameterClass().getName().contentEquals("java.lang.Class"))) {
                classType = (Class) props.get("ClassType").getValue().iterator().next();
            }
        }
        prototype.setClassType(classType);
        prototype.setMinCount(minCount);
        prototype.setMaxCount(maxCount);

        return prototype;

    }

    @Override
    public SyntaxObject create(Properties props) {
        int minCount = (Integer) properties.get("MinCount").getValue().iterator().next();
        if ((props != null)&&(props.get("MinCount") != null) && (props.get("MinCount").getParameterClass().getName().contentEquals("java.lang.Integer"))) {
            minCount = (Integer) props.get("MinCount").getValue().iterator().next();
        }

        int maxCount = (Integer) properties.get("MaxCount").getValue().iterator().next();
        if ((props != null)&&(props.get("MaxCount") != null) && (props.get("MaxCount").getParameterClass().getName().contentEquals("java.lang.Integer"))) {
            maxCount = (Integer) props.get("MaxCount").getValue().iterator().next();
        }
        return create(minCount, maxCount, null, null, props);
    }

    public Parameter getClassParameter(){
        return properties.get("SyntaxCheckerClass");
    }
}
