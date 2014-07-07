/**
 * NullQueryXML
 * Created Jan 12, 2009 - 9:26:21 PM
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

import org.dynamicfactory.propertyQuery.NullPropertyQuery;import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.dynamicfactory.propertyQuery.Query.State;

/**
 *
 * @author Daniel McEnnis
 */
public class NullPropertyQueryXML implements PropertyQueryXML{

    NullPropertyQuery nullQuery = new NullPropertyQuery();
    
    enum InternalState {START,NOT};
    
    InternalState state = InternalState.START;
    
    boolean not = false;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equalsIgnoreCase("StringQuery")&&qName.equalsIgnoreCase("NumericQuery")){
            
        }else if(localName.equalsIgnoreCase("Not")&&qName.equalsIgnoreCase("Not")){
            state = InternalState.NOT;
            not = true;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        ;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (state){
            case START:
                nullQuery.buildQuery(not);
                break;
            case NOT:
                state = InternalState.START;
                break;
        }
    }

    public State buildingStatus() {
        return nullQuery.buildingStatus();
    }

    public NullPropertyQuery getQuery() {
        return nullQuery;
    }

    public NullPropertyQueryXML newCopy() {
        return new NullPropertyQueryXML();
    }
}
