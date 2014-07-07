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
package org.dynamicfactory;

import junit.framework.TestCase;

public class DynamicLoaderTest extends TestCase {

    public void testNewInstance() throws Exception {
        DynamicLoader item = DynamicLoader.newInstance();
        assertNotNull(item);
    }

    public void testLoadClasses() throws Exception {

    }

    public void testFindClass() throws Exception {

    }

    public void testLoadClass() throws Exception {

    }

    public void testLoadFactories() throws Exception {

    }

    public void testLoadMaps() throws Exception {

    }
}