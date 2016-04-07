package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.AbstractFactory;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class EditorFactory extends AbstractFactory<Editor> {
    private static EditorFactory ourInstance = new EditorFactory();

    public static EditorFactory getInstance() {
        return ourInstance;
    }

    private EditorFactory() {
        map.put("NumericEditor",new NumericEditor());
    }

    @Override
    public AbstractFactory<Editor> prototype() {
        return getInstance();
    }
}
