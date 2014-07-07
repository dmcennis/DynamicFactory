/**
 * URLXMLFactory
 * Created Jan 17, 2009 - 12:19:28 AM
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.propertyQuery.Query.State;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel McEnnis
 */
public class URLXML implements PropertyValueXML<URL>{

    URL location;
        
    Properties props;
    
    State state = State.UNINITIALIZED;
    
    public URL getProperty() {
        if(state == State.READY){
            return location;
        }else{
            return null;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        state = State.LOADING;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            location = new URL(new String(ch, start, length));
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLXML.class.getName()).log(Level.SEVERE, "URL '"+new String(ch,start,length)+"' is a malformed URL", ex);
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        state = State.READY;
    }

    public State buildingStatus() {
        return state;
    }

    public URLXML newCopy() {
        return new URLXML();
    }

    public void export(Writer writer) throws IOException {
        writer.append("<URL>").append(location.toString()).append("</URL>\n");
    }

    public void setProperty(URL type) {
        location = type;
    }

    
    public String getClassName() {
        return "URL";
    }
}
