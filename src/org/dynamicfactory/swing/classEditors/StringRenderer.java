package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.swing.PropertyEditorTableModel;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class StringRenderer extends TextFieldRendererObject<String>{

    public StringRenderer(){
        super(new PropertyEditorTableModel(PropertiesFactory.newInstance().create()), PropertiesFactory.newInstance().create());
    }

    public StringRenderer(PropertyEditorTableModel m, Properties p) {
        super(m, p);
    }

    public StringRenderer(PropertyEditorTableModel m, Parameter p) {
        super(m, p);
    }

    public StringRenderer(PropertyEditorTableModel m, ParameterInternal p) {
        super(m, p);
    }

    @Override
    protected TextFieldEditorObject getEditor(PropertyEditorTableModel ref, ParameterInternal param, int index) {
        return new StringEditor(ref,param, index);
    }

    protected Renderer prototype(PropertyEditorTableModel ref, ParameterInternal param, Properties props){
        return new StringRenderer(ref,param);
    }

    @Override
    public StringRenderer prototype(Properties props) {
        if((props!=null)&&(props.get().size()>0)){
            return new StringRenderer(getModel(),props.get(param.getType()));
        }
        return new StringRenderer(getModel(),props.get().get(0));
    }
}
