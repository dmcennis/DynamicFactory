package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class NumericEditor extends AbstractEditor {

    public NumericEditor(Properties p) {
        super(p);
    }

    public NumericEditor() {
        super();
    }

    public NumericEditor(ParameterInternal p) {
        super(p);
    }

    public NumericEditor(Parameter p) {
        super(p);
    }

    @Override
    public Editor prototype(Properties props) {
        return new NumericEditor(props);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }
}
