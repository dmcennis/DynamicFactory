package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import java.awt.*;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/7/2016.
 */
public abstract class AbstractEditor implements Editor {

    ParameterInternal param;

    LinkedList<CellEditorListener> listeners = new LinkedList<CellEditorListener>();

    public AbstractEditor(){
        param = ParameterFactory.newInstance().create();
    }

    @Override
    public Editor prototype() {
        return this;
    }

    protected void setModel(Properties props){
        if(props == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"Setting an editor model with a null Properties object");
        }else if(props.quickCheck("Parameter",ParameterInternal.class)){
            setModel((ParameterInternal)props.quickGet("Parameter"));
        }else if(props.quickCheck("Paramater", Parameter.class)){
            setModel(ParameterFactory.newInstance().create((Parameter)props.quickGet("Parameter"),props));
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model without providing a model");
        }
    }

    protected void setModel(Parameter param){
        if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        setModel(ParameterFactory.newInstance().create(param));
    }


    protected void setModel(ParameterInternal param){
        if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        this.param = param;
    }

    @Override
    public Object getCellEditorValue() {
        return param.getValue();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return false;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listeners.add(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listeners.remove(l);
    }

    public AbstractEditor(Parameter p){
        setModel(p);
    }
    public AbstractEditor(ParameterInternal p){
        setModel(p);
    }
    public AbstractEditor(Properties p){
        setModel(p);
    }
}
