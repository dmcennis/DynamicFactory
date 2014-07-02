package org.dynamicfactory;

import org.dynamicfactory.descriptors.*;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Created by dmcennis on 7/1/2014.
 */
public class FactoryFactory extends AbstractFactory<AbstractFactory> {

    static private FactoryFactory instance = null;

    static public FactoryFactory newInstance(){
        if(instance == null){
            instance =  new FactoryFactory();
        }
        return instance;
    }

    HashMap<String,Class> classObjects;

    public FactoryFactory(){
        classObjects = new HashMap<String,Class>();

        BasicParameter factoryType = new BasicParameter();
        factoryType.setType("FactoryClass");
        factoryType.setDescription("Name of the Factory type to return");
        factoryType.setParameterClass(String.class);
        factoryType.add("PropertiesFactory");
        PropertyRestriction restriction = new PropertyRestriction();
        restriction.setMinCount(1);
        restriction.setMaxCount(1);
        factoryType.setRestrictions(restriction);
        properties.add(factoryType);

    }

    public Class getType(String name){
        if(name == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null factory output type requested");
            return Object.class;
        }
        return classObjects.get(name);
    }

    public void addType(String type,AbstractFactory prototype, Class classType){
        if(type == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null property class name added");
        }
        if(prototype == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "No property object provided");
        }
        if(classType == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "No output class object provided");
        }
        if((type != null)&&(prototype != null)&&(classType != null)){
            map.put(type,prototype);
            classObjects.put(type,classType);
        }
    }


    @Override
    public Parameter getClassParameter() {
        return new BasicParameter("FactoryName",String.class);
    }

    public Class getFactoryType(String type){
        if(classObjects.containsKey(type)){
            return classObjects.get(type);
        }else{
            return null;
        }
    }

    @Override
    public AbstractFactory create(Properties props) {
        if((props != null)&&(props.get("FactoryName") != null)&&(props.get("FactoryName").getValue().size()>0)&&(map.containsKey(props.get("FactoryName").getValue().iterator().next()))&&(props.get("FactoryName").getParameterClass().getName().equals("java.lang.String"))){
            return map.get("FactoryName");
        }else{
            if(props == null){
                Logger.getLogger(FactoryFactory.class.getName()).log(Level.SEVERE, "Null properties found.");
            }else if((props.get("FactoryName") != null)&&(props.get("FactoryName").getValue().size()>0)&&(props.get("FactoryName").getParameterClass().getName().equals("java.lang.String"))){
                Logger.getLogger(FactoryFactory.class.getName()).log(Level.SEVERE, "The factory name "+props.get("FactoryName").getValue().iterator().next()+" is not registered.");
            }else{
                Logger.getLogger(FactoryFactory.class.getName()).log(Level.SEVERE, "The factory name is missing");
            }
            return PropertiesFactory.newInstance();
        }
    }
}
