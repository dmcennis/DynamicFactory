package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/7/2016.
 */
public abstract class AbstractEditor<Type> implements Editor {

    protected ParameterInternal param;

    private PropertyEditorTableModel ref;

    private LinkedList<CellEditorListener> listeners = new LinkedList<CellEditorListener>();

    protected int index;

    protected boolean editable=true;

    protected JTable container=null;


    public AbstractEditor(){
        param = ParameterFactory.newInstance().create();
        ref = null;
    }

    public AbstractEditor(PropertyEditorTableModel m, Parameter p, int index){
        setModel(m,p,index);
    }

    public AbstractEditor(PropertyEditorTableModel m, Properties p, int index){
        setModel(m,p,index);
    }

    public AbstractEditor(PropertyEditorTableModel m, ParameterInternal p, int index){
        setModel(m,p,index);
    }

    protected void setModel(PropertyEditorTableModel model, Properties props, int i){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(props == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null Properties object");
        }else if(props.quickCheck("Parameter",ParameterInternal.class)){
            setModel(model,(ParameterInternal)props.quickGet("Parameter"),i);
        }else if(props.quickCheck("Paramater", Parameter.class)){
            editable=false;
            setModel(model,ParameterFactory.newInstance().create((Parameter)props.quickGet("Parameter"),props),i);
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model without providing a model");
        }
    }

    protected void setModel(PropertyEditorTableModel model, Parameter param, int i){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        editable = false;
        setModel(model,ParameterFactory.newInstance().create(param),i);
    }


    protected void setModel(PropertyEditorTableModel model, ParameterInternal param, int i){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        this.param = param;
        this.index=i;
    }

    @Override
    public Object getCellEditorValue() {
        return param.getValue().get(index);
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return editable;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return (editable = false);
    }

    @Override
    public void cancelCellEditing() {
        param.set(ref.get(param.getType()).getValue());
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listeners.add(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listeners.remove(l);
    }

    @Override
    public abstract Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column);


    protected boolean check(Type value){
        if(editable && param.getRestrictions().check(param.getType(),value)){
            return true;
        }
        return false;
    }

    protected PropertyEditorTableModel getModel(){
        return ref;
    }

    @Override
    public Editor prototype(Properties props) {
        if(props == null){
            return prototype();
        }
        boolean skip = false;
        if(!props.quickCheck("Model",PropertyEditorTableModel.class)){
            skip = true;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"\"Model\" is required to have a PropertyEditorTableModel object for this prototype function");
        }
        if(!props.quickCheck("Parameter",ParameterInternal.class)){
            skip = true;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"\"Parameter\" is required to have a ParameterInternal object for this prototype function");
        }
        int index=0;
        if(!props.quickCheck("Index",Integer.class)){
            index = (int)props.quickGet("Index");
        }
        if(skip){
            return prototype();
        }
        Editor r = null;
        if((r = prototype((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"),index,props))==null){
            try {
                r = this.getClass().getConstructor(PropertyEditorTableModel.class,ParameterInternal.class,Integer.class,Properties.class).newInstance((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"),index,props);
            } catch (Exception e) {
                try {
                    r = this.getClass().getConstructor(PropertyEditorTableModel.class,ParameterInternal.class,Integer.class,Properties.class).newInstance((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"),index);
                } catch (Exception e1) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: At least one of 'prototype(Properties)' and 'prototype(PropertyEditorTableModel,ParameterInternal,Properties)' must be overridden in this class: returning null");
                    return null;
                }
            }
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: At least one of 'prototype(Properties)' and 'prototype(PropertyEditorTableModel,ParameterInternal,Properties)' must be overridden in this class. Performance problems from the reflection used are likely.");
            return r;
        }else{
            return r;
        }
    }

    protected Editor prototype(PropertyEditorTableModel ref, ParameterInternal param, int index, Properties props){
        return null;
    }

    @Override
    public Editor prototype() {
        return EditorFactory.getInstance().create(this.getClass().getSimpleName());
    }

}
