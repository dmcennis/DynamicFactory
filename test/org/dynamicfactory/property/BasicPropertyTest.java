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
/*


 * BasicPropertyTest.java


 * JUnit based test


 *


 * Created on 22 July 2007, 17:40


 *


 * Copyright Daniel McEnnis, all rights reserved


 */





package org.dynamicfactory.property;





import java.util.List;
import junit.framework.*;





/**


 *


 * @author Daniel McEnnis


 */


public class BasicPropertyTest extends TestCase {


    


    public BasicPropertyTest(String testName) {


        super(testName);


    }





    protected void setUp() throws Exception {


    }





    protected void tearDown() throws Exception {


    }





    /**


     * Test of getValue method, of class nz.ac.waikato.mcennis.arm.graph.org.dynamicfactory.property.BasicProperty.


     */


    public void testGetValue() {


        System.out.println("getValue");


        


        BasicProperty instance = new BasicProperty("Type",String.class);


        instance.add("Value1");


        instance.add("Value2");





        List<Object> result = instance.getValue();


        assertNotNull(result);


        assertEquals(2, result.size());


        for(int i=0;i<result.size();++i){


            if("Value1".equals(result.get(i))){


                


            }else if("Value2".equals(result.get(i))){


                


            }else{


                fail("Unexpected value "+result.get(i)+"encountered");


            }


        }


        


    }





    /**


     * Test of getType method, of class nz.ac.waikato.mcennis.arm.graph.org.dynamicfactory.property.BasicProperty.


     */


    public void testGetType() {


        System.out.println("getType");


        


        BasicProperty instance = new BasicProperty("Type",String.class);


        


        String expResult = "Type";


        String result = instance.getType();


        assertEquals(expResult, result);


        


    }





    /**


     * Test of add method, of class nz.ac.waikato.mcennis.arm.graph.org.dynamicfactory.property.BasicProperty.


     */


    public void testAdd() {


        System.out.println("add");


        


        String value = "Value";


        BasicProperty instance = new BasicProperty("Type",String.class);


        


        instance.add(value);


        


    }





    /**


     * Test of compareTo method, of class nz.ac.waikato.mcennis.arm.graph.org.dynamicfactory.property.BasicProperty.


     */


    public void testCompareToSame() {


        System.out.println("compareToSame");


        


        BasicProperty o = new BasicProperty("Type",String.class);


        BasicProperty instance = new BasicProperty("Type",String.class);


        


        int expResult = 0;


        int result = instance.compareTo(o);


        assertEquals(expResult, result);


    }


    


    public void testCompareToDifferentType() {


        System.out.println("compareToDifferentType");


        


        BasicProperty o = new BasicProperty("TypeA",String.class);


        BasicProperty instance = new BasicProperty("TypeB",String.class);


        


        int expResult = 1;


        int result = instance.compareTo(o);


        assertEquals(expResult, result);


    }

    public void testCompareToDifferentClass() {
        System.out.println("compareToDifferentType");
        BasicProperty o = new BasicProperty("TypeA",String.class);
        BasicProperty instance = new BasicProperty("TypeB",Double.class);


        


        int expResult = 1;


        int result = instance.compareTo(o);


        assertEquals(expResult, result);


    }


    


    public void testCompareToDifferingValueLength() {


        System.out.println("compareToDifferentValueLength");


        


        BasicProperty o = new BasicProperty("Type",String.class);


        BasicProperty instance = new BasicProperty("Type",String.class);


        instance.add("Value");


        int expResult = 1;


        int result = instance.compareTo(o);


        assertEquals(expResult, result);


    }


    


        public void testCompareToDifferingContent() {


        System.out.println("compareToDifferingContent");


        


        BasicProperty o = new BasicProperty("Type",String.class);


        o.add("Right");


        BasicProperty instance = new BasicProperty("Type",String.class);


        instance.add("Left");


        assertTrue(instance.compareTo(o)<0);


    }


    





    


}


