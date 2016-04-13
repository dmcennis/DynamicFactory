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

    @Override
    public Editor prototype() {
        return this;
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
}
