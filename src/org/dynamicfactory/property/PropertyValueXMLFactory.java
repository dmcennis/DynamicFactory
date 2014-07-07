/**
 * PropertyValueXMLFactory
 * Created Jan 26, 2009 - 9:22:39 PM
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
import org.dynamicfactory.descriptors.*;
//import org.dynamicfactory.property.xml.AssociativeMiningItemsXML;
//import org.dynamicfactory.property.xml.AttributeXML;
import org.dynamicfactory.property.xml.DoubleXML;
import org.dynamicfactory.property.xml.FileXML;
//import org.dynamicfactory.property.xml.InstanceXML;
//import org.dynamicfactory.property.xml.InstancesXML;
import org.dynamicfactory.property.xml.IntegerXML;
import org.dynamicfactory.property.xml.LongXML;
import org.dynamicfactory.property.xml.PropertyValueXML;
import org.dynamicfactory.property.xml.StringXML;
import org.dynamicfactory.property.xml.URLXML;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyValueXMLFactory extends AbstractFactory<PropertyValueXML>{
    static PropertyValueXMLFactory instance = null;

    public static PropertyValueXMLFactory newInstance(){
        if(instance == null){
            instance = new PropertyValueXMLFactory();
        }
        return instance;
    }
    
    private PropertyValueXMLFactory(){
        ParameterInternal name = ParameterFactory.newInstance().create("ValueClass", String.class);
        SyntaxObject check = SyntaxCheckerFactory.newInstance().create(1,1,null,String.class);
        name.setRestrictions(check);
        name.add("String");
        properties.add(name);
        
//        map.put("AssociativeMiningItems",new AssociativeMiningItemsXML());
//        map.put("Attribute",new AttributeXML());
        map.put("Double",new DoubleXML());
        map.put("File",new FileXML());
//        map.put("Instance",new InstanceXML());
//        map.put("Instances",new InstancesXML());
        map.put("Integer",new IntegerXML());
        map.put("Long",new LongXML());
        map.put("String",new StringXML());
        map.put("URL",new URLXML());
    }
    
    public PropertyValueXML create(Class classType){
        return create(classType.getSimpleName(),properties);
    }
    
    public PropertyValueXML create(String classType){
        return create(classType,properties);
    }
    
    public PropertyValueXML create(String classType, Properties props){
        if(classType==null){
        if ((props.get("ValueClass") != null) && (props.get("QueryClass").getParameterClass().getName().contentEquals(String.class.getName()))) {
            classType = (String) props.get("ValueClass").getValue().iterator().next();
        } else {
            classType = (String) properties.get("ValueClass").getValue().iterator().next();
        }
        }

        if(map.containsKey(classType)){
            return map.get(classType).newCopy();
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"PropertyXML class '"+classType+"' not found - assuming StringXML");
            return new StringXML();
        }
    }

    @Override
    public PropertyValueXML create(Properties props) {
        return create(null,props);
    }

    public Parameter getClassParameter(){
        return properties.get("ValueClass");
    }
}
