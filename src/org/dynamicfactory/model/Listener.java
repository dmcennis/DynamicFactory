/*

 * Listener.java

 *

 * Created on 8 October 2007, 13:24

 *

 * Copyright Daniel McEnnis, published under Aferro GPL (see license.txt)

 */



package org.dynamicfactory.model;



/**

 * Class for implementing a lister interface for View part of MVC.

 *

 * @author Daniel McEnnis

 * 

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

public interface Listener {

    /**

     * Recieve and update of the given type.

     * @param m model that changed

     * @param type type of change that has occured

     */

    public void publishChange(Model m, int type, int argument);

}

