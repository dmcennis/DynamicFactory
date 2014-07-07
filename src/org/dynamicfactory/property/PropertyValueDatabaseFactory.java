/*
 * PropertyValueDatabaseFactory - created 14/03/2009 - 6:27:22 PM
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

import java.sql.Connection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.descriptors.*;
//import org.dynamicfactory.property.database.AssociativeMiningItemsDB;
//import org.dynamicfactory.property.database.AttributeDB;
import org.dynamicfactory.property.database.DoubleDB;
import org.dynamicfactory.property.database.FileDB;
//import org.dynamicfactory.property.database.InstanceDB;
//import org.dynamicfactory.property.database.InstancesDB;
import org.dynamicfactory.property.database.IntegerDB;
import org.dynamicfactory.property.database.LongDB;
import org.dynamicfactory.property.database.PropertyValueDB;
import org.dynamicfactory.property.database.StringDB;
import org.dynamicfactory.property.database.URLDB;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyValueDatabaseFactory extends AbstractFactory<PropertyValueDB>{

    Connection connection = null;
    static PropertyValueDatabaseFactory instance = null;

    public static PropertyValueDatabaseFactory newInstance(){
        if(instance == null){
            instance = new PropertyValueDatabaseFactory();
        }
        return instance;
    }

    private PropertyValueDatabaseFactory(){
        ParameterInternal name = ParameterFactory.newInstance().create("ValueClass", String.class);
        SyntaxObject check = SyntaxCheckerFactory.newInstance().create(1,1,null,String.class);
        name.setRestrictions(check);
        name.add("String");
        properties.add(name);

//        map.put("AssociativeMiningItems",new AssociativeMiningItemsDB());
//        map.put("Attribute",new AttributeDB());
        map.put("Double",new DoubleDB());
        map.put("File",new FileDB());
//        map.put("Instance",new InstanceDB());
//        map.put("Instances",new InstancesDB());
        map.put("Integer",new IntegerDB());
        map.put("Long",new LongDB());
        map.put("String",new StringDB());
        map.put("URL",new URLDB());
    }

    @Override
    public PropertyValueDB create(Properties props) {
        return create(null,props);
    }

    public PropertyValueDB create(Class classType){
        return create(classType.getSimpleName(),properties);
    }

    public PropertyValueDB create(String classType){
        return create(classType,properties);
    }

    public PropertyValueDB create(String classType, Properties props){
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
            return new StringDB();
        }
    }
    
    @Override
    public Parameter getClassParameter() {
        return properties.get("ValueClass");
    }

    public void init(Connection db){
        Iterator<PropertyValueDB> it = map.values().iterator();
        while(it.hasNext()){
            it.next().setConnection(db);
        }
    }

    public void clear(){

    }

    public void initializeDatabase(Connection conn){
        Iterator<PropertyValueDB> it = map.values().iterator();
        while(it.hasNext()){
            it.next().initializeDatabase(conn);
        }
    }

    public void clearDatabase(Connection conn){
        Iterator<PropertyValueDB> it = map.values().iterator();
        while(it.hasNext()){
            it.next().clearDatabase(conn);
        }
    }
}
