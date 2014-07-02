/**
 * PropertyRestrictionComponent
 * Created Jan 24, 2009 - 8:34:36 PM
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

import org.dynamicfactory.property.*;
import org.dynamicfactory.property.PropertyFactory;
import org.dynamicfactory.propertyQuery.PropertyQuery;
import org.dynamicfactory.propertyQuery.NullPropertyQuery;

/**
 *
 * @author Daniel McEnnis
 */
public class PropertyRestriction implements SyntaxObject {

    int minCount = 0;
    int maxCount = Integer.MAX_VALUE;
    PropertyQuery test;
    Class classType = Object.class;

    public PropertyRestriction() {
        test = new NullPropertyQuery();
        ((NullPropertyQuery) test).buildQuery(true);
    }

    public void setPropertyQuery(PropertyQuery test) {
        if (test != null) {
            this.test = test;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null Query provided, leaving the original unchanged.");
        }
    }

    public boolean check(Property type, Object o) {
        Property temp = type.duplicate();
        try {
            temp.add(o);
            return check(temp);
        } catch (InvalidObjectTypeException ex) {
            return false;
        }
    }

    public boolean check(Property type) {
        if (type != null) {
            if ((type.getValue().size()) < minCount) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Min count is " + minCount + " but total count with addition is " + Integer.toString(type.getValue().size()));
                return false;
            } else if ((type.getValue().size()) > maxCount) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Max count is " + maxCount + " but total count with addition is " + Integer.toString(type.getValue().size()));
                return false;
            } else if ((type.getValue().size()==0)&&(!classType.isAssignableFrom(type.getPropertyClass()))) {
                return false;
            } else if ((type.getValue().size()>0)&&(!classType.isAssignableFrom(type.getValue().iterator().next().getClass()))) {
                return false;
            } else if (!test.execute(type)) {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Property failed the query check");
                return false;
            } else {
                return true;
            }
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null Query provided, leaving the original unchanged.");
            return false;
        }
    }

    public boolean check(Parameter type) {
        if (type != null) {
            if ((type.getValue().size()) < minCount) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Min count is " + minCount + " but total count with addition is " + Integer.toString(type.getValue().size()));
                return false;
            } else if ((type.getValue().size()) > maxCount) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Min count is " + maxCount + " but total count with addition is " + Integer.toString(type.getValue().size()));
                return false;
            } else if (!classType.isAssignableFrom(type.getParameterClass())) {
                return false;
            }
            Property props = new BasicProperty(type.getType(), type.getParameterClass());
            Iterator it = type.getValue().iterator();
            while (it.hasNext()) {
                try {
                    props.add(it.next());
                } catch (InvalidObjectTypeException ex) {
                }
            }
            if (!test.execute(props)) {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Property failed the query check");
                return false;
            } else {
                return false;
            }
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null Query provided, leaving the original unchanged.");
            return false;
        }
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public PropertyQuery getTest() {
        return test;
    }

    public void setTest(PropertyQuery test) {
        if (test != null) {
            this.test = test;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null value provided for query - change aborted");
        }
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }

    public PropertyRestriction duplicate() {
        PropertyRestriction ret = new PropertyRestriction();
        ret.minCount = this.minCount;
        ret.maxCount = this.maxCount;
        ret.classType = this.classType;
        ret.test = this.test;
        return ret;
    }

    public void setRestriction(String type, PropertyRestriction restriction) {
        this.classType = restriction.classType;
        this.maxCount = restriction.maxCount;
        this.minCount = restriction.minCount;
        this.test = restriction.test;
    }

    public void setDefaultRestriction(PropertyRestriction restriction) {
        ;// deliberate no-op

    }

    public boolean check(Property property, List value) {
        Property temp = property.duplicate();
        try {
            Iterator it = value.iterator();
            while (it.hasNext()) {
                temp.add(it.next());
            }
            return check(temp);
        } catch (InvalidObjectTypeException ex) {
            return false;
        }
    }

    public boolean check(String type, Object value) {
        if (value != null) {
            Property props = new BasicProperty(type, value.getClass());
            try {
                props.add(value);
            } catch (InvalidObjectTypeException ex) {
                Logger.getLogger(PropertyRestriction.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return check(props);
        } else {
            Property props = new BasicProperty(type, classType);
            return check(props);
        }
    }

    public boolean check(String type, List value) {
        if (value != null) {
            if (value.size() > 0) {
                Property props = new BasicProperty(type, value.iterator().next().getClass());
                try {
                    Iterator it = value.iterator();
                    while(it.hasNext()){
                        props.add(it.next());
                    }
                } catch (InvalidObjectTypeException ex) {
                    Logger.getLogger(PropertyRestriction.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                return check(props);
            }
        }
        Property props = new BasicProperty(type, classType);
        return check(props);
    }
}
