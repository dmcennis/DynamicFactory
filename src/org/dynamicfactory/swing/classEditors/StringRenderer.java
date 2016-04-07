package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
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
public class StringRenderer extends DefaultTableCellRenderer implements Renderer{

    ParameterInternal param;

    boolean editable = true;
    public StringRenderer(){
        param = ParameterFactory.newInstance().create("None",String.class);
    }

    public StringRenderer(ParameterInternal p){
        param = p;
    }

    public StringRenderer(Parameter p){
        param = (ParameterInternal)p;
        editable = false;
    }

    @Override
    public StringRenderer prototype() {
        return new StringRenderer();
    }

    @Override
    public StringRenderer prototype(Properties props) {
        if((props!=null)&&(props.get().size()>0)){
            return new StringRenderer(props.get().get(0));
        }
        return new StringRenderer(props.get().get(0));
    }

}
