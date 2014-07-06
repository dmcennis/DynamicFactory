/**
 * XMLParserObject
 * Created Jan 16, 2009 - 11:57:13 PM
 * Copyright Daniel McEnnis, see license.txt
 */
package org.dynamicfactory.propertyQuery;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.dynamicfactory.propertyQuery.Query.State;import java.lang.String;

/**
 *
 * @author Daniel McEnnis
 */
public interface XMLParserObject {

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;

    public void characters(char[] ch, int start, int length) throws SAXException;

    public void endElement(String uri, String localName, String qName) throws SAXException;    
    
    public State buildingStatus();

    public XMLParserObject newCopy();

}
