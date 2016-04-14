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
 * Created by dmcennis on 4/13/2016.
 */
public abstract class TextFieldEditorObject<Type> extends AbstractEditor<Type> {

    Color bg;

    JTextField member;

    public TextFieldEditorObject(PropertyEditorTableModel m, Parameter p,int i){
        super(m,p,i);
    }

    public TextFieldEditorObject(PropertyEditorTableModel m, ParameterInternal p, int i){
        super(m,p,i);
    }

    public TextFieldEditorObject(PropertyEditorTableModel m, Properties p,int i){
        super(m,p,i);
    }

    public TextFieldEditorObject(){
        super();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        member = new JTextField((String)value);
        bg = member.getBackground();
        member.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if(!parsingCheck((String)evt.getNewValue())){
                    member.setBackground(Color.YELLOW);
                }
                else if(!check((Type)(evt.getNewValue()))){
                    member.setBackground(Color.RED);
                }else{
                    member.setBackground(bg);
                }
            }
        });
        return member;
    }

    protected abstract Type parse(String object);

    protected abstract boolean parsingCheck(String object);
}
