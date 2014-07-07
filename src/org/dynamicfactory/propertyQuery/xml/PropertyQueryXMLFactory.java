/**
 * PropertyQueryXMLFactory
 * Created Jan 26, 2009 - 11:10:44 PM
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
package org.dynamicfactory.propertyQuery.xml;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.descriptors.*;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyQueryXMLFactory extends AbstractFactory<PropertyQueryXML>{

        static PropertyQueryXMLFactory instance = null;
    
    static public PropertyQueryXMLFactory newInstance(){
        if(instance == null){
            instance = new PropertyQueryXMLFactory();
        }
        return instance;
    }
    
    private PropertyQueryXMLFactory(){
        ParameterInternal name = ParameterFactory.newInstance().create("PropertyQueryXMLClass", String.class);
        SyntaxObject syntax = SyntaxCheckerFactory.newInstance().create(1, 1, null, String.class);
        name.setRestrictions(syntax);
        name.add("NullPropertyQuery");
        properties.add(name);
        
        map.put("NullPropertyQuery",new NullPropertyQueryXML());
//        map.put("DataVectorQuery",new DataVectorQueryXML());
        map.put("NumericQuery",new NumericQueryXML());
        map.put("StringQuery",new StringQueryXML());
        
    }

    @Override
    public PropertyQueryXML create(Properties props) {
        return create(null,props);
    }
    
    public PropertyQueryXML create(String name){
        return create(name,properties);
    }
    
    public PropertyQueryXML create(String name, Properties parameters){
        if(name == null){
        if ((parameters.get("PropertyQueryXMLClass") != null) && (parameters.get("PropertyQueryXMLClass").getParameterClass().getName().contentEquals(String.class.getName()))) {
            name = (String) parameters.get("PropertyQueryXMLClass").getValue().iterator().next();
        } else {
            name = (String) properties.get("PropertyQueryXMLClass").getValue().iterator().next();
        }
        }

        if(map.containsKey(name)){
            return (PropertyQueryXML)map.get(name).newCopy();
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Distance class '"+name+"' not found - assuming NullPropertyQueryXML");
            return new NullPropertyQueryXML();
        }
    }
    
    public Parameter getClassParameter(){
        return properties.get("PropertyQueryXMLClass");
    }
}
