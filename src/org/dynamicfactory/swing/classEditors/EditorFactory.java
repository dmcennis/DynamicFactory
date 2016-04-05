package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.AbstractFactory;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class EditorFactory extends AbstractFactory<Editor>{
    private static EditorFactory ourInstance = new EditorFactory();

    public static EditorFactory getInstance() {
        return ourInstance;
    }

    private EditorFactory() {
        map.put("Numeric",new NumericEditor());
        map.put("String",new StringEditor());
    }

    public EditorFactory prototype(){
        return getInstance();
    }
}
