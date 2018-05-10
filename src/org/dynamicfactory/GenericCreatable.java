/**
 * Created by Daniel McEnnis on 3/23/2016
 * <p/>
 * Copyright Daniel McEnnis 2015
 */

package org.dynamicfactory;

import org.dynamicfactory.descriptors.Properties;

import java.lang.reflect.InvocationTargetException;

/**
 * Default Description Google Interview Project
 */
public class GenericCreatable<Type> implements Creatable<Type> {

    public Type getContent() {
        return content;
    }

    public void setContent(Type content) {
        this.content = content;
    }

    Type content = null;

    /**
     * Default constructor for AbstractCreatable
     */
    public GenericCreatable(Type c) {
        content = c;
    }

    @Override
    public Type prototype() {
        if(content == null){
            return null;
        }
        try {
            Type c = (Type)content.getClass().getConstructor().newInstance();
            return c;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public Type prototype(Properties props) {
        return prototype();
    }
}
