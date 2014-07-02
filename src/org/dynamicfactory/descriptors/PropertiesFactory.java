/**
 * PropertiesFactory
 * Created Jan 26, 2009 - 2:45:51 PM
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

/**
 *
 * @author Daniel McEnnis
 */
public class PropertiesFactory extends AbstractFactory<PropertiesInternal>{

    private static PropertiesFactory instance = null;
    
    static public PropertiesFactory newInstance(){
        if(instance==null){
            instance = new PropertiesFactory();
        }
        return instance;
    }
        
    private PropertiesFactory(){
        ParameterInternal type = new BasicParameter();
        type.setType("PropertiesClass");
        type.setParameterClass(String.class);
        PropertyRestriction restriction = new PropertyRestriction();
        restriction.setClassType(String.class);
        restriction.setMaxCount(1);
        restriction.setMinCount(1);
        type.setRestrictions(restriction);
        type.add("PropertiesImplementation");
        properties.add(type);
        
        properties.setDefaultRestriction(new PropertyRestriction());
        
        map.put("PropertiesImplementation", new PropertiesImplementation());
    }
    
    public PropertiesInternal create(){
        return create(properties);
    }
    
    public PropertiesInternal create(Properties props){
        if((props != null)&&(map.containsKey("PropertiesClass"))&&(map.containsKey(props.get("PropertiesClass").getValue().iterator().next()))){
            String type = (String) props.get("PropertiesClass").getValue().iterator().next();
            return map.get(type).duplicate();
        }else if(props == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Null properties detected -  assuming PropertiesImplementation");
        } else if(props.get("PropertiesClass")==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"PropertiesClass property does not exist -  assuming PropertiesImplementation");
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Properties type '" + props.get("PropertiesClass").getValue().iterator().next() + "' does not exist -  assuming PropertiesImplementation");
        }
        return new PropertiesImplementation();
    }

    public Parameter getClassParameter(){
        return properties.get("PropertiesClass");
    }
}
