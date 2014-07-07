/*
 * PropertyXMLFactory - created 1/02/2009 - 10:47:49 PM
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
import org.dynamicfactory.property.xml.BasicPropertyXML;
import org.dynamicfactory.property.xml.PropertyXML;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyXMLFactory extends AbstractFactory<PropertyXML>{

   static PropertyXMLFactory instance = null;
   
   public static PropertyXMLFactory newInstance(){
       if(instance == null){
           instance = new PropertyXMLFactory();
       }
       return instance;
   }
   
   private PropertyXMLFactory(){
        ParameterInternal name = ParameterFactory.newInstance().create("ValueClass", String.class);
        SyntaxObject check = SyntaxCheckerFactory.newInstance().create(1,1,null,String.class);
        name.setRestrictions(check);
        name.add("String");
        properties.add(name);

        map.put("BasicParameter",new BasicPropertyXML());
   }
    @Override
    public PropertyXML create(Properties props) {
        return create(null,props);
    }

    public PropertyXML create(String name){
        return create(name,properties);
    }

    public PropertyXML create(String name,Properties props){
        if((name != null)|| (properties.check(props))){
            if(name == null){
                if((props != null)&&(props.get("PropertyXMLClass")!=null)){
                    name = (String)props.get("PropertyXMLClass").get();
                }else{
                    name = (String)properties.get("PropertyXMLClass").get();
                }
            }
            if(map.containsKey(name)){
                return map.get(name);
            }else{
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Class type '"+name+"' does not exist - using BasicPropertyXML as a default.");
                return new BasicPropertyXML();
            }
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Null name provided without a replacement in the properties object - using BasicPropertyXML as a default.");
            return new BasicPropertyXML();
        }
    }

    @Override
    public Parameter getClassParameter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
