package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class StringEditor extends AbstractEditor{
    public StringEditor() {
        super();
    }

    public StringEditor(Parameter p) {
        super(p);
    }

    public StringEditor(ParameterInternal p) {
        super(p);
    }

    public StringEditor(Properties p) {
        super(p);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }

    @Override
    public Editor prototype(Properties props) {
        return new StringEditor(props);
    }
}
