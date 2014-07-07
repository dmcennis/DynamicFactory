/**
 * DoubleXMLFactory
 * Created Jan 17, 2009 - 12:20:49 AM
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
package org.dynamicfactory.property.xml;

import java.io.IOException;
import java.io.Writer;
import org.dynamicfactory.propertyQuery.Query.State;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel McEnnis
 */
public class DoubleXML implements PropertyValueXML<Double>{

    double value = Double.NaN;
    
    State state = State.UNINITIALIZED;
    
    public Double getProperty() {
        return value;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        state = State.LOADING;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        value = Double.parseDouble(new String(ch,start,length));
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        state = State.READY;
    }

    public State buildingStatus() {
        return state;
    }

    public DoubleXML newCopy() {
        return new DoubleXML();
    }

    public void setProperty(Double type){
        value = type;
    }

    public void export(Writer writer) throws IOException {
        writer.append("<Double>").append(Double.toString(value)).append("</Double>\n");
    }
    
    public String getClassName() {
        return "Double";
    }
}
