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

package org.dynamicfactory.descriptors;

import junit.framework.TestCase;
import org.dynamicfactory.property.InvalidObjectTypeException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mcennis
 */
public class ParameterFactoryTest extends TestCase {

    public ParameterFactoryTest(String testName) {
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
     * Test of newInstance method, of class ParameterFactory.
     */
    public void testNewInstance() {
        System.out.println("newInstance");
        ParameterFactory expResult = ParameterFactory.newInstance();
        ParameterFactory result = ParameterFactory.newInstance();
        assertNotNull(result);
        assertSame(expResult, result);
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testCreate_Properties() {
        System.out.println("create");
        PropertiesInternal props = PropertiesFactory.newInstance().create();
        try {
            props.set("ParameterClass", "BasicParameter");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for ParameterClass");

        }
        try {
            props.set("Name", "Type");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Name");

        }
        try {
            props.set("Class", Double.class);
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Class");

        }
        try {
            props.set("Description", "Description");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Description");

        }
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create((ParameterInternal)props);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("Description", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testCreate_String_Class() {
        System.out.println("create");
        String type = "Type";
        Class classType = Double.class;
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create(type, classType);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("No Description Provided", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testCreate_3args() {
        System.out.println("create");
        String type = "Type";
        Class classType = Double.class;
        String description = "Description";
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create(type, classType, description);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("Description", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testPartsCreate_4args() {
        System.out.println("create");
        String type = "Type";
        Class classType = Double.class;
        String description = "Description";
        Properties props = null;
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create(type, classType, description, props);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("Description", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testPropertiesCreate_4args() {
        System.out.println("create");
        String type = null;
        Class classType = null;
        String description = null;
        PropertiesInternal props = PropertiesFactory.newInstance().create();
        try {
            props.set("ParameterClass", "BasicParameter");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for ParameterClass");
        }
        try {
            props.set("Name", "Type");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Name");
        }
        try {
            props.set("Class", Double.class);
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Class");
        }
        try {
            props.set("Description", "Description");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Description");
        }
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create(type, classType, description, props);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("Description", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of create method, of class ParameterFactory.
     */
    public void testBothCreate_4args() {
        System.out.println("create");
        String type = "Type";
        Class classType = Double.class;
        String description = "Description";
        PropertiesInternal props = PropertiesFactory.newInstance().create();
        try {
            props.set("ParameterClass", "BasicParameter");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for ParameterClass");

        }
        try {
            props.set("Name", "T");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Name");

        }
        try {
            props.set("Class", Long.class);
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Class");

        }
        try {
            props.set("Description", "Bogus");
        } catch (InvalidObjectTypeException e) {
            fail("New properties had existing non-string types for Description");

        }
        ParameterFactory instance = ParameterFactory.newInstance();
        ParameterInternal result = instance.create(type, classType, description, props);
        assertNotNull(result);
        assertTrue(result instanceof BasicParameter);
        assertEquals("Type", result.getType());
        assertEquals(Double.class, result.getParameterClass());
        assertEquals("Description", result.getDescription());
        assertEquals(0, result.getValue().size());
    }

    /**
     * Test of getClassParameter method, of class ParameterFactory.
     */
    public void testGetClassParameter() {
        System.out.println("getClassParameter");
        ParameterFactory instance = ParameterFactory.newInstance();
        Parameter result = instance.getClassParameter();
        assertNotNull(result);
        assertEquals("ParameterClass", result.getType());
    }

}
