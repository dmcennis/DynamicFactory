/**
 * QueryXMLLoader
 * Created Jan 12, 2009 - 8:15:21 PM
 * Copyright Daniel McEnnis, see license.txt
 */
package org.dynamicfactory.propertyQuery.xml;

import nz.ac.waikato.mcennis.rat.XMLParserObject;import org.dynamicfactory.propertyQuery.PropertyQuery;

/**
 *
 * @author Daniel McEnnis
 */
public interface PropertyQueryXML extends XMLParserObject{
    
    public PropertyQuery getQuery();
}
