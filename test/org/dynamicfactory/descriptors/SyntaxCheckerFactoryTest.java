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
import org.dynamicfactory.propertyQuery.PropertyQuery;
import org.dynamicfactory.propertyQuery.StringQuery;

/**
 *
 * @author mcennis
 */
public class SyntaxCheckerFactoryTest extends TestCase {
    
    public SyntaxCheckerFactoryTest(String testName) {
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
     * Test of newInstance method, of class SyntaxCheckerFactory.
     */
    public void testNewInstance() {
        System.out.println("newInstance");
        SyntaxCheckerFactory expResult = SyntaxCheckerFactory.newInstance();
        SyntaxCheckerFactory result = SyntaxCheckerFactory.newInstance();
        assertNotNull(result);
        assertSame(expResult,result);
    }

    /**
     * Test of create method, of class SyntaxCheckerFactory.
     */
    public void testCreate_4args() {
        System.out.println("create");
        int minCount = 1;
        int maxCount = 2;
        StringQuery query = new StringQuery();
        query.buildQuery("Test", false, StringQuery.Operation.MATCHES);
        Class classType = Double.class;
        SyntaxCheckerFactory instance = SyntaxCheckerFactory.newInstance();
        SyntaxObject result = instance.create(minCount, maxCount, query, classType);
        assertNotNull(result);
        assertEquals(1,result.getMinCount());
        assertEquals(2,result.getMaxCount());
        assertSame(query,result.getTest());
        assertEquals(Double.class,result.getClassType());
    }

    /**
     * Test of create method, of class SyntaxCheckerFactory.
     */
    public void testPartCreate_5args() {
        System.out.println("create");
        int minCount = 1;
        int maxCount = 2;
        StringQuery query = new StringQuery();
        query.buildQuery("Test", false, StringQuery.Operation.MATCHES);
        Class classType = Double.class;
        Properties props = null;
        SyntaxCheckerFactory instance = SyntaxCheckerFactory.newInstance();
        SyntaxObject result = instance.create(minCount, maxCount, query, classType,props);
        assertNotNull(result);
        assertEquals(1,result.getMinCount());
        assertEquals(2,result.getMaxCount());
        assertSame(query,result.getTest());
        assertEquals(Double.class,result.getClassType());
    }

    /**
     * Test of create method, of class SyntaxCheckerFactory.
     */
    public void testPropertiesCreate_5args() {
        System.out.println("create");
        int minCount = 1;
        int maxCount = 2;
        StringQuery query = new StringQuery();
        query.buildQuery("Test", false, StringQuery.Operation.MATCHES);
        Class classType = null;
        Properties props = PropertiesFactory.newInstance().create();
        props.set("MinCount", 3);
        props.set("MaxCount", 4);
        props.set("Test", query);
        props.set("TypeClass", Double.class);
        SyntaxCheckerFactory instance = SyntaxCheckerFactory.newInstance();
        SyntaxObject result = instance.create(minCount, maxCount, null, classType,props);
        assertNotNull(result);
        assertEquals(1,result.getMinCount());
        assertEquals(2,result.getMaxCount());
        assertSame(query,result.getTest());
        assertEquals(Double.class,result.getClassType());
    }

    /**
     * Test of create method, of class SyntaxCheckerFactory.
     */
    public void testBothCreate_5args() {
        System.out.println("create");
        int minCount = 3;
        int maxCount = 4;
        StringQuery query = new StringQuery();
        query.buildQuery("Test", false, StringQuery.Operation.MATCHES);
        Class classType = Double.class;
        Properties props = PropertiesFactory.newInstance().create();
        props.set("MinCount", 3);
        props.set("MaxCount", 4);
        props.set("TypeClass", Long.class);
        StringQuery query2 = new StringQuery();
        query2.buildQuery("Test2", false, StringQuery.Operation.MATCHES);
        props.set("Test", query2);
        SyntaxCheckerFactory instance = SyntaxCheckerFactory.newInstance();
        SyntaxObject result = instance.create(minCount, maxCount, query, classType,props);
        assertNotNull(result);
        assertEquals(3,result.getMinCount());
        assertEquals(4,result.getMaxCount());
        assertSame(query,result.getTest());
        assertEquals(Double.class,result.getClassType());
    }

    /**
     * Test of create method, of class SyntaxCheckerFactory.
     */
    public void testCreate_Properties() {
        System.out.println("create");
        Properties props = null;
        SyntaxCheckerFactory instance = null;
        SyntaxObject expResult = null;
        SyntaxObject result = instance.create(props);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassParameter method, of class SyntaxCheckerFactory.
     */
    public void testGetClassParameter() {
        System.out.println("getClassParameter");
        SyntaxCheckerFactory instance = null;
        Parameter expResult = null;
        Parameter result = instance.getClassParameter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
