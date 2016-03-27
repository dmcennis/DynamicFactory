package org.dynamicfactory.propertyQuery;

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
 * org.dynamicfactory.propertyQuery.PropertyQueryFactory
 * Created Jan 26, 2009 - 9:01:40 PM
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.descriptors.SyntaxCheckerFactory;
import org.dynamicfactory.descriptors.SyntaxObject;
import org.dynamicfactory.propertyQuery.PropertyQuery;
import org.dynamicfactory.propertyQuery.NullPropertyQuery;
import org.dynamicfactory.propertyQuery.NumericQuery;
import org.dynamicfactory.propertyQuery.StringQuery;
import org.dynamicfactory.AbstractFactory;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyQueryFactory extends AbstractFactory<PropertyQuery> {

    static PropertyQueryFactory instance = null;
    
    public static PropertyQueryFactory newInstance(){
        if(instance == null){
            instance = new PropertyQueryFactory();
        }
        return instance;
    }

    @Override
    public AbstractFactory prototype() {
        return newInstance();
    }

    private PropertyQueryFactory(){
        ParameterInternal name = ParameterFactory.newInstance().create("QueryClass", String.class);
        SyntaxObject check = SyntaxCheckerFactory.newInstance().create(1,1,null,String.class);
        name.setRestrictions(check);
        name.add("NullPropertyQuery");
        properties.add(name);
        
        map.put("NullPropertyQuery",new NullPropertyQuery());
        map.put("NumericQuery",new NumericQuery());
        map.put("StringQuery",new StringQuery());
        map.put("OrQuery", new OrQuery());
        map.put("AndQuery", new AndQuery());
    }
    
    @Override
    public PropertyQuery create(Properties props) {
        return create(null,props);
    }
    
    public PropertyQuery create(String id){
        return create(id,properties);
    }
    
    public PropertyQuery create(String id, Properties parameters){
        String classType = "";
        if ((parameters.get("QueryClass") != null) && (parameters.get("QueryClass").getParameterClass().getName().contentEquals(String.class.getName()))) {
            classType = (String) parameters.get("QueryClass").getValue().iterator().next();
        } else {
            classType = (String) properties.get("QueryClass").getValue().iterator().next();
        }

        if(map.containsKey(classType)){
            return map.get(classType).prototype();
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"PropertyQuery class '"+classType+"' not found - assuming NullPropertyQuery");
            return new NullPropertyQuery();
        }
    }
    public Parameter getClassParameter(){
        return properties.get("QueryClass");
    }
}
