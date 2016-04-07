package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.EventObject;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class NumericRenderer extends DefaultTableCellRenderer implements Renderer{

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

 }
