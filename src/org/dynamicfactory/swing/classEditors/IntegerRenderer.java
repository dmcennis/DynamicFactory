package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.swing.PropertyEditorTableModel;

/**
 * Created by dmcennis on 4/13/2016.
 */
public class IntegerRenderer extends TextFieldRendererObject<Integer>{
    @Override
    protected TextFieldEditorObject<Integer> getEditor(PropertyEditorTableModel ref, ParameterInternal param, int index) {
        return new IntegerEditor(ref,param,index);
    }

    public IntegerRenderer(){ super(new PropertyEditorTableModel(PropertiesFactory.newInstance().create()), (Parameter)ParameterFactory.newInstance().create());}

    public IntegerRenderer(PropertyEditorTableModel m, Properties p) {
        super(m, p);
    }

    public IntegerRenderer(PropertyEditorTableModel m, Parameter p) {
        super(m, p);
    }

    public IntegerRenderer(PropertyEditorTableModel m, ParameterInternal p) {
        super(m, p);
    }

    @Override
    protected Renderer prototype(PropertyEditorTableModel ref, ParameterInternal param, Properties props) {
        return new IntegerRenderer(ref,param);
    }
}
