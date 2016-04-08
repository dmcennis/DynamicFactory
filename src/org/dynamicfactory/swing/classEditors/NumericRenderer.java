package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.EventObject;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class NumericRenderer extends AbstractRenderer{

    ParameterInternal model;

    public NumericRenderer(){
        model = ParameterFactory.newInstance().create();
    }

    public NumericRenderer(ParameterInternal m){
        model = m;
    }

    @Override
    public Renderer prototype() {
        return null;
    }

    @Override
    public Renderer prototype(Properties props) {
        return null;
    }

    public Component displayComponent(int index){
        Object o = model.getValue().get(index);
        return new JTextField(o.toString());
    }

    @Override
    protected Component getRenderer(int index) {
        return null;
    }
}
