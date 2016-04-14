package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class StringEditor extends TextFieldEditorObject<String>{

    public StringEditor() {
        super();
    }

    public StringEditor(PropertyEditorTableModel m, ParameterInternal p, int i) {
        super(m,p,i);
    }

    public StringEditor(PropertyEditorTableModel m, Properties p, int i) {
        super(m,p,i);
    }

    @Override
    protected boolean parsingCheck(String object) {
        return true;
    }

    protected String parse(String object){
        return object;
    }

    protected Editor prototype(PropertyEditorTableModel ref, ParameterInternal param, int index, Properties props){
        return new StringEditor(ref,param,index);
    }

    @Override
    public StringEditor prototype(Properties props) {
        return new StringEditor(getModel(),props,index);
    }


}
