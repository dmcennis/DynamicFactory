package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.swing.PropertyEditorTableModel;

/**
 * Created by dmcennis on 4/13/2016.
 */
public class FloatingRenderer extends TextFieldRendererObject<Double>{
    public FloatingRenderer() {
        super();
    }

    public FloatingRenderer(PropertyEditorTableModel m, Properties p) {
        super(m, p);
    }

    public FloatingRenderer(PropertyEditorTableModel m, Parameter p) {
        super(m, p);
    }

    public FloatingRenderer(PropertyEditorTableModel m, ParameterInternal p) {
        super(m, p);
    }

    @Override
    protected TextFieldEditorObject getEditor(PropertyEditorTableModel ref, ParameterInternal param, int index) {
        return new FloatingEditor(ref,param,index);
    }

    @Override
    protected Renderer prototype(PropertyEditorTableModel ref, ParameterInternal param, Properties props) {
        return new FloatingRenderer(ref, param);
    }
}
