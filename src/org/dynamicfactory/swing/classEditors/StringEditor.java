package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by dmcennis on 4/7/2016.
 */
public class StringEditor extends AbstractEditor{

    JTextField member;

    Color bg;

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
        member = new JTextField((String)value);
        bg = member.getBackground();
        member.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if(!param.getRestrictions().check(param.getType(),evt.getNewValue())){
                    member.setBackground(Color.RED);
                }else{
                    member.setBackground(bg);
                }
            }
        });
        return member;
    }

    @Override
    public Editor prototype(Properties props) {
        return new StringEditor(props);
    }


}
