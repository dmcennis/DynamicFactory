DynamicFactory
==============

Classes for automating dynamic loading of objects and helper classes for making factory classes painless.

The main factory classes are working, but the dynamic loading classes are still not tested.  

Creating a factory object
--------------------------

* implement prototype() on members of your interface that returns a new version of that object.

* create a subclass of AbstractFactory

* implement create function, on the factory subclass that returns an object given a Properties object

* implement a static newInstance() method that returns a singleton for the factory

* implement getClassParameter() method that returns ta reference to a copy of the parameter defining which class to build

* Place the ensuing jar file in the correct directory to have it uploaded and automatically added to the system when the directory containing it is scanned by the DynamicLoader


This provides a full interface with 

* Factory.newInstance().create(String versionType, Properties properties) method

* Automatic algorithmically discoverable list of both factories and the object types in them

* internally-documented parameters

* configuration properties of arbitrary types

* suite of additional methods inherited from AbstractFactory implemented using the core methods in the subclass.
