/*
 * BasicParameter.java
 *
 * Created on 14 September 2007, 13:55
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
package org.dynamicfactory.descriptors;

import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dynamicfactory.Python;
import org.dynamicfactory.property.InvalidObjectTypeException;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.property.PropertyFactory;
import org.dynamicfactory.property.BasicProperty;
import org.dynamicfactory.descriptors.PropertyRestriction;
import org.dynamicfactory.descriptors.SyntaxObject;
import org.dynamicfactory.propertyQuery.NullPropertyQuery;

/**
 * Base implementation of all the parameter interfaces.
 *
 * @author Daniel McEnnis
 */
public class BasicParameter<Type> implements ParameterInternal<Type> {

    private SyntaxObject restrictions;
    private boolean structural;
    private String description;
    private String longDescription;
    private Property<Type> value;

    /**
     * Creates a new instance of BasicParameter
     */
    public BasicParameter() {
        value = new BasicProperty("Default", Object.class);
        restrictions = new PropertyRestriction();
        structural = false;
    }

    public BasicParameter(String s, Class c) {
        value = new BasicProperty(s, c);
        structural = false;
        restrictions = new PropertyRestriction();
    }

    @Override
    public BasicParameter<Type> setLongDescription(String d) {
        longDescription = d;
        return this;
    }

    @Override
    public String getLongDescription() {
        return longDescription;
    }

