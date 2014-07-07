/*

 * Model.java

 *

 * Created on 8 October 2007, 13:23

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


package org.dynamicfactory.model;



/**

 * Interface for implementing MVC.  TO be replaced by the standard JavaBeans

 *

 * @author Daniel McEnnis

 * 

 */

public interface Model {

    

    /**

     * Add a listener to recieve changes from this object

     * 

     * @param l object that wants to listens to changes

     */

    public void addListener(Listener l);

    

}

