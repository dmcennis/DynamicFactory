/**
 * IntegerXMLFactory
 * Created Jan 17, 2009 - 12:20:05 AM
 * Copyright Daniel McEnnis, see license.txt
 */
package org.dynamicfactory.property.xml;

import java.io.IOException;
import java.io.Writer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel McEnnis
 */
public class IntegerXML implements PropertyValueXML<Integer>{

    int value = 0;
    
    State state = State.UNINITIALIZED;
    
    public Integer getProperty() {
        return value;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        state = State.LOADING;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        value = Integer.parseInt(new String(ch,start,length));
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        state = State.READY;
    }

    public State buildingStatus() {
        return state;
    }

    public IntegerXML newCopy() {
        return new IntegerXML();
    }

    public void setProperty(Integer type){
        value = type;
    }

    public void export(Writer writer) throws IOException {
        writer.append("<Integer>").append(Integer.toString(value)).append("</Integer>\n");
    }
    
    public String getClassName() {
        return "Integer";
    }
}
