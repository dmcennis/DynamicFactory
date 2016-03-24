/**
 * NumericQuery
 * Created Jan 5, 2009 - 11:23:14 PM
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


package org.dynamicfactory.propertyQuery;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.property.Property;

/**
 *
 * @author Daniel McEnnis
 */
public class NumericQuery implements PropertyQuery{
    

    boolean not = false;

    enum Operation {GT,EQ,LT,GTE,LTE,NE};
    
    Operation operation = Operation.GT;
    
    double comparisonValue = 0.0;
    

    public boolean execute(Property property) {
        String name = property.getPropertyClass().getName();
        if(name.contentEquals("java.lang.Double") || 
                name.contentEquals("java.lang.Float")||
                name.contentEquals("java.lang.Long")||
                name.contentEquals("java.lang.Integer")||
                name.contentEquals("java.lang.Short")){
            Collection value = property.getValue();
            if(value.size() > 0){
                double left = ((Number) value.iterator().next()).doubleValue();
                return execute(left);
            }else{
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Property "+property.getType()+" has no values");
                return false;
            }
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Property "+property.getType()+" is of type "+property.getPropertyClass().getName()+" where a numeric org.dynamicfactory.property is expected");
            return false;
        }
    
    }
    
    boolean execute(double left){
                        boolean result = false;
                switch (operation){
                    case EQ:
                        result = (left == comparisonValue);
                        break;
                    case GT:
                        result = (left > comparisonValue);
                        break;
                    case LT:
                        result = (left < comparisonValue);
                        break;
                    case GTE:
                        result = (left >= comparisonValue);
                        break;
                    case LTE:
                        result = (left <= comparisonValue);
                        break; 
                    case NE:
                        result = (left != comparisonValue);
                        break;
                }
                if(not){
                    return !result;
                }else{
                    return result;
                }

    }


    public void buildQuery(double comparisonValue, boolean not, Operation operation) {
        this.comparisonValue = comparisonValue;
        this.not = not;
        if(operation==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "null value instead of an operation, GreaterThan assumed");
            this.operation = Operation.GT;
        }else{
            this.operation = operation;
        }
    }

    public int compareTo(Object o) {
        if(o.getClass().getName().contentEquals(this.getClass().getName())){
            NumericQuery right = (NumericQuery)o;
            if(!operation.equals(right.operation)){
                return operation.compareTo(right.operation);
            }
            if(comparisonValue - right.comparisonValue != 0.0){
                return (int) comparisonValue;
            }
            if(! not && right.not){
                return -1;
            }
            if(not && right.not){
                return 1;
            }
            return 0;
        }else{
            return this.getClass().getName().compareTo(o.getClass().getName());
        }
       
    }

    @Override
    public PropertyQuery prototype(Properties props) {
        return prototype();
    }

    public NumericQuery prototype() {
        return new NumericQuery();
    }
}
