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

/**
 *
 * @author mcennis
 */
public class PropertiesFactoryTest extends TestCase {
    
    public PropertiesFactoryTest(String testName) {
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
     * Test of newInstance method, of class PropertiesFactory.
     */
    public void testNewInstance() {
        System.out.println("newInstance");
        PropertiesFactory expResult = PropertiesFactory.newInstance();
        PropertiesFactory result = PropertiesFactory.newInstance();
        assertNotNull(result);
        assertSame(expResult, result);
    }

    /**
     * Test of create method, of class PropertiesFactory.
     */
    public void testCreate_0args() {
        System.out.println("create");
        PropertiesFactory instance = PropertiesFactory.newInstance();
        PropertiesInternal result = instance.create();
        assertNotNull(result);
        assertTrue(result instanceof PropertiesInternal);
        assertEquals(0,result.get().size());
    }

    /**
     * Test of create method, of class PropertisFactory.
     */
    public void testCreate_Properties() {
        System.out.println("create");
        Properties props = PropertiesFactory.newInstance().create();
        props.set("PropertiesClass","PropertiesImplementation");
        PropertiesFactory instance = PropertiesFactory.newInstance();
        PropertiesInternal result = instance.create(props);
        assertNotNull(result);
        assertTrue(result instanceof PropertiesInternal);
        assertEquals(0,result.get().size());
    }

    /**
     * Test of create method, of class PropertisFactory.
     */
    public void testNullCreate_Properties() {
        System.out.println("create");
        Properties props = PropertiesFactory.newInstance().create();
        PropertiesFactory instance = PropertiesFactory.newInstance();
        PropertiesInternal result = instance.create(props);
        assertNotNull(result);
        assertTrue(result instanceof PropertiesInternal);
        assertEquals(0,result.get().size());
    }

    /**
     * Test of getClassParameter method, of class PropertiesFactory.
     */
    public void testGetClassParameter() {
        System.out.println("getClassParameter");
        PropertiesFactory instance = PropertiesFactory.newInstance();
        Parameter result = instance.getClassParameter();
        assertNotNull(result);
        assertEquals("PropertiesClass",result.getType());
        assertEquals(1,result.getValue().size());
    }

}
