/*

 * ModelShell.java

 *

 * Created on 8 October 2007, 13:28

 *

 */



package org.dynamicfactory.model;



/**

 * Shell class providing basic MVC support to models.  Models must define what the 

 * enums for each type of change and manually fire the change method, but this 

 * class handles all other bookkeeping.

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

public class ModelShell implements Model{

    

    protected java.util.LinkedList<Listener> listener = new java.util.LinkedList<Listener>();

    

    /** Creates a new instance of ModelShell */

    public ModelShell() {

    }

    

    /**

     * notifies all listeners of changes of the given type.

     * 

     * @param type kind of change that has occured, defined by each subclass.

     */

    protected void fireChange(int type, int argument){

        java.util.Iterator<Listener> it = listener.iterator();

        while(it.hasNext()){

            it.next().publishChange(this,type,argument);

        }

    }



    /**

     * Add a listener to this model

     * 

     */

    public void addListener(Listener l) {

        listener.add(l);

    }

}

