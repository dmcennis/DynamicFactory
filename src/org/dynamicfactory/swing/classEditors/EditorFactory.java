package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.AbstractFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.property.InvalidObjectTypeException;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class EditorFactory extends AbstractFactory<Editor> {
    private static EditorFactory ourInstance = new EditorFactory();

    public static EditorFactory getInstance() {
        return ourInstance;
    }

    private EditorFactory() {
        map.put("IntegerEditor",new IntegerEditor());
        map.put("DoubleEditor", new FloatingEditor());
        map.put("StringEditor",new StringEditor());
    }

    public static Editor create(PropertyEditorTableModel model, ParameterInternal parameter, int index) {
        return EditorFactory.getInstance().create("").prototype(model,parameter,index,EditorFactory.getInstance().properties);
    }

    public static Editor create(PropertyEditorTableModel model, ParameterInternal parameter, int index, Properties properties) {
        try {
            return EditorFactory.getInstance().create("").prototype(model,parameter,index,properties.mergeDefaults(EditorFactory.getInstance().properties));
        } catch (InvalidObjectTypeException e) {
            Logger.getLogger(EditorFactory.getInstance().getClass().getName()).log(Level.WARNING,String.format("Additional parameters were discarded for conflicting with the class type of the defaults. %s",e.getLocalizedMessage()));
            return EditorFactory.getInstance().create("").prototype(model,parameter,index,EditorFactory.getInstance().properties);
        }
    }

    @Override
    public AbstractFactory<Editor> prototype() {
        return getInstance();
    }
}
