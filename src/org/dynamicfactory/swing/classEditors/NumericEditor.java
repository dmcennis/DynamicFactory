package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.propertyQuery.StringQuery;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

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

    JTextField member;

    Color bg;

    static final SyntaxObject isInteger = SyntaxCheckerFactory.newInstance().create(1,1,(new StringQuery()).buildQuery("[0-9]+",false, StringQuery.Operation.MATCHES),String.class);
    static final SyntaxObject isFloatingPoint = SyntaxCheckerFactory.newInstance().create(1,1,(new StringQuery()).buildQuery("[0-9]+(\\.[0-9]+)?(e[0-9]+(\\.[0-9]+)?))?",false, StringQuery.Operation.MATCHES),String.class);
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        member = new JTextField((String)value);
        bg = member.getBackground();
        member.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if((Integer.class.isAssignableFrom(param.getParameterClass()))&&(!isInteger.check(NumericEditor.super.param.getType(),evt.getNewValue()))){
                    member.setBackground(Color.RED);
                }else if((Double.class.isAssignableFrom(param.getParameterClass()))&&(!isFloatingPoint.check(NumericEditor.super.param.getType(),evt.getNewValue()))){
                        member.setBackground(Color.RED);
                }else if(!param.getRestrictions().check(param.getType(),evt.getNewValue())){
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
