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



/**

 * Error thrown when a new value is added to a org.dynamicfactory.property that is not assignable

 * to the class of the org.dynamicfactory.property.

 * 

 * @author Daniel McEnnis

 */

public class InvalidObjectTypeException extends Exception {



    String message;

    

    /**

     * Define the exception

     * @param expected string of the class the org.dynamicfactory.property objects accept

     * @param recieved string of the class the org.dynamicfactory.property objects recieved as a new value

     */

    public InvalidObjectTypeException(String expected, String recieved){

        super();

        message = "Expected class "+expected +" but found class "+recieved;

    }

    @Override

    public String getMessage() {

        return message;

    }

    

}

