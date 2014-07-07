/**
 * NumericQueryXML
 * Created Jan 12, 2009 - 8:52:45 PM
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

import java.lang.Double;import java.lang.NumberFormatException;import java.lang.String;import java.util.logging.Level;
import java.util.logging.Logger;

import org.dynamicfactory.propertyQuery.NumericQuery;import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.dynamicfactory.propertyQuery.Query.State;

/**
 *
 * @author Daniel McEnnis
 */
public class NumericQueryXML implements PropertyQueryXML{

    NumericQuery numericQuery = new NumericQuery();
    
    enum InternalState {START, OPERATION, NOT, VALUE};
    
    InternalState state = InternalState.START;
    
    boolean not;
    
    double value = 0.0;
    
    NumericQuery.Operation op = NumericQuery.Operation.GT;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equalsIgnoreCase("NumericQuery")&&qName.equalsIgnoreCase("NumericQuery")){
            
        }else if(localName.equalsIgnoreCase("Operation")&&qName.equalsIgnoreCase("Operation")){
            state = InternalState.OPERATION;
        }else if(localName.equalsIgnoreCase("Not")&&qName.equalsIgnoreCase("Not")){
            state = InternalState.NOT;
        }else if(localName.equalsIgnoreCase("Value")&&qName.equalsIgnoreCase("Value")){
            state = InternalState.VALUE;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
            String contents = new String(ch,start,length);
        if(state == InternalState.OPERATION){
            if(contents.equalsIgnoreCase("EQ")){
                op = NumericQuery.Operation.EQ;
            }else if(contents.equalsIgnoreCase("GT")){
                op = NumericQuery.Operation.GT;
            }else if(contents.equalsIgnoreCase("LT")){
                op = NumericQuery.Operation.LT;
            }else if(contents.equalsIgnoreCase("GTE")){
                op = NumericQuery.Operation.GTE;
            }else if(contents.equalsIgnoreCase("LTE")){
                op = NumericQuery.Operation.LTE;
            }else if(contents.equalsIgnoreCase("NE")){
                op = NumericQuery.Operation.NE;
            }else{
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Unknown operation '"+contents+"' - assuming GT");
            }
        }else if(state == InternalState.VALUE){
            try{
            value = Double.parseDouble(contents);
            }catch(NumberFormatException ex){
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (state){
            case START:
                numericQuery.buildQuery(value, not, op);
                break;
            case NOT:
                state = InternalState.START;
                break;
            case OPERATION:
                state = InternalState.START;
                break;
            case VALUE:
                state = InternalState.START;
                break;                
        }
    }

    public State buildingStatus() {
        return numericQuery.buildingStatus();
    }

    public NumericQuery getQuery() {
        return numericQuery;
    }

    public NumericQueryXML newCopy() {
        return new NumericQueryXML();
    }
}
