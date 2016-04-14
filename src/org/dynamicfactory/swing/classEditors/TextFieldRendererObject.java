package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmcennis on 4/13/2016.
 */
public abstract class TextFieldRendererObject<Type> extends AbstractRenderer<Type> {

    public TextFieldRendererObject(){super();}

    public TextFieldRendererObject(PropertyEditorTableModel m, Properties p) {
        super(m, p);
    }

    public TextFieldRendererObject(PropertyEditorTableModel m, Parameter p) {
        super(m, p);
    }

    public TextFieldRendererObject(PropertyEditorTableModel m, ParameterInternal p) {
        super(m, p);
    }

    @Override
    protected Component getRenderer(int index) {
        return new JTextField((String)param.getValue().get(index));
    }

    @Override
    protected abstract TextFieldEditorObject getEditor(PropertyEditorTableModel ref, ParameterInternal param, int index);
}
