package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.FactoryFactory;
import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/4/2016.
 */
public abstract class AbstractRenderer<Type> extends DefaultTableCellRenderer implements Renderer {
    protected ParameterInternal param;

    private PropertyEditorTableModel ref;

    protected JTable container=null;

    HashMap<Integer,AbstractEditor<Type>> editing = new HashMap<Integer,AbstractEditor<Type>>();

    protected boolean editable;

    protected JPopupMenu menu = new JPopupMenu();

    AbstractEditor<Type> editor;

    public AbstractRenderer(){
        setModel(new PropertyEditorTableModel(PropertiesFactory.newInstance().create()),(new ParameterFactory<String>()).create("Default"));
    };

    public AbstractRenderer(PropertyEditorTableModel m, Properties p){
        setModel(m,p);
    }

    public AbstractRenderer(PropertyEditorTableModel m, Parameter p){
        setModel(m,p);
    }

    public AbstractRenderer(PropertyEditorTableModel m, ParameterInternal p){
        setModel(m,p);
    }

    protected PropertyEditorTableModel getModel(){
        return ref;
    }

    protected void setModel(PropertyEditorTableModel model, Properties props){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(props == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null Properties object");
        }else if(props.quickCheck("Parameter",ParameterInternal.class)){
            setModel(model,(ParameterInternal)props.quickGet("Parameter"));
        }else if(props.quickCheck("Paramater", Parameter.class)){
            editable=false;
            setModel(model,ParameterFactory.newInstance().create((Parameter)props.quickGet("Parameter"),props));
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model without providing a model");
        }
    }

    protected void setModel(PropertyEditorTableModel model, Parameter param){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        editable = false;
        setModel(model,ParameterFactory.newInstance().create(param));
    }


    protected void setModel(PropertyEditorTableModel model, ParameterInternal param){
        if(model==null){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The model to edit to and from is null");
        }else if(param == null){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: Setting an editor model with a null ParameterInteral object");
        }
        this.param = param;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(param.getValue().size()>1) {
            if ((container == null) || (container.getRowCount() != param.getValue().size())) {
                container = new JTable();
                for (int i = 0; i < param.getValue().size(); ++i) {
                    if(editing.containsKey(i)){
                        container.add(editing.get(i).getTableCellEditorComponent(table,value,isSelected,row,column));
                    }else {
                        container.add(getRenderer(i));
                    }
                }
                return container;
            } else {
                for (int i = 0; i < param.getValue().size(); ++i) {
                    container.setValueAt(getRenderer(i), i, 0);
                }
                return container;
            }
        }else if(param.getValue().size()==1){
            return getRenderer(0);
        }
        return new JTextField("Empty Parameter");
    }

    protected abstract Component getRenderer(int index);

    @Override
    public JToolTip createToolTip() {
        JToolTip tip = new JToolTip();
        tip.setTipText(param.getDescription());
        return tip;
    }

