/**
 * DynamicLoader
 * Created Jan 24, 2009 - 11:10:11 PM
 * Copyright Daniel McEnnis, see license.txt
 */
package org.dynamicfactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.PropertiesFactory;
import org.dynamicfactory.descriptors.PropertiesInternal;
import org.dynamicfactory.descriptors.SyntaxCheckerFactory;
import org.dynamicfactory.descriptors.SyntaxObject;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.property.PropertyFactory;

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
/**
 *
 * @author Daniel McEnnis
 */
public class DynamicLoader extends ClassLoader {

    static DynamicLoader instance = null;

    static DynamicLoader newInstance() {
        if (instance == null) {
            instance = new DynamicLoader();
        }
        return instance;
    }
    ProtectionDomain domain = null;



    private DynamicLoader() {
    }

    public void loadClasses() {
        File libDirectory = new File(System.getenv("GRAPH_RAT") + File.pathSeparator + "lib");
        LinkedList<String> classNames = new LinkedList<String>();
        LinkedList<File> fileNames = new LinkedList<File>();
        LinkedList<Class> classesToLoad = new LinkedList<Class>();


        findClass("", libDirectory, classNames, fileNames,classesToLoad);
        File jarDirectory = new File(System.getenv("GRAPH_RAT") + File.pathSeparator + "jar");
        File[] jarList = jarDirectory.listFiles();
        for(int i=0;i<jarList.length;++i){
            if((jarList[i].isFile())&&(jarList[i].getName().matches(".+\\.jar"))){
                try {
                    JarFile jar = new JarFile(jarList[i]);
                    Enumeration<JarEntry> enumeration = jar.entries();
                    while (enumeration.hasMoreElements()) {
                        JarEntry entry = enumeration.nextElement();
                        String fileName = entry.getName();
                        if (fileName.matches(".+\\.class")) {
                            String className = fileName.replace('/', '.');
                            className = className.replace('\\', '.');
                            className = className.replace(':', '.');
                            className.substring(0, className.lastIndexOf('.'));
                            InputStream stream = jar.getInputStream(entry);
                            try {
                                loadClass(className, stream, classesToLoad);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        for(Class item : classesToLoad){
            loadFactories(item);
        }

        for(Class item : classesToLoad){
            loadMaps(item);
        }

    }

    protected void findClass(String prefix, File currentDirectory, LinkedList<String> names, LinkedList<File> files, LinkedList<Class> classesToLoad) {
        File[] children = currentDirectory.listFiles();
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                if (children[i].isDirectory()) {
                    findClass(prefix + children[i].getName() + ".", children[i], names, files, classesToLoad);
                } else if (children[i].getName().matches(".+\\.class")) {
                    FileInputStream fileStream = null;
                    try {
                        String name = children[i].getName().substring(0, children[i].getName().lastIndexOf('.'));
                        fileStream = new FileInputStream(children[i]);
                        loadClass(name, fileStream, classesToLoad);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fileStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    protected void loadClass(String name, InputStream fileStream, LinkedList<Class> classesToLoad) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream dataSink = new ByteArrayOutputStream();
        int read = -1;
        byte[] array = new byte[10240];
        read = fileStream.read(array);
        while (read != -1) {
            dataSink.write(array, 0, read);
            read = fileStream.read(array);
        }
        array = dataSink.toByteArray();
        Class classObject = null;
        if (domain == null) {
            classObject = this.defineClass(name, array, 0, read);
        } else {
            classObject = this.defineClass(name, array, 0, read, domain);
        }
        this.loadClass(name, true);
        classesToLoad.add(classObject);
    }

    protected void loadFactories(Class name){
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Loading "+name.getName());
        if (AbstractFactory.class.isAssignableFrom(name)) {
            try {
                FactoryFactory.newInstance().addType(name.getSimpleName(), (AbstractFactory) name.newInstance(),name);
            } catch (InstantiationException ex) {
                Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, "Failed to create a '" + name.getSimpleName() + "' object", ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DynamicLoader.class.getName()).log(Level.SEVERE, "Failed to create a '" + name.getSimpleName() + "' object", ex);
            }
        }
    }

    protected void loadMaps(Class name) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Loading "+name.getName());
        for(String type : FactoryFactory.newInstance().getKnownTypes()) {
            if(FactoryFactory.newInstance().getType(type).isAssignableFrom(name)) {
                try {
                    Creatable item = (Creatable)name.getConstructor().newInstance();
                    FactoryFactory.newInstance().create(type).getContent().addType(type,item);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
