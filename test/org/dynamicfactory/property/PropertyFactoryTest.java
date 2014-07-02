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

package org.dynamicfactory.property;

import junit.framework.TestCase;
import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.descriptors.PropertiesFactory;

/**
 *
 * @author mcennis
 */
public class PropertyFactoryTest extends TestCase {
    
    public PropertyFactoryTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of newInstance method, of class PropertyFactory.
     */
    public void testNewInstance() {
        System.out.println("newInstance");
        PropertyFactory expResult = PropertyFactory.newInstance();
        PropertyFactory result = PropertyFactory.newInstance();
        assertNotNull(result);
        assertSame(expResult,result);
    }

    /**;
     * Test of create method, of class PropertyFactory.
     */
    public void testCreate_String_Class() {
        System.out.println("create");
        String id = "Type";
        Class objectType = Double.class;
        PropertyFactory instance = new PropertyFactory();
        Property result = instance.create("BasicProperty",id, objectType);
        assertNotNull(result);
        assertEquals("Type",result.getType());
        assertEquals(Double.class,result.getPropertyClass());
    }

    /**
     * Test of create method, of class PropertyFactory.
     */
    public void testCreate_Properties() {
        System.out.println("create");
        Properties props = null;
        PropertyFactory instance = new PropertyFactory();
        Property expResult = null;
        Property result = instance.create(props);
        assertNotNull(result);
        assertEquals("Default",result.getType());
        assertEquals(String.class,result.getPropertyClass());
    }

    /**
     * Test of create method, of class PropertyFactory.
     */
    public void testPartCreate_3args() {
        System.out.println("create");
        String id = "Type";
        Class objectType = Double.class;
        Properties props = null;
        PropertyFactory instance = new PropertyFactory();
        Property result = instance.create("BasicProperty",id, objectType, props);
        assertNotNull(result);
        assertEquals("Type",result.getType());
        assertEquals(Double.class,result.getPropertyClass());
    }

    /**
     * Test of create method, of class PropertyFactory.
     */
    public void testPropertiesCreate_3args() {
        System.out.println("create");
        String id = null;
        Class objectType = null;
        Properties props = PropertiesFactory.newInstance().create();
        props.add("PropertyID", "Type");
        props.add("PropertyValueClass", Double.class);
        PropertyFactory instance = new PropertyFactory();
        Property result = instance.create("BasicProperty",id, objectType, props);
        assertNotNull(result);
        assertEquals("Type",result.getType());
        assertEquals(Double.class,result.getPropertyClass());
    }

    /**
     * Test of create method, of class PropertyFactory.
     */
    public void testBothCreate_3args() {
        System.out.println("create");
        String id = "Type";
        Class objectType = Double.class;
        Properties props = PropertiesFactory.newInstance().create();
        props.add("PropertyType", "BadType");
        props.add("PropertyValueClass", Long.class);
        PropertyFactory instance = new PropertyFactory();
        Property result = instance.create("BasicProperty",id, objectType, props);
        assertNotNull(result);
        assertEquals("Type",result.getType());
        assertEquals(Double.class,result.getPropertyClass());
    }

    /**
     * Test of getClassParameter method, of class PropertyFactory.
     */
    public void testGetClassParameter() {
        System.out.println("getClassParameter");
        PropertyFactory instance = new PropertyFactory();
        Parameter result = instance.getClassParameter();
        assertNotNull(result);
        assertEquals("PropertyClass",result.getType());
        assertEquals(String.class,result.getParameterClass());
    }

}