    @Override
    public JPopupMenu getComponentPopupMenu() {
        if(editable){
            menu = new JPopupMenu();
            menu.add(new AddValue());
            menu.add(new RemoveValue());
            return menu;
        }else{
            return super.getComponentPopupMenu();
        }
    }
    protected class AddParameter extends AbstractAction {
        public AddParameter() {
            setEnabled(editable);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editable) {
                ParameterInternal old = ParameterFactory.newInstance().create(param);
                param.add(defaultItem());
                editing.put(param.getValue().size() - 1, getEditor(ref, param,param.getValue().size()-1));
                firePropertyChange("Param",old,param );
                invalidate();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "INTERNAL: Attempting to add values to an uneditable parameter.");
            }
        }
    }

    protected class RemoveParameter extends AbstractAction{
        public RemoveParameter(){
            setEnabled(editable);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (editable){
                int index = container.getSelectedRow();
                if((index<0)){
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is negative or does not exist");
                }else if(index >= param.getValue().size()){
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is beyond the end of the table");
                }else {
                    ParameterInternal old = ParameterFactory.newInstance().create(param);
                    param.getValue().remove(index);
                    HashMap<Integer, AbstractEditor<Type>> rep = new HashMap<Integer, AbstractEditor<Type>>();
                    for (Integer i : editing.keySet()) {
                        if (i < index) {
                            rep.put(i, editing.get(i));
                        } else if (i > index) {
                            rep.put(i - 1, editing.get(i));
                        }
                    }
                    editing = rep;
                    firePropertyChange("Param",old,param );
                    invalidate();
                }
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "INTERNAL: Attempting to add values to an uneditable parameter.");
            }
        }
    }

    protected class AddValue<Type> extends AbstractAction {
        public AddValue() {
            setEnabled(editable);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editable) {
                ParameterInternal old = ParameterFactory.newInstance().create(param);
                param.add(defaultItem());
                editing.put(param.getValue().size() - 1, getEditor(ref, param,param.getValue().size()-1));
                firePropertyChange("Param",old,param );
                invalidate();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "INTERNAL: Attempting to add values to an uneditable parameter.");
            }
        }
    }

        protected class RemoveValue extends AbstractAction{
            public RemoveValue(){
                setEnabled(editable);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editable){
                    int index = container.getSelectedRow();
                    if((index<0)){
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is negative or does not exist");
                    }else if(index >= param.getValue().size()){
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is beyond the end of the table");
                    }else {
                        ParameterInternal old = ParameterFactory.newInstance().create(param);
                        param.getValue().remove(index);
                        HashMap<Integer, AbstractEditor<Type>> rep = new HashMap<Integer, AbstractEditor<Type>>();
                        for (Integer i : editing.keySet()) {
                            if (i < index) {
                                rep.put(i, editing.get(i));
                            } else if (i > index) {
                                rep.put(i - 1, editing.get(i));
                            }
                        }
                        editing = rep;
                        firePropertyChange("Param",old,param );
                        invalidate();
                    }
                } else {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "INTERNAL: Attempting to add values to an uneditable parameter.");
                }
            }
        }

    protected class EditValue<Type> extends AbstractAction {
        public EditValue() {
            setEnabled(editable);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editable) {
                ParameterInternal old = ParameterFactory.newInstance().create(param);
                int index = container.getSelectedRow();
                if((index<0)){
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is negative or does not exist");
                }else if(index >= param.getValue().size()){
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"INTERNAL: The selected row is beyond the end of the table");
                }
                firePropertyChange("Param",old,param );
                invalidate();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "INTERNAL: Attempting to add values to an uneditable parameter.");
            }
        }
    }


    protected Type defaultItem(){
        return (Type)FactoryFactory.newInstance().create(param.getParameterClass().getInterfaces()[0].getSimpleName()).getContent().create(param.getParameterClass().getSimpleName());
    }

    @Override
    public Renderer prototype(Properties props) {
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
        if(skip){
            return prototype();
        }
        Renderer r = null;
        if((r = prototype((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"),props))==null){
            try {
                r = this.getClass().getConstructor(PropertyEditorTableModel.class,ParameterInternal.class,Properties.class).newInstance((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"),props);
            } catch (Exception e) {
                try {
                    r = this.getClass().getConstructor(PropertyEditorTableModel.class,ParameterInternal.class).newInstance((PropertyEditorTableModel)props.quickGet("Model"),(ParameterInternal)props.quickGet("ParameterInternal"));
                } catch (Exception e1) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: At least one of 'prototype(Properties)' and 'prototype(PropertyEditorTableModel,ParameterInternal,Properties)' must be overridden in this class: returning null");
                    return null;
                }
            }
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"DEVELOPER: At least one of 'prototype(Properties)' and 'prototype(PropertyEditorTableModel,ParameterInternal,Properties)' must be overridden in this class. Performance problems from the reflection used are likely.");
            return prototype();
        }else{
            return r;
        }
    }

    protected Renderer prototype(PropertyEditorTableModel ref, ParameterInternal param, Properties props){
        return null;
    }

    @Override
    public Renderer prototype() {
        return RendererFactory.getInstance().create(this.getClass().getSimpleName());
    }

    protected abstract AbstractEditor<Type> getEditor(PropertyEditorTableModel ref, ParameterInternal param, int index);
}