    @Override
    public int compareTo(Parameter o) {
        int answer=0;
        if ((answer = this.getType().compareTo(o.getType())) != 0) {
            return answer;
        }

        if ((answer = this.getParameterClass().getName().compareTo(o.getParameterClass().getName())) != 0) {
            return answer;
        }
        ;
        if ((answer = this.getValue().size() - o.getValue().size()) != 0) {
            return answer;
        }

        if ((answer = this.getParameterClass().getName().compareTo(o.getParameterClass().getName())) != 0) {
            return answer;
        }

        List leftValue = this.getValue();
        List rightValue = o.getValue();

        if ((answer = leftValue.size() - rightValue.size())!=0) {
            return leftValue.size() - rightValue.size();
        } else {
            boolean comparable = true;
            if (leftValue.size() == 0) {
                return 0;
            } else if (!(leftValue.iterator().next() instanceof Comparable)) {
                comparable = false;
            }
            java.util.Collections.sort(leftValue);
            java.util.Collections.sort(rightValue);
            Iterator leftIt = leftValue.iterator();
            Iterator rightIt = rightValue.iterator();
            while (leftIt.hasNext()) {
                if (comparable) {
                    Comparable l = (Comparable) leftIt.next();
                    Comparable r = (Comparable) rightIt.next();
                    if ((answer = (l.compareTo(r))) != 0) {
                        return l.compareTo(r);
                    }
                } else {
                    if (leftIt.next() != rightIt.next()) {
                        return -1;
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public BasicParameter<Type> setStructural(boolean b) {

        structural = b;
        return this;
    }

    @Override
    public boolean isStructural() {

        return structural;

    }

    @Override
    public BasicParameter<Type> set(Property o) {

        if (o.getType().contentEquals(value.getType()) && o.getPropertyClass().equals(value.getPropertyClass())) {
            if (restrictions.check(o)) {
                value = o;
            }
        }
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BasicParameter<Type> setDescription(String d) {
        if (d != null) {
            description = d;
        } else {
            Logger.getLogger(d).log(Level.WARNING, "Null description passed to parameter " + value.getType());
        }
        return this;
    }

    public BasicParameter<Type> setType(String name) {
        value.setType(name);return this;
    }

    public BasicParameter<Type> setRestrictions(SyntaxObject syntax) {
        restrictions = syntax;
        return this;
    }

    public SyntaxObject getRestrictions() {
        return restrictions;
    }

    public BasicParameter<Type> add(Type o) {
        if (restrictions.check(value, o)) {
            try {
                value.add(o);
            } catch (InvalidObjectTypeException ex) {
                Logger.getLogger(BasicParameter.class.getName()).log(Level.SEVERE, "Uncaught Invalid object type after cleared by syntax checker", ex);
            }
        }
        return this;
    }

    public String getType() {
        return value.getType();
    }

    public List<Type> getValue() {
        return value.getValue();
    }

    public Class getParameterClass() {
        return value.getPropertyClass();
    }

    public BasicParameter<Type> setParameterClass(Class type) {
        try {
            value.setClass(type);
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, String.format("Class type presented is not compatible with the templated value: %s\n%s", e.getLocalizedMessage(), Python.join(e.getStackTrace(),"\n")));
        }
        return this;
    }

    @Override
    public BasicParameter<Type> set(String type, Class parameterClass, boolean structural, List value, String description, String longDescription) {
        setType(type);
        setParameterClass(parameterClass);
        setStructural(structural);
        set(value);
        setDescription(description);
        setLongDescription(longDescription);
        return this;
    }

    public boolean check(Property property) {
        if (!property.getType().contentEquals(this.getType())) {
            return false;
        }
        if (!property.getPropertyClass().equals(this.getParameterClass())) {
            return false;
        }
        return restrictions.check(property);
    }

    public boolean check(String type, Object value) {
        if (this.value.getType().contentEquals(type)) {
            return restrictions.check(this.value, value);
        }
        return false;
    }

    public boolean check(Parameter type) {
        if (this.value.getType().contentEquals(type.getType())) {
            return restrictions.check(type);
        } else {
            return false;
        }
    }

    public void clear() {
        String type = value.getType();
        Class classType = value.getPropertyClass();
        value = PropertyFactory.newInstance().create(value.getClass().getSimpleName(), type, classType);
    }

    public BasicParameter<Type> add(List value) {
        if (restrictions.check(this.value, value)) {
            Iterator it = value.iterator();
            try {
                while (it.hasNext()) {
                    this.value.add((Type)it.next());
                }
            } catch (InvalidObjectTypeException ex) {
                Logger.getLogger(BasicParameter.class.getName()).log(Level.SEVERE, String.format("INTERNAL: the object provided was not compatible with the type of this parameter, but was not detected by the syntax checker. %s\n%s", ex.getLocalizedMessage(),Python.join(ex.getStackTrace(),"\n")));
            }
        }else{
            Logger.getLogger(BasicParameter.class.getName()).log(Level.SEVERE, "DEVELOPER: the object provided was not compatible with the parameters syntax restrictions");
        }
        return this;
    }

    public boolean check(String type, List value) {
        if (type != this.value.getType()) {
            return false;
        }
        return restrictions.check(type, value);
    }

    @Override
    public ParameterInternal<Type> prototype(Properties props) {
        ParameterInternal<Type> ret = prototype();
        if(props.quickCheck("Name",String.class)){
            ret.setType((String)props.getQuick("Name"));
        }
        try {
            if(props.quickCheck("Class",Class.class)){
                ret.setParameterClass((Class)props.getQuick("Class"));
            }
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL ERROR: Found a non-class object after checking for its type: "+props.getQuick("Class").getClass().getName());
        }
        if(props.quickCheck("Description",String.class)){
            ret.setDescription((String)props.getQuick("Description"));
        }
        if(props.quickCheck("LongDescription",String.class)){
            ret.setLongDescription((String)props.getQuick("LongDescription"));
        }
        return ret;
    }

    public ParameterInternal<Type> prototype() {
        BasicParameter ret = new BasicParameter();
        ret.value = PropertyFactory.newInstance().create(this.value.getClass().getSimpleName(), this.value.getType(), this.value.getPropertyClass());
        ret.setType(getType());
        Iterator it = this.value.getValue().iterator();
        while (it.hasNext()) {
            try {
                ret.value.add(it.next());
            } catch (InvalidObjectTypeException ex) {
                Logger.getLogger(BasicParameter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ret.structural = this.structural;
        ret.description = this.description;
        ret.restrictions = this.restrictions.prototype();
        return ret;
    }

    public Type get() {
        if (this.value.getValue().size() > 0) {
            return this.value.getValue().get(0);
        }
        return (Type)null;
    }

    public BasicParameter<Type> set(Type value) {
        this.clear();
        this.add(value);
        return this;
    }

    public BasicParameter<Type> set(List value) {
        this.clear();
        this.add(value);
        return this;
    }

}

