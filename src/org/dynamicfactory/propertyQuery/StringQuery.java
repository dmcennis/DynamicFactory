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
 * StringQuery
 * Created Jan 5, 2009 - 11:47:19 PM
 */
package org.dynamicfactory.propertyQuery;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.property.Property;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel McEnnis
 */
public class StringQuery implements PropertyQuery{

    public enum Operation {EQUALS, EQUALS_IGNORE_CASE, CONTAINS, ENDS_WITH, MATCHES, STARTS_WITH

    };
    
    Operation operation = Operation.EQUALS;
    
    String comparison = "";
    
    boolean not = false;
    

    public boolean execute(Property property) {
        boolean result = false;
        
        if(property.getPropertyClass().getName().contentEquals("java.lang.String")){
            Collection values = property.getValue();
            if(values.size() >0){
                switch(operation){
                    case EQUALS:
                        result = ((String)values.iterator().next()).equals(comparison);
                        break;
                    case EQUALS_IGNORE_CASE:
                        result = ((String)values.iterator().next()).equalsIgnoreCase(comparison);
                        break;
                    case CONTAINS:
                        result = ((String)values.iterator().next()).contains(comparison);
                        break;
                    case ENDS_WITH:
                        result = ((String)values.iterator().next()).endsWith(comparison);
                        break;
                    case MATCHES:
                        result = ((String)values.iterator().next()).matches(comparison);
                        break;
                    case STARTS_WITH:
                        result = ((String)values.iterator().next()).startsWith(comparison);
                        break;
                }        
                if(not){
                    return !result;
                }else{
                    return result;
                }
            }
        }
        
        return result;
    }

    public void exportQuery(Writer writer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void importQuery(Reader reader) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void buildQuery(String comparison, boolean not, Operation operation) {
        if(comparison==null){
            this.comparison="";
        }else{
            this.comparison=comparison;
        }
        this.not = not;
        if(operation == null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Operation is null, assuming Equals");
            this.operation = Operation.EQUALS;
        }else{
            this.operation = operation;
        }
    }

    public int compareTo(Object o) {
        if(o.getClass().getName().contentEquals(this.getClass().getName())){
            StringQuery right = (StringQuery)o;
            if(this.operation.compareTo(right.operation) != 0){
                return operation.compareTo(right.operation);
            }
            if(comparison.compareTo(right.comparison) != 0){
                return  comparison.compareTo(right.comparison);
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

    public StringQuery prototype() {
        return new StringQuery();
    }
}
