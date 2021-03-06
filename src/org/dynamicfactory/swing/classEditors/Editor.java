package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Created by dmcennis on 4/4/2016.
 */
public interface Editor extends TableCellEditor, Creatable<Editor> {
    @Override
    Editor prototype();

    @Override
    Editor prototype(Properties props);

    Editor prototype(PropertyEditorTableModel model, ParameterInternal parameter, int index, Properties propertiesStringEditor);
}
